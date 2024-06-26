package VetClinicPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "owner")
@XmlType(propOrder = {"email", "phone","cardnumber", "pets"})
public class Owner implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8016658301900081451L;
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String email;
	@XmlElement
	private Integer phone;
	@XmlElement
	private Integer cardnumber;
	@XmlElement (name = "pet")
	@XmlElementWrapper(name = "pets")
	private List<Pet> pets;
	

	public Owner() {
		super();
		this.pets = new ArrayList<Pet>();
	}

	
	
	public Owner(String name, String email, Integer phone, Integer cardnumber) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.cardnumber = cardnumber;
		this.pets = new ArrayList<Pet>();
	}



	public Owner(Integer id, String name, String email, Integer phone, Integer cardnumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.cardnumber = cardnumber;
		this.pets = new ArrayList<Pet>();

	}



	public List<Pet> getPets() {
		return pets;
	}


	public void setPets(List<Pet> pets) {
		this.pets = pets;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getPhone() {
		return phone;
	}


	public void setPhone(Integer phone) {
		this.phone = phone;
	}


	public Integer getCardnumber() {
		return cardnumber;
	}


	public void setCardnumber(Integer cardnumber) {
		this.cardnumber = cardnumber;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cardnumber, email, id, name, pets, phone);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Owner other = (Owner) obj;
		return Objects.equals(cardnumber, other.cardnumber) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(pets, other.pets)
				&& Objects.equals(phone, other.phone);
	}


	@Override
	public String toString() {
		return "Owner [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", cardnumber="
				+ cardnumber + "]";
	}
	
	
}
