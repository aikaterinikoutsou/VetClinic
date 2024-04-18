package VetClinicIfaces;

import VetClinicPOJOs.Vet;

public interface VetManager {
	
	public void createVet(Vet v);
	public void updateSpeciality(Integer vet_id, String n_speciality);
	public void deleteVetbyID(Integer vet_id);

}
