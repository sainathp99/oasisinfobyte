package com.reservationsystem.service;
import java.sql.*;

public class LoginService {
    public static boolean authenticateUser(String userId, String password)throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Train","root","");
    	PreparedStatement st=con.prepareStatement("SELECT COUNT(*) FROM user WHERE user.id = ? and user.pass=?");
    	st.setString(1, userId);
    	st.setString(2, password);
    	ResultSet rs=st.executeQuery();
    	if(rs.next() && rs.getInt(1) ==1) {
    	   return true;
    	}
    	else {
    	   return false;
    	}
    }
    
}
