package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
	
	public static User authenticateUser(String email, String password) {
		User dbUser = null;
		
		try {
			con = ConnectFA.connect();
			String queryA = "SELECT * FROM UserFA WHERE email = ? AND password = ?;";
			ps = con.prepareStatement(queryA);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dbUser = new User();
				
				dbUser.setEmail(rs.getString(1));
				dbUser.setPassword(rs.getString(2));
				dbUser.setName(rs.getString(3));
				dbUser.setPhone(rs.getString(4));
				dbUser.setAddress(rs.getString(5));
				dbUser.setBio(rs.getString(6));
				dbUser.setImg(rs.getString(7));
			}
			
			return dbUser;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(ps != null) try { ps.close(); } catch(Exception e) {}
            if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return dbUser;
	}
	
}
