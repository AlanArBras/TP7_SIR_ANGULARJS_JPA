package fr.istic.sir.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.sir.resources.ElectronicDevice;
import fr.istic.sir.resources.Heater;
import fr.istic.sir.resources.Home;
import fr.istic.sir.resources.Person;
import fr.istic.sir.resources.SmartDevice;

public class JpaTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    EntityManager em = Persistence.createEntityManagerFactory("mysql").createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Home home1 = new Home("3 rue Foye-Seth, Triso-les-bains ");
    Home home2 = new Home("Lieu-dit Jeussu Ithéo");
    Home home3 = new Home("2 rue Bilbo-Quais, Mulhouse");
    Home home4 = new Home("14 Digue-Du-Fût, Montaigu");
    Person person1 = new Person("Alan", "Huitel");
    Person person2 = new Person("Gwendal", "Denoual");
    Heater heater1 = new Heater("firstHeater", 5, 30);
    heater1.setAvgCons(1000);
    Heater heater2 = new Heater("secondHeater", 6, 20);
    heater2.setAvgCons(1100);
    Heater heater3 = new Heater("thirdHeater", 10, 40);
    heater3.setAvgCons(950);
    ElectronicDevice electronicDevice1 = new ElectronicDevice("firstDevice", "dishwasher");
    electronicDevice1.setAvgCons(30);
    ElectronicDevice electronicDevice2 = new ElectronicDevice("secondDevice", "microwave");
    electronicDevice2.setAvgCons(120);

    List<Home> homes1 = new ArrayList<Home>();
    List<Home> homes2 = new ArrayList<Home>();
    List<Person> friends = new ArrayList<Person>();
    List<SmartDevice> devices1 = new ArrayList<SmartDevice>();
    List<SmartDevice> devices2 = new ArrayList<SmartDevice>();

    homes1.add(home1);
    homes1.add(home2);
    homes1.add(home3);
    homes2.add(home4);
    friends.add(person2);
    devices1.add(heater1);
    devices1.add(electronicDevice2);
    devices2.add(electronicDevice1);
    devices2.add(heater2);
    devices2.add(heater3);

    person1.setHomes(homes1);
    person2.setHomes(homes2);
    home1.setOwner(person1);
    home2.setOwner(person1);
    home3.setOwner(person1);
    home4.setOwner(person2);
    person1.setFriends(friends);
    addHeaters(devices1, home1);
    addHeaters(devices2, home4);

    em.persist(person1);
    em.persist(person2);
    em.persist(heater1);
    em.persist(heater2);
    em.persist(heater3);
    em.persist(electronicDevice1);
    em.persist(electronicDevice2);

    tx.commit();

    List<Home> homesResult = em.createQuery("SELECT h FROM Home h", Home.class).getResultList();
    em.close();
    System.out.println("Number of created homes : " + homesResult.size());
    for (Home next : homesResult) {
      System.out.println("address : " + next.getAddress());
    }
    System.out.println(".. done");
  }

  /**
   * Add all heaters from a SmartDevice list to a given home
   * @param devices
   * @param home
   */
  private static void addHeaters(List<SmartDevice> devices, Home home){
    for (SmartDevice device : devices) {
      if(device instanceof Heater) {
        home.addHeater((Heater) device);
        ((Heater) device).setHome(home);
      }
    }
  }

}
