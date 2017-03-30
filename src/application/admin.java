package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class admin implements Initializable{


		// TODO Auto-generated constructor stub
		@FXML
		private TableView<paymentData> status;
		@FXML
		private TableColumn<paymentData,String> usn;
		@FXML
		private TableColumn<paymentData,String> name;
		@FXML
		private TableColumn<paymentData,String> fast;
		@FXML
		private TableColumn<paymentData,String> annual;
		@FXML
		private TableColumn<paymentData,String> fastPay;
		@FXML
		private TableColumn<paymentData,String> reval;
		@FXML
		private TableColumn<paymentData,String> makeup;
		@FXML
		private TableColumn<paymentData,String> lib;
		@FXML
		private TableColumn<paymentData,String> id;
		@FXML
		private JFXButton load;
		@FXML
		private JFXButton generate;
		@FXML
		private JFXButton feedback;
		@FXML
		private JFXButton logout;
		@FXML
		private TextField usnT;
		@FXML
		private TextField nameT;
		@FXML
		private TextField annualT;
		@FXML
		private TextField fastT;
		@FXML
		private TextField deptT;
		@FXML
		private TextField semT;
		private ObservableList<paymentData>data;

		
		private void tableLoad(){
			Connection conn=databaseConnectivity.Connect();
			data=FXCollections.observableArrayList();
			try {
				ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM PAYMENT");
				while(rs.next()){
					data.add(new paymentData(rs.getString("usn"),rs.getString("name"),rs.getString("fast_flag"),rs.getString("annual_pay"),rs.getString("fast_pay"),rs.getString("makeup_pay"),rs.getString("reval_pay"),rs.getString("Library"),rs.getString("Id")));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generat();
			}
			usn.setCellValueFactory(new PropertyValueFactory<>("usn"));
			name.setCellValueFactory(new PropertyValueFactory<>("name"));
			fast.setCellValueFactory(new PropertyValueFactory<>("fastEligibility"));
			annual.setCellValueFactory(new PropertyValueFactory<>("annual"));
			fastPay.setCellValueFactory(new PropertyValueFactory<>("fastPayment"));
			reval.setCellValueFactory(new PropertyValueFactory<>("RevalAmt"));
			makeup.setCellValueFactory(new PropertyValueFactory<>("MakeUpAmt"));
			lib.setCellValueFactory(new PropertyValueFactory<>("LibraryPayment"));
			id.setCellValueFactory(new PropertyValueFactory<>("IdPayment"));
			
			
			status.setItems(null);
			status.setItems(data);
		}
		@FXML
		private void loadAction(ActionEvent event)throws IOException{
			tableLoad();
		}
		@FXML
		private void clearFields(){
			usnT.clear();
			nameT.clear();
			annualT.clear();
			fastT.clear();
			deptT.clear();
			semT.clear();
			
		}
        @FXML
        private void updateAction(ActionEvent event)throws IOException{
        	Connection conn=databaseConnectivity.Connect();
        	String query="UPDATE payment SET name=?,annual_pay=?,fast_flag=? where usn=?";
        	PreparedStatement pst;
			try {
				pst = conn.prepareStatement(query);
				
	        	pst.setString(1,nameT.getText());
	        	pst.setInt(2,Integer.parseInt(annualT.getText()));
	        	pst.setString(3,fastT.getText());
	        	pst.setString(4,usnT.getText() );
	        	pst.executeUpdate();
	        	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearFields();
        	tableLoad();
        }
        @FXML
        private void insertAction(ActionEvent event)throws IOException{
        	Connection conn=databaseConnectivity.Connect();
        	String query1="INSERT INTO payment(usn,name,annual_pay,fast_flag,fast_pay,makeup_pay,reval_pay,Library,Id) VALUES(?,?,?,?,?,?,?,?,?)";
        	String query2="INSERT INTO userdata(username,password,name,department,semester,session,feedback) VALUES(?,?,?,?,?,?,?)";
        	PreparedStatement pst1,pst2;
			try {
				pst1 = conn.prepareStatement(query1);
				pst2 = conn.prepareStatement(query2);
				pst1.setString(1,usnT.getText());
	        	pst1.setString(2,nameT.getText());
	        	pst1.setInt(3,Integer.parseInt(annualT.getText()));
	        	pst1.setString(4,fastT.getText());
	        	pst1.setInt(5,0);
	        	pst1.setInt(6,0);
	        	pst1.setInt(7,0);
	        	pst1.setInt(8,0);
	        	pst1.setInt(9,0);
	        	pst2.setString(1,usnT.getText());
	        	pst2.setString(2,usnT.getText());
	        	pst2.setString(3,nameT.getText());
	        	pst2.setString(4,deptT.getText());
	        	pst2.setInt(5,Integer.parseInt(semT.getText()));
	        	pst2.setInt(6,0);
	        	pst2.setString(7,null);
	        	pst1.executeUpdate();
	        	pst2.executeUpdate();
	        	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearFields();
        	tableLoad();
        }
        @FXML
        private void deleteAction(ActionEvent event)throws IOException{
        	Connection conn=databaseConnectivity.Connect();
        	String query="delete from userdata where username=?";
        	String query1="delete from payment where usn=?";
        	PreparedStatement pst,pst1;
        	try {
				pst = conn.prepareStatement(query);
				pst1 = conn.prepareStatement(query1);
				pst.setString(1,usnT.getText());
				pst1.setString(1,usnT.getText());
				pst.executeUpdate();
				pst1.executeUpdate();
        	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        	clearFields();
        	tableLoad();
        }
        
        //Event Handling method for Logout Button
        @FXML
        private void LogOutAction(ActionEvent event)throws IOException{
        	clearSession();
        	System.out.println("Logout button has been clicked");
        	Parent Home_parent=FXMLLoader.load(getClass().getResource("Login.fxml"));
        	Scene Home_scene=new Scene(Home_parent);
        	Home_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	Stage app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        	app_stage.hide();
        	app_stage.setScene(Home_scene);
        	app_stage.show();
        }
        //Method to clear the session for the admin when Logout button is clicked
    	protected void clearSession(){
    		System.out.println("session method called");
    	       Connection c = null;
    	        try {
    	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
    	           // c.setAutoCommit(false);
    	            String query="UPDATE userdata set session=NULL where username=?";
    	            PreparedStatement pst=c.prepareStatement(query);
    	            pst.setString(1,"root");
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
        @FXML
        private void reportAction(ActionEvent event)throws IOException{
        	 reciept viewReport=new reciept();
         	viewReport.showReciept("src/report/adminStatus.jasper");  
        }
        @FXML
        private void feedbackReport(ActionEvent event)throws IOException{
        	 reciept viewReport=new reciept();
          	viewReport.showReciept("src/report/feedback.jasper");  
        }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
				}

}
