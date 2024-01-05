package com.reservationsystem.service;

import java.sql.*;
import java.util.*;

public class ReservationService {
	private static long generateRandomNumber(Random random) {
        // Generate a random 10-digit number
        return (long) (Math.pow(10, 9) + random.nextDouble() * Math.pow(10, 9));
    } 
    public static void makeReservation(String phone,int tno,String sex,String uid,String pname,String DOB,String address,String DOJ)throws Exception {
        // Add logic to save reservation details to the database
    	System.out.println("reserving");
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Train","root","");
        Random random=new Random();
        long randomNumber=generateRandomNumber(random);
        PreparedStatement st1=con.prepareStatement("select train_name,source,destination from trains where trains.train_no=?");
		st1.setInt(1, tno);
	    ResultSet rs=st1.executeQuery();
	    String trainName="",source="",destination="";
	    if(rs.next()) {
	    	trainName=(rs.getString(1));
	    	source=(rs.getString(2));
	    	destination=(rs.getString(3));
	    }
	    try {
        PreparedStatement st2=con.prepareStatement("insert into reservation_log values(?sur,?,?,?,?,?,?,?,?,?,?,?,?)");
        st2.setInt(1,(int)randomNumber);
        st2.setString(2,uid);
        st2.setInt(3, tno);
        st2.setString(4,trainName);
        st2.setString(5,source);
        st2.setString(6, destination);
        st2.setString(7,DOJ);
        st2.setString(8,pname);
        st2.setString(9,phone);
        st2.setString(10,sex);
        st2.setString(11,DOB);
        st2.setString(12,address);
        st2.setString(13,"Booked");
    	st2.executeUpdate();
    	System.out.println("booked");
	    }
	    catch(Exception E) {
	    	System.out.print(E);   }
    }
}
