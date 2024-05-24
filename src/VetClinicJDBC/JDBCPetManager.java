package VetClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import VetClinicIfaces.OwnerManager;
import VetClinicIfaces.PetManager;
import VetClinicPOJOs.Owner;
import VetClinicPOJOs.Pet;

public class JDBCPetManager implements PetManager{

	private JDBCManager manager;
	private OwnerManager ownermanager;
	
	public JDBCPetManager(JDBCManager m)
	{
		this.manager = m;
		this.ownermanager = new JDBCOwnerManager(manager);
	}
	
	@Override
	public void addPet(Pet p) {
		// TODO Auto-generated method stub
		try {
		 String sql = "INSERT INTO pets (name, cured, typeOfAnimal, dob, coat, onwer_id)"
				 + "VALUES(?,?,?,?,?,?)";
		 
		 PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		 
		 prep.setString(1, p.getName());
		 prep.setBoolean(2, p.getCured());
		 prep.setString(3, p.getTypeofAnimal());
		 prep.setDate(4, p.getDob());
		 prep.setString(5, p.getCoat());
		 prep.setInt(6, p.getOwner().getId());
			
		 prep.executeUpdate();
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public List<Pet> getPetsOfanOwner(Integer owner_id) {
		// TODO Auto-generated method stub
		
		List<Pet> pets = new ArrayList<Pet>();
		
		try {
			
			Owner o = ownermanager.searchOwnerById(owner_id);
			
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM pets WHERE onwer_id="+owner_id;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Boolean cured = rs.getBoolean("cured");
				String typeOfAnimal = rs.getString("typeOfAnimal");
				Date dob = rs.getDate("dob");
				String coat = rs.getString("coat");
						
				Pet p = new Pet(id, coat,  name,cured, typeOfAnimal, dob, o);
				pets.add(p);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return pets;
	}

	@Override
	public void assingPet2Vet(Integer pet_id, Integer vet_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO treats (vet_id, pet_id) VALUES (?,?);";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, vet_id);
			prep.setInt(2, pet_id);
			prep.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
