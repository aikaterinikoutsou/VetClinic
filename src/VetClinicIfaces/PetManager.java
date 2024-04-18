package VetClinicIfaces;

import java.util.List;

import VetClinicPOJOs.Pet;

public interface PetManager {
	
	public void addPet(Pet p);
	public List<Pet> getPetsOfanOwner(Integer owner_id);
	public void assingPet2Vet(Integer pet_id, Integer vet_id);
}
