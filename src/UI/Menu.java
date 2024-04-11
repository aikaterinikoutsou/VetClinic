package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import VetClinicIfaces.OwnerManager;
import VetClinicJDBC.JDBCManager;
import VetClinicJDBC.JDBCOwnerManager;
import VetClinicPOJOs.Owner;

public class Menu {

	private static JDBCManager jdbcmanager;
	private static OwnerManager ownermanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		jdbcmanager = new JDBCManager();
		ownermanager = new JDBCOwnerManager(jdbcmanager); 
		
		
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add a new owner.");
				System.out.println("0. Exit.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					createOwner();
					break;
				case 0:
					jdbcmanager.disconnect();
					System.exit(0);
					
				}
				
			}while(true);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
	private static void createOwner() throws Exception
	{
		System.out.println("Type the name of the owner");
		String name = reader.readLine();
		System.out.println("Type the phone of the owner");
		Integer phone = Integer.parseInt(reader.readLine());
		System.out.println("Type the cardnumber of the owner");
		Integer cardnumber = Integer.parseInt(reader.readLine());
		System.out.println("Type the email of the owner");
		String email = reader.readLine();
		
		Owner o = new Owner(name, email, phone, cardnumber);
		
		ownermanager.createOwner(o);
	}

}
