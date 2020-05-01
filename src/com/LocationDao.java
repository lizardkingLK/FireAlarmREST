package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {
	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	
	public static List<Location> getLocations() {
		List<Location> list = new ArrayList<>();
		
		try {
			con = ConnectFA.connect();
			String queryA = "SELECT * FROM Location;";
			ps = con.prepareStatement(queryA);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Location a = new Location();
				
				a.setLid(rs.getString(1));
				a.setFloorNo(rs.getString(2));
				a.setRoomNo(rs.getString(3));
				
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
	
	public static Location getLocation(String lid) {
		Location l = null;
		
		try {
			con = ConnectFA.connect();
			String queryA = "SELECT * FROM Location WHERE lid = ? ;";
			ps = con.prepareStatement(queryA);
			ps.setString(1, lid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				l = new Location();
				
				l.setLid(rs.getString(1));
				l.setFloorNo(rs.getString(2));
				l.setRoomNo(rs.getString(3));
			}
			
			return l;
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
	
	public static boolean addLocation(Location l) {
		try {
			con = ConnectFA.connect();
			String queryA = "INSERT INTO Location (lid,floorNo,roomNo) VALUES (?,?,?) ;";
			ps = con.prepareStatement(queryA);
			ps.setString(1, l.getLid());
			ps.setString(2, l.getFloorNo());
			ps.setString(3, l.getRoomNo());
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
	
	public static boolean updateLocation(Location l) {
		String f = l.getFloorNo();
		String r = l.getRoomNo();
		String fr = f+r;
		
		try {
			con = ConnectFA.connect();
			String queryA = "UPDATE Location SET lid = ?,floorNo = ?,roomNo = ? WHERE lid = ? ;";
			ps = con.prepareStatement(queryA);
			ps.setString(1, fr);
			ps.setString(2, f);
			ps.setString(3, r);
			ps.setString(4, l.getLid());
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
