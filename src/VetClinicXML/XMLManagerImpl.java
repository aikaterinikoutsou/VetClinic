package VetClinicXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import VetClinicIfaces.OwnerManager;
import VetClinicIfaces.PetManager;
import VetClinicIfaces.XMLManager;
import VetClinicJDBC.JDBCManager;
import VetClinicJDBC.JDBCOwnerManager;
import VetClinicJDBC.JDBCPetManager;
import VetClinicPOJOs.Owner;
import VetClinicPOJOs.Pet;

public class XMLManagerImpl implements XMLManager {
	JDBCManager manager;
	OwnerManager ownermanager;
	PetManager petmanager;
	
	public XMLManagerImpl(JDBCManager m) {
		super();
		this.manager = m;
		this.ownermanager = new JDBCOwnerManager(manager);
		this.petmanager = new JDBCPetManager(manager);
	}

	@Override
	public void owner2xml(Integer id) {
		// TODO Auto-generated method stub
		Owner o = null;
		List<Pet> pets = new ArrayList<Pet>();
		
		
		try {
			//Do a sql query to get the owner by the id
			o = ownermanager.searchOwnerById(id);
			//search for the pets of the owner
			pets = petmanager.getPetsOfanOwner(id);
			System.out.println(pets);
			o.setPets(pets);
			
			//export the owner to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Owner.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Owner.xml");
			marshaller.marshal(o, file);
			System.out.print(o);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Pet xml2Pet(File xml) {
		// TODO Auto-generated method stub
		Pet p =null;
			
		try {
		//read pet from xml file
		JAXBContext jaxbContext = JAXBContext.newInstance(Pet.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		p = (Pet) unmarshaller.unmarshal(xml);
		Owner o = null;
		o = ownermanager.searchOwnerByEmail(p.getOwner().getEmail());
		p.setOwner(o);
		petmanager.addPet(p);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	@Override
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
