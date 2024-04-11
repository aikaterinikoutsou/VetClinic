package VetClinicIfaces;

import java.util.List;

import VetClinicPOJOs.Owner;

public interface OwnerManager {

	public void createOwner(Owner o);
	public List<Owner> getListOfOwners();
}
