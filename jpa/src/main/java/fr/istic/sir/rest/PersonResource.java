package fr.istic.sir.rest;

import fr.istic.sir.resources.Home;
import fr.istic.sir.resources.Person;
import fr.istic.sir.service.PersonService;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/persons")
public class PersonResource implements AwesomeResource<Person> {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAll() {
		return new PersonService().findAll();
	}

	/**
	 * Get a person from its id
	 * @param id int
	 */
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Person get(@PathParam("id") long id) {
		return new PersonService().find(id);
	}

	/**
	 * Delete a person from its id
	 * @param id
	 */
	@DELETE
  @Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void delete(@PathParam("id")long id) {
		PersonService personService = new PersonService();
		personService.delete(personService.find(id));
	}

	/**
	 * Add a person and also can add a home and then bind them together
	 * @param jsonResult
	 * @return Response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void add(String jsonResult) {
		//Gson isn't the best way to make the JSON object because of the optionnal home object
		JSONObject personJson = new JSONObject(jsonResult);
		Person p = new Person(personJson.getString("firstName"),personJson.getString("lastName"));
		//homeCreate is set when the user checks the checkbox when adding a new person
		if (personJson.getBoolean("homeCreate")) {
			Home h = new Home(personJson.getString("homeAddress"));
			List<Home> homes = new ArrayList<Home>();
			homes.add(h);
			p.setHomes(homes);
			h.setOwner(p);
		}
		new PersonService().add(p);
	}
}


