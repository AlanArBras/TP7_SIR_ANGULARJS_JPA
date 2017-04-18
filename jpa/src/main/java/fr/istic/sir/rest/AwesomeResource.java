package fr.istic.sir.rest;

import java.util.List;

public interface AwesomeResource<T> {
	List<T> getAll();
	T get(long id);
	void delete(long id);
	void add(String jsonResult);
}
