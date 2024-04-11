package VetClinicJDBC;

import java.sql.PreparedStatement;

import VetClinicIfaces.OwnerManager;
import VetClinicPOJOs.Owner;

public class JDBCOwnerManager implements OwnerManager{
	
	private JDBCManager manager;
	
	public JDBCOwnerManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void createOwner(Owner o) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO owners (name,"
					+ "phone, email, cardnumber)"
					+ "VALUES (?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, o.getName());
			prep.setInt(2, o.getPhone());
			prep.setString(3, o.getEmail());
			prep.setInt(4, o.getCardnumber());
			
			prep.executeUpdate();				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

}
