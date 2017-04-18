package fr.istic.sir.resources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SmartDevice {

	private long id;

	private String name;

	private int avgCons;

	public SmartDevice(){}

	public SmartDevice(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvgCons() {
		return avgCons;
	}

	public void setAvgCons(int avgCons) {
		this.avgCons = avgCons;
	}
}
