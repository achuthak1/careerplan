package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class status implements Initializable {

	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private Label label5;
	@FXML
	private Label label6;
	@FXML
	private JFXTextField amt1;
	@FXML
	private JFXTextField amt2;
	@FXML
	private JFXTextField amt3;
	@FXML
	private JFXTextField amt4;
	@FXML
	private JFXTextField amt5;
	@FXML
	private JFXTextField amt6;
	@FXML
	private JFXTextField totalAmt;
	@FXML
	private JFXButton total;
	@FXML
	private JFXButton pay;
	@FXML
	private JFXButton getStatus;
    static int totalamt=0;
    static int amount=0;
    
    //Event handling method for getStatus button to display all the payments pending
	@FXML
	private void getStatusAction(ActionEvent event){
		annualStatus();
		revalStatus();
		makeupStatus();
		idStatus();
		libraryStatus();
		fastrackStatus();
		
	}
	
	//Method to retrieve Annual Fee status
	@FXML
	private void annualStatus(){
		 Connection c = null;
	        Statement stmt = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            String query="SELECT annual_pay,AnnualStatus FROM payment,userdata where session= 1 AND userdata.username=payment.usn";
	            System.out.println("Opened database successfully");
	           stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next()){
	            	 if(rs.getInt("annual_pay")>0 && rs.getString("AnnualStatus").equals("paid"))
	            	label1.setText("Annual fee is payed for year 2016-17");
	             else if(rs.getInt("annual_pay")==0){
	            	 label1.setText("");
	             }
	             else{
	            	 label1.setText("Annual fee is cuurently due for year 2016-17");
	            	 amount+=rs.getInt("annual_pay");
	            	 amt1.setText(String.valueOf(rs.getString("annual_pay")));
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
	
	//Method to retrieve ReEvaluation Fee status
	@FXML
	private void revalStatus(){
		Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
         
            String query="SELECT reval_pay,RevalStatus FROM payment,userdata where session= 1 AND userdata.username=payment.usn";
            System.out.println("Opened database successfully");
           stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
               if(rs.getInt("reval_pay") > 0 && rs.getString("RevalStatus").equals("paid")){
            	   label2.setText(" Revaluation amount is paid");
               }
               else if(rs.getInt("reval_pay")==0){
            	   label2.setText("");
               }
               else{
               label2.setText("You have applied for Revaluation");
        	   amount+=rs.getInt("reval_pay");
        	   amt2.setText(Integer.toString(rs.getInt("reval_pay")));
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
	
	//Method to retrieve Makeup Fee status
	@FXML
	private void makeupStatus(){
		Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
         
            String query="SELECT makeup_pay,MakeupStatus FROM payment,userdata where session= 1 AND userdata.username=payment.usn";
            System.out.println("Opened database successfully");
           stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
            	
          if(rs.getInt("makeup_pay") > 0 && rs.getString("MakeupStatus").equals("paid")){
        	  label3.setText("Makeup amount has been paid");
               }
          else if(rs.getInt("makeup_pay")==0){
       	   label3.setText("");
          }
            	else{
            		   label3.setText("You have applied for make-up");
                	   amount+=rs.getInt("makeup_pay");
                	   amt3.setText(Integer.toString(rs.getInt("makeup_pay")));
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
	
	//Method to retrieve Fastrack Fee status
	@FXML
	private void fastrackStatus(){
		Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
         
            String query="SELECT fast_pay,FastrackStatus FROM payment,userdata where session=1 AND userdata.username=payment.usn";
            System.out.println("Opened database successfully");
           stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
               if(rs.getInt("fast_pay") > 0 && rs.getString("FastrackStatus").equals("paid")){
            	   label6.setText("Fastrack amount is paid");
               }
               else if(rs.getInt("fast_pay")==0){
            	   label6.setText("");
               }
               else{
               label6.setText("You have applied for Fastrack");
        	   amount+=rs.getInt("fast_pay");
        	   amt6.setText(Integer.toString(rs.getInt("fast_pay")));
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
	
	//Method to retrieve Id card Fee status
	@FXML
	private void idStatus(){
		Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
         
            String query="SELECT Id,IdStatus FROM payment,userdata where session= 1 AND userdata.username=payment.usn";
            System.out.println("Opened database successfully");
           stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
            	
            	 if(rs.getInt("Id") > 0 && rs.getString("IdStatus").equals("paid")){
            		 label4.setText("Id amount has been paid");
               }
            	 else if(rs.getInt("Id")==0){
              	   label4.setText("");
                 }
            	else{
            		label4.setText("You have applied for Id card ");
             	   amount+=rs.getInt("Id");
             	   amt4.setText(Integer.toString(rs.getInt("Id")));
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
	
	//Method to retrieve Library Card Fee status
	@FXML
	private void libraryStatus(){
		Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
         
            String query="SELECT Library,LibraryStatus FROM payment,userdata where session= 1 AND userdata.username=payment.usn";
            System.out.println("Opened database successfully");
           stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
            	
            if(rs.getInt("Library") > 0 && rs.getString("LibraryStatus").equals("paid")){
            	label5.setText("Library card amount has been paid");
               }
            else if(rs.getInt("Library")==0){
         	   label5.setText("");
            }
            else{
            	 label5.setText("You have applied for Library card ");
          	   amount+=rs.getInt("Library");
          	   amt5.setText(Integer.toString(rs.getInt("Library")));
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
	
	//Method to display total amount of all payments pending
	@FXML
	private void totalAction(){
	    totalAmt.setText(Integer.toString(amount));
	    System.out.println(amount);
	}
	
	//Event handling method for Pay Button which displays payment page
	@FXML
	private void payAction(ActionEvent event) throws IOException{
		FXMLLoader Home_parent=new FXMLLoader(getClass().getResource("payment.fxml"));
		Parent root1 = (Parent) Home_parent.load();
		Scene Home_scene=new Scene(root1 ,500,500);
		Stage app_stage=new Stage();
		app_stage.setScene(Home_scene);
		app_stage.initModality(Modality.APPLICATION_MODAL);
		  //app_stage.initOwner(About.getScene().getWindow());
		   app_stage.show();
		   Stage stage = (Stage) pay.getScene().getWindow();
	   	    stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		amount=0;
	}

	

}
