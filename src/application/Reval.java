package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Reval implements Initializable{
    @FXML
    private JFXCheckBox first;
    @FXML
    private JFXCheckBox second;
    @FXML
    private JFXCheckBox third;
    @FXML
    private JFXCheckBox fourth;
    @FXML
    private JFXCheckBox fifth;
    @FXML
    private JFXCheckBox sixth;
    @FXML
    private JFXTextField one;
    @FXML
    private JFXTextField two;
    @FXML
    private JFXTextField three;
    @FXML
    private JFXTextField four;
    @FXML
    private JFXTextField five;
    @FXML
    private JFXTextField six;
    @FXML
    private JFXButton Add;
    @FXML
    private Label id1;
    @FXML
    private Label  id2;
    @FXML
    private Label id3;
    @FXML
    private Label id4;
    @FXML
    private Label id5;
    @FXML
    private Label id6;
    int sum=0;
	final ObservableList<String> checkBoxList=FXCollections.observableArrayList();
	
	//Event handling method for checkbox when it is selected
       @FXML
        private void firstAction(ActionEvent event)throws IOException{
        	if(RevalSelected(Login.usernam,first.getText(),id1.getText())){
        		;
        	}
        	 else{
	            	 sum+=600;
	            	 one.setText("600");
	            	 addPaymentdb(id1.getText(),first.getText(),600);
                     alertSelected("New reval request for selected subject has been succesfully recorded");
               
	             }
        	
        }                
        
     //Event handling method for checkbox when it is selected
       @FXML
        private void secondAction(ActionEvent event)throws IOException{
        	if(RevalSelected(Login.usernam,second.getText(),id2.getText())){
        		;
        	}
        	 else{
	            	 sum+=600;
	            	 two.setText("600");;
	            	 addPaymentdb(id2.getText(),second.getText(),600);
                     alertSelected("New reval request for selected subject has been succesfully recorded");
               
	             }
        
        }
        
      //Event handling method for checkbox when it is selected
        @FXML
        private void thirdAction(ActionEvent event)throws IOException{
        	if(RevalSelected(Login.usernam,third.getText(),id3.getText())){
        		;
        	}
        	 else{
	            	 sum+=600;
	            	 three.setText("600");
	            	 addPaymentdb(id3.getText(),third.getText(),600);
                     alertSelected("New reval request for selected subject has been succesfully recorded");
               
	             }
        	
        }
        
        //Event handling method for checkbox when it is selected
        @FXML
        private void fourthAction(ActionEvent event)throws IOException{
          if(RevalSelected(Login.usernam,four.getText(),id4.getText())){
        		;
          }
          else{
          	 sum+=600;
          	 four.setText("600");
          	 addPaymentdb(id4.getText(),fourth.getText(),600);
             alertSelected("New reval request for selected subject has been succesfully recorded");
            
           }
        	
        }
       
        //Event handling method for checkbox when it is selected
        @FXML
        private void fifthAction(ActionEvent event)throws IOException{
        	if(RevalSelected(Login.usernam,five.getText(),id5.getText())){
        		;
        	}
        	 else{
	            	 sum+=600;
	            	 five.setText("600");
	            	 addPaymentdb(id5.getText(),fifth.getText(),600);
                     alertSelected("New reval request for selected subject has been succesfully recorded");
               
	             }
        		
        	
        }
      
        //Event handling method for checkbox when it is selected
        @FXML
        private void sixthAction(ActionEvent event)throws IOException{
        	if(RevalSelected(Login.usernam,six.getText(),id6.getText())){
        		;
        	}
        	 else{
	            	 sum+=600;
	            	 addPaymentdb(id6.getText(),six.getText(),600);
	            	 six.setText("600");
                     alertSelected("New reval request for selected subject has been succesfully recorded");
               
	             }
        }
       
        //method to check whether the user has selected the subject previously ,if not the subject is added to database
        private boolean RevalSelected(String usn,String subject,String course_id){
            boolean f=false;
        	Connection c = null;
 	        Statement stmt = null;
 	        try {
 	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
 	            c.setAutoCommit(false);
 	            String query="SELECT usn,subject_name FROM paymentsapplied WHERE usn="+"'"+Login.usernam+"' AND subject_name="+"'"+subject+"'";
 	            System.out.println("Opened database successfully");
 	            stmt = c.createStatement();
 	            ResultSet rs = stmt.executeQuery(query);
 	         
 	            while(rs.next()){
 	             if(rs.getString("usn").equals(usn) && rs.getString("subject_name").equals(subject)){
 	            	 System.out.println("Revaluation for subject "+subject+" has already placed");
                      alertSelected("Revaluation for selected subject already placed");
                    f=true;
 	             }
 	            
 	           }
 	            rs.close();
 	            c.close();
 	            } catch ( SQLException e ) {
 	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 	                System.exit(0);
 	            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
 	            }
 	        return f;
        }
       
      //Method to add newly selected subjects by user into database
        private void  addPaymentdb(String id,String name,int amt){
        	Connection c = null;
 	        try {
 	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
 	            String query="INSERT INTO paymentsapplied(usn,c_id,subject_name,reval_amt) VALUES(?,?,?,?)";
 	            PreparedStatement pst;
 	            pst=c.prepareStatement(query);
 	            pst.setString(1, Login.usernam);
 	            pst.setString(2,id);
 	            pst.setString(3,name);
 	            pst.setInt(4,amt);
 	            System.out.println("Opened database successfully"); 
 	            pst.executeUpdate();
 	            System.out.println("inserted into database");
 	            pst.close();
 	            c.close();
 	            } catch ( SQLException e ) {
 	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 	                System.exit(0);
 	            	
 	            }
        }
       
       //Event handling method raised by Add button
        @FXML
        private void AddAction(ActionEvent event)throws IOException{
        valid();
      	Stage stage = (Stage) Add.getScene().getWindow();
  	    stage.close();
        }
        
       //method to check whether the user has already done the payment or not for the subjects selected if any previously
        private void valid(){
 		   Connection c = null;
 	        Statement stmt = null;
 	        try {
 	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
 	            c.setAutoCommit(false);
 	            String query="SELECT reval_pay,RevalStatus FROM userdata,payment WHERE payment.usn=userdata.username AND session=1";
 	            System.out.println("Opened database successfully");
 	            stmt = c.createStatement();
 	            ResultSet rs = stmt.executeQuery(query);
 	            while(rs.next()){
 	             if(rs.getInt("reval_pay")>0 && rs.getString("RevalStatus").equals("paid")){
 	            	 System.out.println("reval for selected subjects  already placed");
                      alertSelected("Revaluation for selected subjects already placed and amount has been paid");
 	             }
 	             else{
 	            	 setIdDb();
                      alertSelected("New reval request for selected subjects has been succesfully recorded");                      
 	             }
 	           }
 	            rs.close();
 	            c.close();
 	            } catch ( SQLException e ) {
 	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 	                System.exit(0);
 	            	
 	            }
 	}
 	
    //payment database is update with total fastrack amount of all subjects selected by user     
    private void setIdDb()
 	{
 		 Connection c = null;
 	        try {
 	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
 	           // c.setAutoCommit(false);
 	            String query="UPDATE userdata,payment set payment.reval_pay= payment.reval_pay+? where payment.usn=userdata.username AND userdata.session=?";
 	            PreparedStatement pst;
 	            pst=c.prepareStatement(query);
 	            pst.setInt(1,sum);
 	            pst.setInt(2, 1);
 	            System.out.println("Opened database successfully"); 
 	            pst.executeUpdate();
 	            pst.close();
 	            c.close();
 	            } catch ( SQLException e ) {
 	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 	                System.exit(0);
 	            	
 	            }
 	}
 	
 	//Alert method used to display various alerts thrown by methods to users
 	private void alertSelected(String msg){
 		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setHeaderText("Status");
        alert.setContentText(msg);
        alert.showAndWait();
 	}
   
        
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
