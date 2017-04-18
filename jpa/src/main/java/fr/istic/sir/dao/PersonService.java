package fr.istic.sir.dao;

import fr.istic.sir.resources.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class PersonService {

	private EntityManager manager;

	/**
	 * Init the entityManager
	 */
	public PersonService() {
		this.manager = Persistence.createEntityManagerFactory("mysql").createEntityManager();
	}

	private String findById = "SELECT p FROM Person p WHERE id = :id";
	private String findAll = "SELECT p FROM Person p";

	/**
	 * Get a Person by an ID
	 * @param id
	 * @return SmartDevice
	 */
	public Person getById(long id) {
		try {
			Person person = manager.createQuery(findById, Person.class)
				.setParameter("id",id).getSingleResult();
			return person;
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Get all Person
	 * @return List<Person>
	 */
	public List<Person> getAll() {
		try {
			List<Person> lp = manager.createQuery(findAll, Person.class).getResultList();
			return lp;
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Delete a person from its id
	 * @param id long
	 */
	public void delete(long id) {
		manager.getTransaction().begin();
		manager.remove(manager.find(Person.class, id));
		manager.getTransaction().commit();
		manager.close();
	}

	/**
	 * Add a person into the db
	 * @param person Person
	 */
	public void add(Person person) {
		manager.getTransaction().begin();
		manager.persist(person);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
	}
}
