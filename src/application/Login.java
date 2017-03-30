package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Node;


public class Login  implements Initializable{	
	@FXML
	private JFXTextField username;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXButton submit;
	@FXML
	private Label invalid_label;
	
    static String  usernam;
    static  String  passwor;
	Stage app_stage;

    //Event handling method for submit button
	@FXML
	private void submitAction(ActionEvent event) throws IOException{
		 app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		 System.out.println("Submit button has been clicked");
		 app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		 usernam=username.getText();
		 passwor=password.getText();
		 System.out.println(usernam+" "+passwor);
		 if(isValidDetails()){
			 setSession();
		 }
		 else{
        	 username.clear();
    			password.clear();
    			invalid_label.setText("Username or password is wrong");
         }
		 
	}
	
	//Method to validate entered username and password with the data present in database
	private boolean isValidDetails(){
		boolean flag=false;
		    System.out.println( "SELECT email,password FROM userdata WHERE email= " + "'" + username.getText() + "'" 
		            + " AND password= " + "'" + password.getText() + "'" );
	    
	        Connection c = null;
	        Statement stmt = null;
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/careerplan","root","");
	            c.setAutoCommit(false);
	            
	            System.out.println("Opened database successfully");
	           stmt = c.createStatement();
	            
	            ResultSet rs = stmt.executeQuery( "SELECT email,password,name FROM userdata WHERE email= " + "'" + username.getText() + "'" 
	            + " AND password= " + "'" + password.getText() + "'");
	            
	            while ( rs.next() ) {
	            	String c_user=rs.getString("email");
	            	String c_pass=rs.getString("password");
	            	String name=rs.getString("name");
	            	System.out.println(c_user+""+c_pass+""+name);
	            	
	            	if ( c_user.equals(usernam) && c_pass.equals(passwor) && name.equals("Administrator")) { 
	                    	 Parent Home_parent=FXMLLoader.load(getClass().getResource("admin.fxml"));
	             			Scene Home_scene=new Scene(Home_parent,1920,1080);
	             			 Home_scene.getStylesheets().add(getClass().getResource("listview.css").toExternalForm());
	             			//Stage app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
	             			app_stage.hide();
	             			app_stage.setScene(Home_scene);
	             			app_stage.show();	
	             			flag=true;
	                     }
	                     else if(c_user.equals(usernam) && c_pass.equals(passwor) && !name.equals("Administrator")){
	                    	 setSession();
	                    	 Parent Home_parent=FXMLLoader.load(getClass().getResource("Home.fxml"));
		             			Scene Home_scene=new Scene(Home_parent,1159,620);
		             			 Home_scene.getStylesheets().add(getClass().getResource("application1.css").toExternalForm());
		             			//Stage app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		             			app_stage.hide();
		             			app_stage.setScene(Home_scene);
		             			app_stage.show();
		             			flag=true;
	                    		
	                     }
	                    
	         
	                 }  
	            
	            rs.close();
	            stmt.close();
	            c.close();
	            } catch ( Exception e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	            }
	            System.out.println("Operation done successfully");
	            return flag;
	}
	
	//Method to set session	in the database for the user who Logs in
    @FXML
	private boolean setSession(){
	    System.out.println(usernam);
		boolean let_in = false;
		System.out.println("session method");
        System.out.println( "UPDATE userdata SET session=1 WHERE email= " + "'" + username.getText() + "'" 
	            + " AND password= " + "'" + password.getText() + "'" );
        Connection c = null;
       
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/careerplan","root","");
          //  c.setAutoCommit(false);
            String sql= "UPDATE userdata  SET session=? WHERE email= ? " 
                    +" AND password= ?";
            PreparedStatement pst=c.prepareStatement(sql);
            pst.setInt(1, 1);
            pst.setString(2,username.getText() );
            pst.setString(3,password.getText());
            System.out.println("Opened database successfully");
            pst.executeUpdate();
            pst.close();
            c.close();
            }catch ( SQLException e ) {
               System.err.println( e.getClass().getName() + ": " + e.getMessage() );
               System.exit(0);
             
            }
            System.out.println("Operation done successfully");
            return let_in;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
}