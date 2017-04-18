package fr.istic.sir.service;

import fr.istic.sir.resources.Home;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class HomeService implements AwesomeService<Home> {

  private EntityManager manager;

  /**
   * Init the entityManager
   */
  public HomeService() {
    this.manager = Persistence.createEntityManagerFactory("mysql").createEntityManager();
  }

  /**
   * Get a home by an ID
   *
   * @param id long
   * @return Home
   */
  public Home find(long id) {
    return manager.find(Home.class, id);
  }

  public List<Home> findAll() {
    return manager.createQuery("SELECT h FROM Home h", Home.class).getResultList();
  }

  /**
   * Add a Home
   *
   * @param home Home
   */
  public Home add(Home home) {
    manager.getTransaction().begin();
    manager.persist(home);
    manager.getTransaction().commit();
    return home;
  }

  public Home update(Home home) {
    manager.getTransaction().begin();
    manager.merge(home);
    manager.getTransaction().commit();
    return home;
  }

  /**
   * Delete a Home from database
   *
   * @param home Home
   */
  public void delete(Home home) {
    manager.getTransaction().begin();
    manager.remove(home);
    manager.getTransaction().commit();
  }
}
