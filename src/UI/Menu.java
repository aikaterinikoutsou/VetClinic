package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import VetClinicIfaces.OwnerManager;
import VetClinicIfaces.PetManager;
import VetClinicJDBC.JDBCManager;
import VetClinicJDBC.JDBCOwnerManager;
import VetClinicJDBC.JDBCPetManager;
import VetClinicPOJOs.Owner;
import VetClinicPOJOs.Pet;

public class Menu {

	private static JDBCManager jdbcmanager;
	private static OwnerManager ownermanager;
	private static PetManager petmanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		jdbcmanager = new JDBCManager();
		ownermanager = new JDBCOwnerManager(jdbcmanager); 
		petmanager = new JDBCPetManager(jdbcmanager);
		
		
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add a new owner.");
				System.out.println("2. Print all the owners in DB.");
				System.out.println("3.  Add a new pet in the DB");
				System.out.println("4.  Print all the pets of an owner.");
				System.out.println("0. Exit.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					createOwner();
					break;
				case 2:
					getAllowners();
				case 3:
					createPet();
				case 4:
					printOwnersPets();
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
	
	private static void createPet() throws Exception
	{
		System.out.println("Type the name of the pet");
		String name = reader.readLine();
		System.out.println("Type if it's cured or not");
		Boolean cured = Boolean.valueOf(reader.readLine());
		System.out.println("Type the type of animal(dog, cat, bird, hamster)");
		String typeOfAnimal = reader.readLine();
		System.out.println("Type the dob of the pet in formal yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dob = (Date) df.parse(dob_str);
		System.out.println("Type the coat of the pet");
		String coat = reader.readLine();
		System.out.println("Type the owner id of the pet");
		Integer owner_id = Integer.parseInt(reader.readLine());
		
		Owner o = ownermanager.searchOwnerById(owner_id);
		
		Pet p = new Pet(coat,  name,cured, typeOfAnimal, dob, o);
		petmanager.addPet(p);
		
	}
	private static void getAllowners() throws Exception{
		
		List<Owner> owners = null;
		
		owners = ownermanager.getListOfOwners();
		
		System.out.println(owners);
		
	}
	
	private static void printOwnersPets() throws Exception{
		
		List<Pet> pets = null;
		
		System.out.println("Type the id of the owner");
		Integer o_id = Integer.parseInt(reader.readLine());
		
		pets = petmanager.getPetsOfanOwner(o_id);
		
		System.out.println(pets);
		
	}
}
