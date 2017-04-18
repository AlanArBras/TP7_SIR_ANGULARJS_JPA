package fr.istic.sir.service;

import fr.istic.sir.resources.Person;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class PersonService implements AwesomeService<Person> {

  private EntityManager manager;

  /**
   * Init the entityManager
   */
  public PersonService() {
    this.manager = Persistence.createEntityManagerFactory("mysql").createEntityManager();
  }

  /**
   * Find a Person by an ID
   *
   * @param id
   * @return SmartDevice
   */
  public Person find(long id) {
    return manager.find(Person.class, id);
  }


  public List<Person> findAll() {

    return manager.createQuery("Select p FROM Person p", Person.class).getResultList();
  }

  /**
   * Add one person in the db
   *
   * @param person Person
   */
  public Person add(Person person) {
    manager.getTransaction().begin();
    manager.persist(person);
    manager.getTransaction().commit();
    return person;
  }

  public Person update(Person person) {
    manager.getTransaction().begin();
    manager.merge(person);
    manager.getTransaction().commit();
    return person;
  }

  /**
   * Delete a person from database
   *
   * @param person Person
   */
  public void delete(Person person) {
    manager.getTransaction().begin();
    manager.remove(person);
    manager.getTransaction().commit();
  }
}
