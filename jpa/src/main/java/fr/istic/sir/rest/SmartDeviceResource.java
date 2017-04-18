package fr.istic.sir.rest;

import fr.istic.sir.resources.ElectronicDevice;
import fr.istic.sir.resources.Heater;
import fr.istic.sir.resources.Home;
import fr.istic.sir.resources.SmartDevice;
import fr.istic.sir.service.HomeService;
import fr.istic.sir.service.SmartDeviceService;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/devices")
public class SmartDeviceResource implements AwesomeResource<SmartDevice> {

  /**
   * Get all SmartDevices registered in db
   * @return SmartDevice list
   */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SmartDevice> getAll() {
		return new SmartDeviceService().findAll();
	}

	/**
	 * Get a smartDevice from its id
	 * @param id long
	 */
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SmartDevice get(@PathParam("id") long id) {
		return new SmartDeviceService().find(id);
	}

	/**
	 * Delete a smartDevice from its id
	 * @param id long
	 */
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		SmartDeviceService sds = new SmartDeviceService();
		sds.delete(sds.find(id));
	}

	/**
	 * Create a smartDevice.
   * Bind it with a home if it is a heater
	 * @param jsonResult Json
	 * @return Response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void add(String jsonResult) {
		//Gson won't work because both Heater and EletronicDevice are SmartDevice, but each requires a specific treatment
		JSONObject json = new JSONObject(jsonResult);
		if (json.getString("deviceType").equals("heater")) {
			Heater device = new Heater(json.getString("name"), json.getInt("temperatureMin"), json.getInt("temperatureMax"));
			device.setAvgCons(json.getInt("avgcons"));
			new SmartDeviceService().add(device);
			HomeService hs = new HomeService();
			Home h = hs.find(json.getLong("homeid"));
			h.addHeater(device);
			hs.update(h);
		} else {
			ElectronicDevice device = new ElectronicDevice(json.getString("name"), json.getString("type"));
			device.setAvgCons(json.getInt("avgcons"));
			new SmartDeviceService().add(device);
		}
	}
}
