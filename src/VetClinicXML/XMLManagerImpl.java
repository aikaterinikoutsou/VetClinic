package VetClinicXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

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
	
	@Override
	public void owner2xml(Integer id) {
		// TODO Auto-generated method stub
		Owner o = null;
		List<Pet> pets = new ArrayList<Pet>();
		manager = new JDBCManager();
		ownermanager = new JDBCOwnerManager(manager);
		petmanager = new JDBCPetManager(manager);
		
		try {
			//Do a sql query to get the owner by the id
			o = ownermanager.searchOwnerById(id);
			//search for the pets of the owner
			pets = petmanager.getPetsOfanOwner(id);
			o.setPets(pets);
			
			//export the owner to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Owner.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Owner.xml");
			marshaller.marshal(o, file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Pet xml2Pet(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

}
