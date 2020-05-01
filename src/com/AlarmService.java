package com;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/AlarmService")
public class AlarmService {
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String Hello() {
		return "AlarmService ONLINE...";
	}
	
	@GET
	@Path("/getAlarms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alarm> getAlarms() {
		List<Alarm> list = AlarmDao.getAlarms();
		
		return list;
	}
	
	@GET
	@Path("/getAlarm/{aid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Alarm getAlarm(@PathParam("aid") String aid) {
		Alarm alarm = AlarmDao.getAlarm(aid);
		
		return alarm;
	}
	
	@GET
	@Path("/getNewAlarmId")
	@Produces(MediaType.APPLICATION_JSON)
	public Alarm getNewAlarmId() {
		Alarm a = new Alarm();
		String lastId = AlarmDao.getLastAlarmId();
		String newId = IdentityFA.getID("Alarm", lastId);
		a.setAid(newId);
		
		return a;
	}
	
	@POST
	@Path("/addAlarm")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAlarm(Alarm a) {
		boolean state = AlarmDao.addAlarm(a);
		if(state) {
			Alarm newAlarm = AlarmDao.getAlarm(a.getAid());
			String success = "Alarm added successfully \n"+newAlarm;
			return Response.status(201).entity(success).build();
		}
		else {
			Alarm newAlarm = null;
			String failure= "Add alarm failed \nCheck your aid,lid and email \n"+newAlarm;
			return Response.status(400).entity(failure).build();
		}
	}
	
	@PUT
	@Path("/updateAlarm")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAlarm(Alarm a) {
		boolean state = AlarmDao.updateAlarm(a);
		if(state) {
			Alarm newAlarm = AlarmDao.getAlarm(a.getAid());
			String success = "Alarm updated successfully \n"+newAlarm;
			return Response.status(201).entity(success).build();
		}
		else {
			Alarm newAlarm = null;
			String failure= "Update alarm failed \nCheck your email and lid \n"+newAlarm;
			return Response.status(400).entity(failure).build();
		}
	}
	
	@DELETE
	@Path("/deleteAlarm/{aid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAlarm(@PathParam("aid") String aid) {
		boolean state = AlarmDao.deleteAlarm(aid);
		if(state) {
			String success = "Alarm "+aid+" deactivated successfully";
			return Response.status(201).entity(success).build();
		}
		else {
			String failure= "Deactivate alarm "+aid+" failed \nCheck your aid";
			return Response.status(400).entity(failure).build();
		}
	}
}
