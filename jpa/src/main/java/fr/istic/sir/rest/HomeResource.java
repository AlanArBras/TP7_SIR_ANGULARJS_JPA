package fr.istic.sir.rest;

import com.google.gson.Gson;
import fr.istic.sir.resources.Home;
import fr.istic.sir.service.HomeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/homes")
public class HomeResource implements AwesomeResource<Home> {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> getAll() {
		return new HomeService().findAll();
	}

	/**
	 * Get a home from this id encode in json
	 * @param id long
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home get(@PathParam("id")long id) {
		return new HomeService().find(id);
	}

	/**
	 * Delete a home from this id encode in json
	 * @param id
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void delete(@PathParam("id")long id) {
		HomeService homeService = new HomeService();
		homeService.delete(homeService.find(id));
	}

	/**
	 * Add a house
	 * @param jsonResult
	 * @return Response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void add(String jsonResult) {
		// Gson is a Java library that can be used to convert Java Objects into their JSON representation
    // https://sites.google.com/site/gson/gson-user-guide
		Gson gson = new Gson();
		Home home = gson.fromJson(jsonResult, Home.class);
		new HomeService().add(home);
	}
}
