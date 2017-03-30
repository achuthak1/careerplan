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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;   
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

    public class Id  implements Initializable {
	@FXML
    public  Label IdInfo;
	@FXML
    public  Button Add;
	
	//Event handling method for submit button 
	@FXML
	private void SubmitOnAction(ActionEvent event)throws IOException{
	  System.out.println("submit button for id card has been clicked");
	  valid();
	  Stage stage = (Stage) Add.getScene().getWindow();
	  stage.close();
	}
	
	//Method to check from the database whether user has previously applied for Id Card or not 
	private void valid(){
		   Connection c = null;
	        Statement stmt = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            c.setAutoCommit(false);
	            String query="SELECT Id FROM userdata,payment WHERE payment.usn=userdata.username AND session=1";
	            System.out.println("Opened database successfully");
	           stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next()){
	             if(rs.getInt("Id")>0){
	            	 System.out.println("Id card request already placed");
	            	 Alert alert = new Alert(AlertType.CONFIRMATION);
                     alert.setTitle("Confirmation:");
                     alert.setHeaderText("Exit application");
                     alert.setContentText("Id card request has previously been succesfully recorded");
                     alert.showAndWait();
	             }
	             else{
	            	 setIdDb();
	            	 Alert alert = new Alert(AlertType.CONFIRMATION);
                     alert.setTitle("Confirmation:");
                     alert.setHeaderText("Exit application");
                     alert.setContentText("New Id card Request has been succesfully recorded");
                     alert.showAndWait();
	             }
	           }
	            rs.close();
	            c.close();
	            } catch ( SQLException e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
	            }
	}
	
	//Method to update the database corresponding to user for Id card when submit button is clicked
	private void setIdDb()
	{
		 Connection c = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	           // c.setAutoCommit(false);
	            String query="UPDATE userdata,payment set payment.Id=? where payment.usn=userdata.username AND userdata.session=?";
	            PreparedStatement pst=c.prepareStatement(query);
	            pst.setInt(1,300);
	            pst.setInt(2, 1);
	            System.out.println("Opened database successfully"); 
	            pst.executeUpdate();
	            pst.close();
	            c.close();
	            } catch ( SQLException e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
	            }
	}
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		IdInfo.setText("The amount to be paid for the id card is 300");
	}
    }
