package com;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/LocationService")
public class LocationService {
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String Hello() {
		return "LocationService ONLINE...";
	}
	
	@GET
	@Path("/getLocations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> getLocations() {
		List<Location> list = LocationDao.getLocations();
		
		return list;
	}
	
	@GET
	@Path("/getLocation/{lid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Location getLocation(@PathParam("lid") String lid) {
		Location location = LocationDao.getLocation(lid);
		
		return location;
	}
	
	@POST
	@Path("/addLocation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLocation(Location l) {
		boolean state = LocationDao.addLocation(l);
		if(state) {
			Location newLocation = LocationDao.getLocation(l.getLid());
			String success = "Location added successfully \n"+newLocation;
			return Response.status(201).entity(success).build();
		}
		else {
			Alarm newLocation = null;
			String failure= "Add location failed \nCheck your lid \n"+newLocation;
			return Response.status(400).entity(failure).build();
		}
	}
	
	@PUT
	@Path("/updateLocation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLocation(Location l) {
		boolean state = LocationDao.updateLocation(l);
		if(state) {
			Location newLocation = LocationDao.getLocation(l.getFloorNo()+l.getRoomNo());
			String success = "Location updated successfully \n"+newLocation;
			return Response.status(201).entity(success).build();
		}
		else {
			Alarm newLocation = null;
			String failure= "Update location failed \nCheck your lid \n"+newLocation;
			return Response.status(400).entity(failure).build();
		}
	}
}
