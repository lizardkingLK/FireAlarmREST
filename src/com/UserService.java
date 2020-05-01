package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/UserService")
public class UserService {
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String Hello() {
		return "UserService ONLINE...";
	}
	
	@POST
	@Path("/authenticateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(User postUser) {
		User user = UserDao.authenticateUser(postUser.getEmail(), postUser.getPassword());
		if(user != null) {
			String success = "User authenticated \n"+user;
			return Response.status(201).entity(success).build();
		}
		else {
			String failure = "User authentication failed \n"+user;
			return Response.status(403).entity(failure).build();
		}
	}
}
