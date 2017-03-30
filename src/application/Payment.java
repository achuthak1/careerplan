package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class Payment implements Initializable{
 
    @FXML
    private JFXButton Submit;
    @FXML
    private JFXButton Reset;
    @FXML
    private JFXTextField card;
    @FXML
    private JFXTextField cvv;
    @FXML
    private Label invalid_label;
    String cardNo;
    int pass;
    
    //Event handling method for submit button
    @FXML
	private void submitAction(ActionEvent event) throws IOException{
		System.out.println("Submit button has been clicked");
	
		//Calling method to check whether the entered card number and cvv are correct or not
		if(isValidDetails()){
		               setStatus();
			           alert("Payment has been done succesfully");
			           setInvoice(Login.usernam);
			           Stage stage = (Stage) Submit.getScene().getWindow();
			      	    stage.close();
                        System.out.println("the payment is succesfull");
                        reciept viewAnnualReciept=new reciept();
                        reciept viewRevalReciept=new reciept();
                        reciept viewMakeupReciept=new reciept();
                        reciept viewFastrackReciept=new reciept(); 
                    	viewAnnualReciept.showReciept("src/report/status.jasper");   
                    	viewRevalReciept.showReciept("src/report/reciept.jasper");
                    	viewMakeupReciept.showReciept("src/report/makeup.jasper");
                    	viewFastrackReciept.showReciept("src/report/Fastrack_Reciept.jasper"); 
		}
		else{
			card.clear();
			cvv.clear();
			invalid_label.setText("cardNo or cvv is wrong or Balance is not available");
		}
	}
    
    //Method to set invoice corresponding to user who makes successfull payment
    private void setInvoice(String usn){
    	 Connection c = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            String query="UPDATE payment,userdata SET Invoice=? WHERE payment.usn=userdata.username AND userdata.session=1 ";
	            PreparedStatement pst;
	            pst=c.prepareStatement(query);
	            pst.setString(1,Login.usernam);
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
    
        //Event handling method for reset button to clear the fields
         @FXML
	     private void resetAction(ActionEvent event) throws IOException{
		  System.out.println("Reset button has been clicked");
          card.clear();
	      cvv.clear();
        }
         
        // method to check in the database whether the entered card number and cvv are correct or not
        @FXML
	    private boolean isValidDetails() {
		// TODO Auto-generated method stub
	
	       boolean let_in = false;
	        System.out.println( "SELECT card_num,cvv,balancr FROM cards WHERE card_num= " + "'" + card.getText() + "'" 
		            + " AND cvv= " + "'" + cvv.getText() + "'"+"AND balance>status.totalamt");
	    
	        Connection c = null;
	        Statement stmt = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            c.setAutoCommit(false);       
	            System.out.println("Opened database successfully");
	           stmt = c.createStatement();
	           System.out.println(status.amount);
	            ResultSet rs = stmt.executeQuery("SELECT card_num,cvv,balance FROM cards WHERE card_num= " + "'" + card.getText() + "'" 
		            + " AND cvv= " + "'" + cvv.getText() + "'"+"AND balance>="+status.amount);
	            while ( rs.next() ) {
	                 if (rs.getString("card_num") != null && rs.getInt("cvv") != 0) { 
	                     cardNo = rs.getString("card_num");
	                     System.out.println( "cardNumber = " + cardNo );
	                     pass = rs.getInt("cvv");
	                     System.out.println("cvv = " + pass);
	                     let_in = true;
	                 }  
	            }	            
		            System.out.println("Operation done successfully");
	            rs.close();
	            stmt.close();
	            c.close();
	            } catch ( Exception e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	                alert("Balance is not available");
	            }
	            
	            return let_in;
	}
        
        //Method to set the status of different payments as "paid" on successfull payment
        private void setStatus(){
        	 Connection c = null;
  	        try {
  	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
  	            String query="UPDATE payment SET Revalstatus='paid',MakeupStatus='paid',IdStatus='paid',LibraryStatus='paid',AnnualStatus='paid',FastrackStatus='paid' WHERE usn="+"'"+Login.usernam+"'";
  	            PreparedStatement pst;
  	            pst=c.prepareStatement(query);
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
        
     	//Alert method used to display various alerts thrown by methods to users
        private void alert(String msg){
        	   Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.setTitle("Confirmation:");
	            alert.setHeaderText("Payment");
	            alert.setContentText(msg);
	            alert.showAndWait();
        }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }

}
