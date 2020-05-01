package com;

public class IdentityFA {
	public static String getID(String tableName, String lastID) {
		try {
            String prevID = "",oneSubID = "",newSubID = "",newID = "";
            char letter = '0';
            int newOneSubID;

            switch (tableName) {
                case "Alarm":
                    letter = 'A';
                    prevID = lastID;
                    break;
                default:
                	break;
            }
            
            if(prevID != null) {
                oneSubID = 1 + prevID.substring(1);
                newOneSubID = Integer.parseInt(oneSubID)+1;
                newSubID = Integer.toString(newOneSubID);
                newID = letter+newSubID.substring(1);
                System.out.println(newID);
            }
            else
                return letter+"000000001";
            
            return newID;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        return null;
	}
}
