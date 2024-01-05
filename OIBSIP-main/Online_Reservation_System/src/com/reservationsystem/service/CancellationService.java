package com.reservationsystem.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CancellationService {
    public static void cancelReservation(long pnrNumber)throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Train","root","");
	    PreparedStatement st=con.prepareStatement("update reservation_log set reservation_status=? where PNR=?");
		st.setString(1,"Cancelled");
		st.setInt(2,(int)pnrNumber);
		st.executeUpdate();
	    con.close();
		}
}
