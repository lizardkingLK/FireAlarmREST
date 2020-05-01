package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdatesDao {
	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	
	public static List<Updates> getUpdates() {
		List<Updates> list = new ArrayList<>();
		
		try {
			con = ConnectFA.connect();
			String queryA = "SELECT * FROM Updates;";
			ps = con.prepareStatement(queryA);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Updates a = new Updates();
				
				a.setAid(rs.getString(1));
				a.setOccured(rs.getString(2));
				a.setSmokeLevel(rs.getInt(3));
				a.setCo2Level(rs.getInt(4));
				a.setIsActive(rs.getInt(5));
				a.setIsWorking(rs.getInt(6));
				
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
}
