package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Provides database connectivity
public  class databaseConnectivity {
		// TODO Auto-generated constructor stub
		public static Connection Connect(){
			try{
				String url="jdbc:mysql://localhost:3306/careerPlan";
				String username="root";
				String password="";
				Class.forName("com.mysql.jdbc.Driver");
				 Connection conn=DriverManager.getConnection(url,username,password);
				 return conn;
			} catch (ClassNotFoundException| SQLException e ) {
               
             
            	Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
            }
			return null;
		
	}

}
