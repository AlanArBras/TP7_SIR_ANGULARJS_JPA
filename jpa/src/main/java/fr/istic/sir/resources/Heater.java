package fr.istic.sir.resources;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Heater extends SmartDevice {

  private int temperatureMin;

  private int temperatureMax;

  private Home home;

  public Heater() {
  }

  public Heater(String name, int tempMin, int tempMax) {
    super(name);
    this.temperatureMin = tempMin;
    this.temperatureMax = tempMax;
  }

  public int getTemperatureMin() {
    return temperatureMin;
  }

  public void setTemperatureMin(int temperatureMin) {
    this.temperatureMin = temperatureMin;
  }

  public int getTemperatureMax() {
    return temperatureMax;
  }

  public void setTemperatureMax(int temperatureMax) {
    this.temperatureMax = temperatureMax;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonBackReference("heater-home")
  public Home getHome() {
    return home;
  }

  public void setHome(Home home) {
    this.home = home;
  }
}
