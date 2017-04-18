package fr.istic.sir.resources;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Home {

	private long id;

	private String address;

	private Person owner;

	private List<Heater> heaters = new ArrayList<Heater>();

	public Home(String address){
		this.address = address;
	}

	public Home(){}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne
	@JsonBackReference("person-home")
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@OneToMany(mappedBy="home", cascade = CascadeType.PERSIST)
	@JsonManagedReference("home-heater")
	public List<Heater> getHeaters() {
		return heaters;
	}

	public void setHeaters(List<Heater> heaters) {
		this.heaters = heaters;
	}

	public void addHeater(Heater heater) {
		this.heaters.add(heater);
	}
}
