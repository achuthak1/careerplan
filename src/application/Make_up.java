/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author HP
 */
public class Make_up implements Initializable{
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
    private Label id2;
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
	 
	//Method to add newly selected subjects by user into database
     private void  addpaymentdb(String id,String name,int amt){
     	Connection c = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            String query="INSERT INTO makeup(usn,c_id,subject_name,makeup_amt) VALUES(?,?,?,?)";
	            PreparedStatement pst;
	            pst=c.prepareStatement(query);
	            pst.setString(1, Login.usernam);
	            pst.setString(2,id);
	            pst.setString(3,name);
	            pst.setInt(4,amt);
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
     
       ////Event handling method for checkbox1
        @FXML
        private void firstAction(ActionEvent event)throws IOException{
        	if(MakeupSelected(Login.usernam,first.getText(),id1.getText())){
        		;
        	}
        	else{
            one.setText("300");
            sum+=300;
            addpaymentdb(id1.getText(),first.getText(),300);
            alertSelected("New reval request for selected subject has been succesfully recorded");
        	}
        }
        
      //Event handling method for checkbox2
        @FXML
        private void secondAction(ActionEvent event)throws IOException{
        
        	if(MakeupSelected(Login.usernam,second.getText(),id2.getText())){
        		;
        	}
        	else{
            two.setText("300");
            sum+=300;
            addpaymentdb(id2.getText(),second.getText(),300);
            alertSelected("New reval request for selected subject has been succesfully recorded");
           }
        }
        
      //Event handling method for checkbox3
        @FXML
        private void thirdAction(ActionEvent event)throws IOException{
        	if(MakeupSelected(Login.usernam,third.getText(),id3.getText())){
        	  ;
        	}
        	else{
            three.setText("300");
            sum+=300;
            addpaymentdb(id3.getText(),third.getText(),300);
            alertSelected("New reval request for selected subject has been succesfully recorded");
        	}
        }
        
      //Event handling method for checkbox4
        @FXML
        private void fourthAction(ActionEvent event)throws IOException{
        	if(MakeupSelected(Login.usernam,fourth.getText(),id4.getText())){
        		;
        	}
        	else{
           four.setText("300");
           sum+=300;
           addpaymentdb(id4.getText(),fourth.getText(),300);
           alertSelected("New reval request for selected subject has been succesfully recorded");
        	}
        }
        
      //Event handling method for checkbox5
        @FXML
        private void fifthAction(ActionEvent event)throws IOException{
        	if(MakeupSelected(Login.usernam,fifth.getText(),id5.getText())){
        		;
        	}
        	else{
            five.setText("300");
            sum+=300;
            addpaymentdb(id5.getText(),fifth.getText(),300);
            alertSelected("New reval request for selected subject has been succesfully recorded");
        	}
        }
        
      //Event handling method for checkbox6
        @FXML
        private void sixthAction(ActionEvent event)throws IOException{
        	if(MakeupSelected(Login.usernam,six.getText(),id6.getText())){
        	  ;
        	}
        	else{
            six.setText("300");
            sum+=300;
            addpaymentdb(id6.getText(),sixth.getText(),300);
            alertSelected("New reval request for selected subject has been succesfully recorded");
        	}
        }
        
        
      //Method to check whether the user has selected the subject previously
        private boolean MakeupSelected(String usn,String subject,String course_id){
            boolean f=false;
        	Connection c = null;
 	        Statement stmt = null;
 	        try {
 	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
 	            c.setAutoCommit(false);
 	            String query="SELECT usn,subject_name FROM makeup WHERE usn="+"'"+Login.usernam+"' AND subject_name="+"'"+subject+"'";
 	            System.out.println("Opened database successfully");
 	           stmt = c.createStatement();
 	            ResultSet rs = stmt.executeQuery(query);
 	         
 	            while(rs.next()){
 	             if(rs.getString("usn").equals(usn) && rs.getString("subject_name").equals(subject)){
 	            	 System.out.println("Makeup for subject "+subject+" has already placed");
                      alertSelected("Makeup for selected subject already placed");
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
        
        //Event handling method for Add button
        @FXML
        private void addAction(ActionEvent event)throws IOException{
        	valid();
      	  Stage stage = (Stage) Add.getScene().getWindow();
  	    stage.close();
        }
        
      //Method to check whether the user has paid Makeup fees or not
        private void valid(){
 		   Connection c = null;
 	        Statement stmt = null;
 	        try {
 	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
 	            c.setAutoCommit(false);
 	            String query="SELECT makeup_pay,Makeupstatus FROM userdata,payment WHERE payment.usn=userdata.username AND session=1";
 	            System.out.println("Opened database successfully");
 	           stmt = c.createStatement();
 	            ResultSet rs = stmt.executeQuery(query);
 	            while(rs.next()){
 	            	if(rs.getInt("makeup_pay")>0 && rs.getString("Makeupstatus").equals("paid")){
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
 	        }catch ( SQLException e ) {
 	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 	                System.exit(0);
 	            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
 	            }
 	}
        
    //Method to update database with the total amount of all selected subjects
 	private void setIdDb()
 	{
 		 Connection c = null;
 	        try {
 	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
 	           // c.setAutoCommit(false);
 	            String query="UPDATE userdata,payment set payment.makeup_pay=payment.makeup_pay+? where payment.usn=userdata.username AND userdata.session=?";
 	            PreparedStatement pst=c.prepareStatement(query);
 	            pst.setInt(1,sum);
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
 	
 	//Method to alert user with various confirmations 
 	private void alertSelected(String msg){
 		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setHeaderText("Status");
        alert.setContentText(msg);
       alert.showAndWait();
 	}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //To change body of generated methods, choose Tools | Templates.
    }
    
}
