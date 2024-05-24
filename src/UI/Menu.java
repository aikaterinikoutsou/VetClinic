package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import VetClinicIfaces.OwnerManager;
import VetClinicIfaces.PetManager;
import VetClinicIfaces.UserManager;
import VetClinicIfaces.XMLManager;
import VetClinicJDBC.JDBCManager;
import VetClinicJDBC.JDBCOwnerManager;
import VetClinicJDBC.JDBCPetManager;
import VetClinicJPA.JPAUserManager;
import VetClinicPOJOs.Owner;
import VetClinicPOJOs.Pet;
import VetClinicPOJOs.Role;
import VetClinicPOJOs.User;
import VetClinicXML.XMLManagerImpl;

public class Menu {

	private static JDBCManager jdbcmanager;
	private static OwnerManager ownermanager;
	private static PetManager petmanager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	private static UserManager usermanager;
	private static XMLManager xmlmanager;
	
	public static void main(String[] args) {
		
		jdbcmanager = new JDBCManager();
		ownermanager = new JDBCOwnerManager(jdbcmanager); 
		petmanager = new JDBCPetManager(jdbcmanager);
		usermanager = new JPAUserManager();
		xmlmanager = new XMLManagerImpl(jdbcmanager);
		
		try {
			int choice;
			
			do {
				
				System.out.println("Choose an option");
				System.out.println("1. Login User");
				System.out.println("2. Sign-up new user");
				System.out.println("3. Update password");
				System.out.println("0. Exit.");
								
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					login();
					break;
				case 2:
					System.out.println("Add info of new user.");
					signUpUser();
					break;
				case 3: 
					System.out.println("Update the password of an exissting user.");
					updatePassword();
					break;
				case 0:
					System.out.println("Exiting application.");
					jdbcmanager.disconnect();
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
	
	private static void updatePassword() throws Exception {
		
		System.out.println("Email: ");
		String email = reader.readLine();
				
		System.out.println("Enter current Password");
		String passwd = reader.readLine();
		
		System.out.println("Enter new Password");
		String new_passwd = reader.readLine();
				
		User u = usermanager.checkPassword(email, passwd);
				
		if(u!=null)
		{
			System.out.println("Login of owner successful!");
			usermanager.changePassword(u, new_passwd);
		}
				
	}

	private static void login() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Email: ");
		String email = reader.readLine();
		
		System.out.println("Password");
		String passwd = reader.readLine();
		
		User u = usermanager.checkPassword(email, passwd);
		
		if(u!=null & u.getRole().getName().equals("owner"))
		{
			System.out.println("Login of owner successful!");
			//call for owner submenu;
			ownerMenu(u.getId());
		}
		
	}

	private static void ownerMenu(Integer id) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add a new owner.");
				System.out.println("2. Print all the owners in DB.");
				System.out.println("3. Add a new pet in the DB");
				System.out.println("4. Print all the pets of an owner.");
				System.out.println("5. Print me to xml.");
				System.out.println("6. Load Pets from xml File");
				System.out.println("0. Return.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					createOwner();
					break;
				case 2:
					getAllowners();
					break;
				case 3:
					createPet();
					break;
				case 4:
					printOwnersPets();
					break;
				case 5:
					printMe(id);
					break;
				case 6:
					loadPets();
					break;
				case 0:
					System.out.println("Back to main menu");
					
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}

	private static void loadPets() {
		// TODO Auto-generated method stub
		Pet p = null;
		File file = new File("./xmls/External-Pet.xml");
		p = xmlmanager.xml2Pet(file);
		
		System.out.print(p);
	}

	private static void printMe(Integer id) {
		// TODO Auto-generated method stub
		xmlmanager.owner2xml(id);
		xmlmanager.simpleTransform("./xmls/Owner.xml", "./xmls/owner-style.xslt", "./xmls/owner.html");
	}

	private static void signUpUser() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			System.out.println("Introduce the password");
			String password = reader.readLine();
			
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			
			System.out.println("Introduce the role of the user. 1: owner, 2: vet");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);
			
			User u = new User(email, pass, r);
			
			usermanager.newUser(u);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			}
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
