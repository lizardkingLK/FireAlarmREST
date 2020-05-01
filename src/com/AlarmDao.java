package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlarmDao {
	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	
	public static List<Alarm> getAlarms() {
		List<Alarm> list = new ArrayList<>();
		
		try {
			con = ConnectFA.connect();
			String queryA = "SELECT * FROM Alarm ;";
			ps = con.prepareStatement(queryA);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Alarm a = new Alarm();
				
				a.setAid(rs.getString(1));
				a.setSmokeLevel(rs.getInt(2));
				a.setCo2Level(rs.getInt(3));
				a.setIsActive(rs.getInt(4));
				a.setIsWorking(rs.getInt(5));
				a.setEmail(rs.getString(6));
				a.setLid(rs.getString(7));
				
				list.add(a);
			}
			
			return list;
		}
		catch(SQLException sqle) {
			System.out.println(sqle);
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(ps != null) try { ps.close(); } catch(Exception e) {}
            if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return null;
	}
	
	public static Alarm getAlarm(String aid) {
		Alarm a = null;
		
		try {
			con = ConnectFA.connect();
			String queryA = "SELECT * FROM Alarm WHERE aid = ? ;";
			ps = con.prepareStatement(queryA);
			ps.setString(1, aid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				a = new Alarm();
				
				a.setAid(rs.getString(1));
				a.setSmokeLevel(rs.getInt(2));
				a.setCo2Level(rs.getInt(3));
				a.setIsActive(rs.getInt(4));
				a.setIsWorking(rs.getInt(5));
				a.setEmail(rs.getString(6));
				a.setLid(rs.getString(7));
			}
			
			return a;
		} 
		catch(SQLException sqle) {
			System.out.println(sqle);
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(ps != null) try { ps.close(); } catch(Exception e) {}
            if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return null;
	}
	
	public static String getLastAlarmId() {
		String a = null;
		
		try {
			con = ConnectFA.connect();
			String queryA = "SELECT MAX(aid) FROM Alarm ;";
			ps = con.prepareStatement(queryA);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				a = rs.getString(1);
			}
			
			return a;
		} 
		catch(SQLException sqle) {
			System.out.println(sqle);
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(ps != null) try { ps.close(); } catch(Exception e) {}
            if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return null;
	}
	
	public static boolean addAlarm(Alarm a) {
		try {
			con = ConnectFA.connect();
			String queryA = "INSERT INTO Alarm (aid,smokeLevel,CO2Level,isActive,isWorking,email,lid) VALUES (?,?,?,?,?,?,?) ;";
			ps = con.prepareStatement(queryA);
			ps.setString(1, a.getAid());
			ps.setInt(2, a.getSmokeLevel());
			ps.setInt(3, a.getCo2Level());
			ps.setInt(4, a.getIsActive());
			ps.setInt(5, a.getIsWorking());
			ps.setString(6, a.getEmail());
			ps.setString(7, a.getLid());
			int count = ps.executeUpdate();
			if(count > 0)
				return true;
			else
				return false;
		}
		catch(SQLException sqle) {
			System.out.println(sqle);
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(ps != null) try { ps.close(); } catch(Exception e) {}
            if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return false;
	}

	public static boolean updateAlarm(Alarm a) {
		try {
			con = ConnectFA.connect();
			String queryA = "UPDATE Alarm SET smokeLevel = ?,CO2Level = ?,isActive = ?,isWorking = ?,email = ?,lid = ? WHERE aid = ? ;";
			ps = con.prepareStatement(queryA);
			ps.setInt(1, a.getSmokeLevel());
			ps.setInt(2, a.getCo2Level());
			ps.setInt(3, a.getIsActive());
			ps.setInt(4, a.getIsWorking());
			ps.setString(5, a.getEmail());
			ps.setString(6, a.getLid());
			ps.setString(7, a.getAid());
			int count = ps.executeUpdate();
			
			if(count > 0)
				return true;
			else
				return false;
		}
		catch(SQLException sqle) {
			System.out.println(sqle);
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(ps != null) try { ps.close(); } catch(Exception e) {}
            if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return false;
	}

	public static boolean deleteAlarm(String aid) {
		try {
			con = ConnectFA.connect();
			String queryA = "UPDATE Alarm SET isWorking = ? WHERE aid = ? ;";
			ps = con.prepareStatement(queryA);
			ps.setInt(1, 0);
			ps.setString(2, aid);
			int count = ps.executeUpdate();
			
			if(count > 0)
				return true;
			else
				return false;
		}
		catch(SQLException sqle) {
			System.out.println(sqle);
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(ps != null) try { ps.close(); } catch(Exception e) {}
            if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return false;
	}
}
