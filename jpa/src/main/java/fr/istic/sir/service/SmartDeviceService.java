package fr.istic.sir.service;

import fr.istic.sir.resources.SmartDevice;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class SmartDeviceService implements AwesomeService<SmartDevice> {
	private EntityManager manager;

	/**
	 * Init the entityManager
	 */
	public SmartDeviceService() {
		this.manager = Persistence.createEntityManagerFactory("mysql").createEntityManager();
	}

	/**
	 * Find a SmartDevice by an ID
	 * @param id
	 * @return SmartDevice
	 */
	public SmartDevice find(long id) {
		return manager.find(SmartDevice.class, id);
	}

	/**
	 * Delete a SmartDevice
	 */
	public void delete(SmartDevice device) {
		manager.getTransaction().begin();
		manager.remove(device);
		manager.getTransaction().commit();
	}

	/**
	 * Add a SmartDevice
	 * @param device SmartDevice
	 */
	public SmartDevice add(SmartDevice device) {
		manager.getTransaction().begin();
		manager.persist(device);
		manager.getTransaction().commit();
		return device;
	}

	public SmartDevice update(SmartDevice device) {
		manager.getTransaction().begin();
		manager.merge(device);
		manager.getTransaction().commit();
		return device;
	}

	public List<SmartDevice> findAll() {
		return manager.createQuery("SELECT s FROM SmartDevice s", SmartDevice.class).getResultList();
	}

}
