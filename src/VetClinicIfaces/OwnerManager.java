package VetClinicIfaces;

import java.util.List;

import VetClinicPOJOs.Owner;

public interface OwnerManager {

	public void createOwner(Owner o);
	public List<Owner> getListOfOwners();
	public Owner searchOwnerById(Integer id);
	public Owner searchOwnerByEmail(String email);

}
