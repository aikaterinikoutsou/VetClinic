package VetClinicIfaces;

import java.io.File;

import VetClinicPOJOs.Pet;

public interface XMLManager {

	public void owner2xml(Integer id);
	public Pet xml2Pet(File xml);
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir);

}
