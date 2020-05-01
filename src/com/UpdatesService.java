package com;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/UpdatesService")
public class UpdatesService {
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String Hello() {
		return "UpdatesService ONLINE...";
	}
	
	@GET
	@Path("/getUpdates")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Updates> getUpdates() {
		List<Updates> list = UpdatesDao.getUpdates();
		
		return list;
	}
}
