package VetClinicJDBC;

import java.sql.PreparedStatement;

import VetClinicIfaces.VetManager;
import VetClinicPOJOs.Vet;

public class JDBCVetManager implements VetManager {
	private JDBCManager manager;
	
	public JDBCVetManager(JDBCManager m)
	{
		this.manager = m;
	}

	@Override
	public void createVet(Vet v) {
		
		try {
			String sql = "INSERT INTO vets (name, specialty, phone, email, licence)"
					+"VALUES(?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,v.getName());
			prep.setString(2, v.getSpecialty());
			prep.setInt(3, v.getPhone());
			prep.setString(4, v.getEmail());
			prep.setString(5, v.getLicense());
			prep.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateSpeciality(Integer vet_id, String n_speciality) {
		
		
		try {
			String sql = "UPDATE vets SET specialty= ? WHERE id= ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, n_speciality);
			prep.setInt(2, vet_id);
			
			prep.executeQuery();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}		
	}

	@Override
	public void deleteVetbyID(Integer vet_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM vets WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, vet_id);
			
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
