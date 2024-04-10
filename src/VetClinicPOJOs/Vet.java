package VetClinicPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vet implements Serializable {

	private static final long serialVersionUID = -3745538070135973596L;
	
	private Integer id;
	private String name;
	private String specialty;
	private String license;
	private Integer phone;
	private String email;
	private List<Pet> pets;
	
	public Vet() {
		super();
		pets = new ArrayList<Pet>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, id, license, name, pets, phone, specialty);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vet other = (Vet) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(license, other.license) && Objects.equals(name, other.name)
				&& Objects.equals(pets, other.pets) && Objects.equals(phone, other.phone)
				&& Objects.equals(specialty, other.specialty);
	}
	@Override
	public String toString() {
		return "Vet [id=" + id + ", name=" + name + ", specialty=" + specialty + ", license=" + license + ", phone="
				+ phone + ", email=" + email + ", pets=" + pets + "]";
	}
	
	
	
}
