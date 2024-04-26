package VetClinicIfaces;

import VetClinicPOJOs.User;

public interface UserManager {
	
	public User checkPassword(String email, String pass);

}
