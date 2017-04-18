package fr.istic.sir.resources;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Person {

	private long id;

	private String lastName;

	private String firstName;

	private List<Home> homes = new ArrayList<Home>();

	private List<Person> friends = new ArrayList<Person>();

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(){}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@OneToMany(mappedBy="owner", cascade = CascadeType.PERSIST)
	@JsonManagedReference("person-home")
	public List<Home> getHomes() {
		return homes;
	}

	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}

	public void addHome(Home home) {
		this.homes.add(home);
	}

	@ManyToMany
  @JoinTable(name = "Friends")
	@JsonIgnore
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
}
