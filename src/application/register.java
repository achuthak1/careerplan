package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class register implements Initializable{

	@FXML
	private JFXTextField name;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField contact;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXPasswordField repassword;
	@FXML
	private JFXButton registerButton;
	@FXML
	private JFXButton signInButton;
	@FXML
	private Label invalid_label;
	private Stage app_stage;
	private String Tname,Temail,Tcontact,Tpassword,Trepassword;

	@FXML
	private void signInAction(ActionEvent event) throws IOException{
		 Parent Home_parent=FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene Home_scene=new Scene(Home_parent);
			 Home_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			//app_stage.hide();
			app_stage.setScene(Home_scene);
			app_stage.show();
	}
	@FXML
	private void submitAction(ActionEvent event) throws Exception{
		 app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		 System.out.println("Submit button has been clicked");
		 app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		 Tname=name.getText();
		 Temail=email.getText();
		 Tpassword=password.getText();
		 Tcontact=contact.getText();
		 Tpassword=password.getText();
		 Trepassword=repassword.getText();
		// System.out.println(usernam+" "+passwor);
		 if(isValidDetails()){
			 try {
				storeDetails();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Login();
			
		 }
		 else{
        	    name.clear();
    			password.clear();
    			//invalid_label.setText("Please enter correct details");
         }
		 
	}

	private void Login() throws Exception {
		// TODO Auto-generated method stub
		 Parent Home_parent=FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene Home_scene=new Scene(Home_parent,1159,620);
			 Home_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Stage app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.hide();
			app_stage.setScene(Home_scene);
			app_stage.show();
	}
	private void storeDetails() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		boolean flag=false;
		System.out.println( "INSERT into userdata(name,email,contact,password,session) VALUES(?,?,?,?)" );
        String query="insert into userdata(name,email,contact,password,session) values('"
		                    +Tname+
		                    "','"+
		                    Temail+
		                    "','"+
		                    Tcontact+
		                    "','"+
		                    Tpassword+"',0)"; 
        Connection c = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/careerplan","root","");
           // c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            /*PreparedStatement pst=c.prepareStatement(query);
            pst.setString(1,Tname);
            pst.setString(2,Temail);
            pst.setString(3,Tcontact);
            pst.setString(4,Tpassword);
           */
            System.out.println("Opened database successfully");    
             stmt.executeUpdate(query);
            System.out.println("Details store in the  database successfully");
            invalid_label.setText("Registered Successfully.Please LogIn to continue");
	    }catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
        }
	}

	private boolean isValidDetails() {
		// TODO Auto-generated method stub
		if(checkName()
		&&checkEmail()
		&&checkPassword()
		&&checkContact())
			return true;
		return false;
	}

	private boolean checkContact() {
		// TODO Auto-generated method stub
		if(!Tcontact.isEmpty()){
			//invalid_label.setText("Contact is empty");
			return true;
		}
		
		invalid_label.setText("Contact detail error");	
		return false;
	}

	private boolean checkPassword() {
		// TODO Auto-generated method stub
		if(Tpassword.isEmpty()||Trepassword.isEmpty()){
			invalid_label.setText("password field is empty");
		    return false;
	     }
		else if(Tpassword.equals(Trepassword))
			return true;
		invalid_label.setText("password error");
		return false;
	}

	private boolean checkEmail() {
		// TODO Auto-generated method stub
		if(!Temail.isEmpty()){
			//invalid_label.setText("Email field is empty");
			return true;
		}
		/*else if(Temail.matches(""))
			return true;*/
		invalid_label.setText("email error");
		return false;
	}

	private boolean checkName() {
		// TODO Auto-generated method stub
		if(!Tname.isEmpty())
		{
			
			return true;
		}
		invalid_label.setText("name field is empty");
		return false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	

}
