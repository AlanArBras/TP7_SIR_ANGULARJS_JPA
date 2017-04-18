package fr.istic.sir.service;

import java.util.List;

public interface AwesomeService<T> {
  T find(long id);

  List<T> findAll();

  T add(T t);

  T update(T t);

  void delete(T t);
}
