package VetClinicPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Pet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264165912558346853L;

	private Integer id;
	private String breed;
	private String coat;
	private String name;
	private Boolean cured;
	private String typeofAnimal;
	private Date dob;
	private Owner owner;
	
	public Pet() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getCoat() {
		return coat;
	}
	public void setCoat(String coat) {
		this.coat = coat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getCured() {
		return cured;
	}
	public void setCured(Boolean cured) {
		this.cured = cured;
	}
	public String getTypeofAnimal() {
		return typeofAnimal;
	}
	public void setTypeofAnimal(String typeofAnimal) {
		this.typeofAnimal = typeofAnimal;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	@Override
	public int hashCode() {
		return Objects.hash(breed, coat, cured, dob, id, name, owner, typeofAnimal);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return Objects.equals(breed, other.breed) && Objects.equals(coat, other.coat)
				&& Objects.equals(cured, other.cured) && Objects.equals(dob, other.dob) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(owner, other.owner)
				&& Objects.equals(typeofAnimal, other.typeofAnimal);
	}
	
	@Override
	public String toString() {
		return "Pet [id=" + id + ", breed=" + breed + ", coat=" + coat + ", name=" + name + ", cured=" + cured
				+ ", typeofAnimal=" + typeofAnimal + ", dob=" + dob + ", owner=" + owner + "]";
	}

}
