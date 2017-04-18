package fr.istic.sir.dao;

import fr.istic.sir.resources.Heater;
import fr.istic.sir.resources.Home;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class HomeService {

	private EntityManager manager;

	/**
	 * Init the entityManager
	 */
	public HomeService() {
		this.manager = Persistence.createEntityManagerFactory("mysql").createEntityManager();
	}

	private String findById = "SELECT h FROM Home h WHERE id = :id";
	private String findAll = "SELECT h FROM Home h";

	/**
	 * Get a home by an ID
	 * @param id
	 * @return Home
	 */
	public Home getById(long id) {
		try {
			Home home = manager.createQuery(findById, Home.class)
				.setParameter("id",id).getSingleResult();
			return home;
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Return All the Home recorded in the DB
	 * @return List<Home>
	 */
	public List<Home> getAll() {
		try {
			List<Home> lh = manager.createQuery(findAll, Home.class).getResultList();
			return lh;
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Delete a Home by this ID
	 * @param id long
	 */
	public void delete(long id) {
		manager.getTransaction().begin();
		manager.remove(manager.find(Home.class, id));
		manager.getTransaction().commit();
		manager.close();
	}

	/**
	 * Add a Home
	 * @param home Home
	 */

	public void add(Home home) {
		manager.getTransaction().begin();
		manager.persist(home);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
	}

	/**
	 * Add a heater to a home
	 * @param id
	 * @param heater
	 */
	public void addHeater(long id, Heater heater) {
		Home home = this.getById(id);
		manager.getTransaction().begin();
		home.addHeater(heater);
		manager.getTransaction().commit();
		manager.close();
	}

}
