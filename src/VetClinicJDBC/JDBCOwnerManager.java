package VetClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Owner> getListOfOwners() {
		// TODO Auto-generated method stub
		List<Owner> owners= new ArrayList<Owner>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM owners";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
				Integer cardnumber = rs.getInt("cardnumber");
				
				Owner o = new Owner (id, name, email, phone, cardnumber);
				owners.add(o);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}
		
		
		return null;
	}
	

}
