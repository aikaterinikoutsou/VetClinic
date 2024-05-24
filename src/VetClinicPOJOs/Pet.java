package VetClinicPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import VetClinicXMLutils.SQLDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Pet")
@XmlType(propOrder = {"typeofAnimal", "cured", "coat", "dob", "owner"})
public class Pet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264165912558346853L;

	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement 
	private String coat;
	@XmlElement
	private Boolean cured;
	@XmlElement
	private String typeofAnimal;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlElement
	private Owner owner;
	@XmlTransient
	private Byte[] foto;
	@XmlTransient
	private List<Vet> vets;
	
	public Pet() {
		super();
		vets = new ArrayList<Vet>();
	}
	
	public Pet(Integer id, String coat, String name, Boolean cured, String typeofAnimal, Date dob, Owner owner) {
		super();
		this.id = id;
		this.coat = coat;
		this.name = name;
		this.cured = cured;
		this.typeofAnimal = typeofAnimal;
		this.dob = dob;
		this.owner = owner;
	}

	public Pet(String coat, String name, Boolean cured, String typeofAnimal, Date dob, Owner owner) {
		super();
		this.coat = coat;
		this.name = name;
		this.cured = cured;
		this.typeofAnimal = typeofAnimal;
		this.dob = dob;
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public Byte[] getFoto() {
		return foto;
	}

	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}

	public List<Vet> getVets() {
		return vets;
	}
	public void setVets(List<Vet> vets) {
		this.vets = vets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coat, cured, dob, foto, id, name, owner, typeofAnimal, vets);
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
		return Objects.equals(coat, other.coat) && Objects.equals(cured, other.cured) && Objects.equals(dob, other.dob)
				&& Objects.equals(foto, other.foto) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(owner, other.owner) && Objects.equals(typeofAnimal, other.typeofAnimal)
				&& Objects.equals(vets, other.vets);
	}
	
	@Override
	public String toString() {
		return "Pet [id=" + id + ", coat=" + coat + ", name=" + name + ", cured=" + cured
				+ ", typeofAnimal=" + typeofAnimal + ", dob=" + dob + ", owner=" + owner + "]";
	}

}
