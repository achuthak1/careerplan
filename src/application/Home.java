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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Home  implements Initializable{
	@FXML
	private MenuButton Payments;
	@FXML
	private MenuButton Fine;
	@FXML
	private Button Fastrack;
	@FXML
	private MenuButton options;
	@FXML
	private Button LogOut;
	@FXML
	protected Label  CurrentUser;
	@FXML
	private Button Feedback;
	@FXML
	private Label AnnualFeeStatus;
	@FXML
	public static Text label1;
	@FXML
	public static Label label2;
	@FXML
	public static Label label3;
	@FXML
	public static Label label4;
	@FXML
	public static TextField Amount1;
	@FXML
	public static TextField Amount2;
	@FXML
	private JFXButton Status;
	@FXML
	private Label department;
	@FXML
	private Label semester;
	@FXML
	private JFXButton Reciept;
	
   public static String username;

    //event handling method for Logout
    @FXML
	private void LogOutAction(ActionEvent event) throws IOException{
	System.out.println("Logout button has been clicked");
	Parent Home_parent=FXMLLoader.load(getClass().getResource("Login.fxml"));
	Scene Home_scene=new Scene(Home_parent);
	Home_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	Stage app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
	app_stage.hide();
	app_stage.setScene(Home_scene);
	app_stage.show();
	clearSession();	
	}
    
    //Method to load new scenes for different types of payments available
    private void loadFxml(String name,int w,int h) throws IOException{
    	
    	FXMLLoader Home_parent=new FXMLLoader(getClass().getResource(name));
    	Parent root1 = (Parent) Home_parent.load();
    	Scene Home_scene=new Scene(root1,w,h);
    	//Home_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage app_stage=new Stage();
    	app_stage.setScene(Home_scene);
    	app_stage.initModality(Modality.APPLICATION_MODAL);
        // app_stage.initOwner(About.getScene().getWindow());
        app_stage.showAndWait();
    }
    
    //Event handling method for reEvaluation menu item
	@FXML
	private void RevalAction(ActionEvent event) throws IOException{
	  loadFxml("Reval.fxml",500,500);
	  }
	
	//Event handling method for Makeup menu item
	@FXML
	private void MakeUpAction(ActionEvent event) throws IOException{
	loadFxml("Make-up.fxml",500,500);
    } 
	
	//Event handling method for IdCard menu item
	@FXML
	private void IdCardAction(ActionEvent event) throws IOException{
	loadFxml("Id.fxml",500,200);
	}
	
	//Event handling method for Library card menu item
	@FXML
	private void LibraryAction(ActionEvent event) throws IOException{
	loadFxml("Library.fxml",500,200);
	}
	
	//Event handling method for fastrack menu item
	@FXML
	private void FastrackAction(ActionEvent event) throws IOException{
		if(valid()){
       loadFxml("Fastrack.fxml",700,550);
	}
	}
	
	//Event handling method for Contact menu item under options menu box
	@FXML
	private void ContactAction(ActionEvent event) throws IOException{
		loadFxml("Contact.fxml",400,400);
	}
	
	//Event handling method for About us menu item under options menu box
	@FXML
	private void AboutAction(ActionEvent event) throws IOException{
		loadFxml("About.fxml",400,400);
	}
	
	//Event handling method for feedback button
	@FXML
	private void FeedbackAction(ActionEvent event) throws IOException{
		loadFxml("Feedback.fxml",500,500);
	}
	
	//Event handling method for status button
    @FXML
	private void statusAction(ActionEvent event) throws IOException{
		loadFxml("status.fxml",700,500);
	}
    
    //Method used to check whether the user who is currently Logged in is eleigible for fastrack or not
	private boolean valid(){
		Connection c = null;
		Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
            String query="SELECT fast_flag FROM payment,userdata WHERE usn="+"'"+Login.usernam+"'";
            stmt = c.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
            	String check=rs.getString("fast_flag");
            	System.out.println(check);
            	if(check.equals("yes")){
            		break;
            	}
            	if(check.equals("no")){
            		Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation:");
                    alert.setHeaderText("Exit application");
                    alert.setContentText("You are not eleigible for fastrack");
                    alert.showAndWait();
            		return false;
            		
              }
            }
            
            System.out.println("Opened database successfully"); 
            rs.close();
            c.close();
            } catch ( SQLException e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
            }
		return true;
	}

	    //Event handling method for Reciept button
        @FXML
        private void  RecieptAction(){
        	reciept viewAnnualReciept=new reciept();
            reciept viewRevalReciept=new reciept();
            reciept viewMakeupReciept=new reciept();
            reciept viewFastrackReciept=new reciept(); 
        	viewAnnualReciept.showReciept("src/report/status.jasper");   
        	viewRevalReciept.showReciept("src/report/reciept.jasper");
        	viewMakeupReciept.showReciept("src/report/makeup.jasper");
        	viewFastrackReciept.showReciept("src/report/Fastrack_Reciept.jasper"); 
        }
        
        //Method to set 
        @FXML
        public static  void setLabel1(){
        	label1.setText("Id card Amount has been selected");
        	label2.setText("300");
        }
	@FXML
	protected void setSession(){
		System.out.println("session method called");
	       Connection c = null;
	        Statement stmt = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/careerplan","root","");
	            c.setAutoCommit(false);
	            String query="SELECT * FROM userdata";
	            System.out.println("Opened database successfully");
	           stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next()){
	             if(rs.getInt("session")==1){
	            	 username=rs.getString("name");
	            	 System.out.println(username);    
	                 CurrentUser.setText( rs.getString("name"));
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
	
	//Method to clear session as soon as user logs Out
	@FXML
	protected void clearSession(){
		System.out.println("session method called");
	       Connection c = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/careerplan","root","");
	           // c.setAutoCommit(false);
	            String query="UPDATE userdata set session=0 where name=?";
	            PreparedStatement pst=c.prepareStatement(query);
	            pst.setString(1,username);
	            System.out.println("Opened database successfully");
	            System.out.println(username);  
	            pst.executeUpdate();
	            pst.close();
	            c.close();
	            } catch ( SQLException e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
	            }
	}
	
	//Method to set the department and semester of user in the Home page
   /* @FXML
    void setDept(){

	       Connection c = null;
	        Statement stmt = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            c.setAutoCommit(false);
	            String query="SELECT semester,department FROM userdata where name= "+"'"+username+"'";
	            System.out.println("Opened database successfully");
	           stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next()){
	             semester.setText(Integer.toString(rs.getInt("semester")));
	             department.setText(rs.getString("department"));
	           }
	            rs.close();
	            c.close();
	            } catch ( SQLException e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
	            }
    }
		
    //Method to check whether the user has paid the Annual fee or not.If not the, method displays the due on the Home page
	@FXML
	private void feeStatus(){
	     Connection c = null;
	        Statement stmt = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            c.setAutoCommit(false);
	            String query="SELECT annual_pay FROM payment where name= "+"'"+username+"'";
	            System.out.println("Opened database successfully");
	           stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next()){
	             if(rs.getInt("annual_pay")==0)
	            	AnnualFeeStatus.setText("Annual fee is payed for year 2016-17");
	             else if(rs.getInt("annual_pay")==16500)
	            	 AnnualFeeStatus.setText("Annual fee is cuurently due for year 2016-17.Amount: "+rs.getInt("annual_pay"));
	             else if(rs.getInt("annual_pay")==140000)
	            	 AnnualFeeStatus.setText("Annual fee is cuurently due for year 2016-17.Amount: "+rs.getInt("annual_pay"));
	             else if(rs.getInt("annual_pay")==49500)
	            	 AnnualFeeStatus.setText("Annual fee is cuurently due for year 2016-17.Amount: "+rs.getInt("annual_pay"));
	           }
	            rs.close();
	            c.close();
	            } catch ( SQLException e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	            	//Logger.getLogger(Home.class.getName()).log(Level.SEVERE,null,e);
	            }
	}*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
       setSession();
	}

	
	
}
