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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Fastrack implements Initializable{
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
    private TextField c1;
    @FXML
    private TextField c2;
    @FXML
    private TextField c3;
    @FXML
    private TextField c4;
    @FXML
    private TextField c5;
    @FXML
    private TextField c6;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXTextField cid1;
    @FXML
    private JFXTextField cid2;
    @FXML
    private JFXTextField cid3;
    @FXML
    private JFXTextField cid4;
    @FXML
    private JFXTextField cid5;
    @FXML
    private MenuButton semester;
    int sum=0;
    int credits;
    int cal;
    int i=0;
    String course[]=new String[10];
    String cid[]=new String[10];
	final ObservableList<String> checkBoxList=FXCollections.observableArrayList();
    
	//Event handling method for displaying subjects of semester 3
	@FXML
    private void semesterThreeAction(ActionEvent event)throws IOException{    
        System.out.println( "SELECT c_id,c_name FROM course WHERE sem=3");   
	    Connection c = null;
	    Statement stmt = null;
        try {
         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root",""); 
         c.setAutoCommit(false);      
	     System.out.println("Opened database successfully");
         stmt = c.createStatement();           
	     ResultSet rs = stmt.executeQuery( "SELECT c_id,c_name FROM course WHERE sem=3");
         i=0;
         while ( rs.next() ) {
	         course[i]= rs.getString("c_name"); 
	         cid[i]=rs.getString("c_id");
             i++;
	     }
        first.setText(course[0]);
        second.setText(course[1]);
        third.setText(course[2]);
        fourth.setText(course[3]);
        fifth.setText(course[4]);
        cid1.setText(cid[0]);
        cid2.setText(cid[1]);
        cid3.setText(cid[2]);
        cid4.setText(cid[3]);
        cid5.setText(cid[4]);
        for(i=0;i<10;i++)
           course[i]="";
        for(i=0;i<10;i++)
           cid[i]="";
	    
        rs.close();
	    stmt.close();
	    c.close();
	   } catch ( Exception e ) {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	     }
	   System.out.println("Operation done successfully");
    }

	//Event handling method for displaying subjects of semester 4
        @FXML
        private void semesterFourAction(ActionEvent event)throws IOException{
            System.out.println( "SELECT c_id,c_name FROM course WHERE sem=4");
	    
	        Connection c = null;
	        Statement stmt = null;
	        try {
	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
	            c.setAutoCommit(false);
	            
	            System.out.println("Opened database successfully");
	            stmt = c.createStatement();
	            
	            ResultSet rs = stmt.executeQuery( "SELECT c_id,c_name FROM course WHERE sem=4");
	            i=0;
	            while ( rs.next() ) {
	              course[i]= rs.getString("c_name");
	              cid[i]=rs.getString("c_id");
                      i++;
	            }
                    first.setText(course[0]);
                    second.setText(course[1]);
                    third.setText(course[2]);
                    fourth.setText(course[3]);
                    fifth.setText(course[4]);
                    
                    	cid1.setText(cid[0]);
                    	cid2.setText(cid[1]);
                    	cid3.setText(cid[2]);
                    	cid4.setText(cid[3]);
                    	cid5.setText(cid[4]);
                        
                    for(i=0;i<10;i++)
                        course[i]="";
                    for(i=0;i<10;i++)
                        cid[i]="";
	            rs.close();
	            stmt.close();
	            c.close();
	            } catch ( Exception e ) {
	                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	                System.exit(0);
	            }
	            System.out.println("Operation done successfully");
        }
        
        //Method to add newly selected subjects by user into database
        private void  addpaymentdb(String id,String name,int amt){
         	Connection c = null;
    	        try {
    	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
    	            String query="INSERT INTO fastrack(usn,c_id,subject_name,fastrack_amt) VALUES(?,?,?,?)";
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
        
        //Event handling method for checkbox1
            @FXML
            private void firstAction(ActionEvent event)throws IOException{
            	if(FastrackSelected(Login.usernam,first.getText(),cid1.getText())){
            		;
            	}
            	else{            
    			setCredits(cid1.getText(),c1,one);
                addpaymentdb(cid1.getText(),first.getText(),Integer.parseInt(one.getText()));
                alertSelected("New reval request for selected subject has been succesfully recorded");
            	}
            }
            
          //Event handling method for checkbox2
            @FXML
            private void secondAction(ActionEvent event)throws IOException{
            
            	if(FastrackSelected(Login.usernam,second.getText(),cid2.getText())){
            		;
            	}
            	else{
                setCredits(cid2.getText(),c2,two);
                addpaymentdb(cid2.getText(),second.getText(),Integer.parseInt(two.getText()));
                alertSelected("New reval request for selected subject has been succesfully recorded");
               }
            }
            
          //Event handling method for checkbox3
            @FXML
            private void thirdAction(ActionEvent event)throws IOException{
            	if(FastrackSelected(Login.usernam,third.getText(),cid3.getText())){
            	  ;
            	}
            	else{
                setCredits(cid3.getText(),c3,three);
                addpaymentdb(cid3.getText(),third.getText(),Integer.parseInt(three.getText()));
                alertSelected("New reval request for selected subject has been succesfully recorded");
            	}
            }
            
          //Event handling method for checkbox4
            @FXML
            private void fourthAction(ActionEvent event)throws IOException{
            	if(FastrackSelected(Login.usernam,fourth.getText(),cid4.getText())){
            		;
            	}
            	else{
              setCredits(cid4.getText(),c4,four);
               addpaymentdb(cid4.getText(),fourth.getText(),Integer.parseInt(four.getText()));
               alertSelected("New reval request for selected subject has been succesfully recorded");
            	}
            }
            
          //Event handling method for checkbox5
            @FXML
            private void fifthAction(ActionEvent event)throws IOException{
            	if(FastrackSelected(Login.usernam,fifth.getText(),cid5.getText())){
            		;
            	}
            	else{
                setCredits(cid5.getText(),c5,five);
                addpaymentdb(cid5.getText(),fifth.getText(),Integer.parseInt(five.getText()));
                alertSelected("New reval request for selected subject has been succesfully recorded");
            	}
            }
            
            //Method to check whether the user has selected the subject previously
            private boolean FastrackSelected(String usn,String subject,String course_id){
                boolean f=false;
            	Connection c = null;
     	        Statement stmt = null;
     	        try {
     	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
     	            c.setAutoCommit(false);
     	            String query="SELECT usn,subject_name FROM fastrack WHERE usn="+"'"+Login.usernam+"' AND subject_name="+"'"+subject+"'";
     	            System.out.println("Opened database successfully");
     	           stmt = c.createStatement();
     	            ResultSet rs = stmt.executeQuery(query);
     	         
     	            while(rs.next()){
     	             if(rs.getString("usn").equals(usn) && rs.getString("subject_name").equals(subject)){
     	            	 System.out.println("Fastrack for subject "+subject+" has already placed");
                          alertSelected("Fastrack for selected subject already placed");
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
            
            //Method to check whether the user has paid fastrack fees or not
            private void valid(){
     		    Connection c = null;
     	        Statement stmt = null;
     	        try {
     	            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
     	            c.setAutoCommit(false);
     	            String query="SELECT fast_pay,Fastrackstatus FROM userdata,payment WHERE payment.usn=userdata.username AND session=1";
     	            System.out.println("Opened database successfully");
     	            stmt = c.createStatement();
     	            ResultSet rs = stmt.executeQuery(query);
     	            while(rs.next()){
     	            	if(rs.getInt("fast_pay")>0 && rs.getString("Fastrackstatus").equals("paid")){
     	 	            	 System.out.println("Fastrack for selected subjects  already placed");
     	                      alertSelected("Fastrack for selected subjects already placed and amount has been paid");
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
     	            String query="UPDATE userdata,payment set payment.fast_pay=payment.fast_pay+? where payment.usn=userdata.username AND userdata.session=?";
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
     	
     	//Method to set credits,amount of subject selected 
    	 private void setCredits(String id,TextField credit,JFXTextField amt){
    	  
    	  System.out.println( "SELECT credits FROM course WHERE c_id= " + "'" +id+"'");
          
          Connection c = null;
          Statement stmt = null;
          try {
              c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
              c.setAutoCommit(false);
              
              System.out.println("Opened database successfully");
             stmt = c.createStatement();
              
              ResultSet rs = stmt.executeQuery( "SELECT credits FROM course WHERE c_id= " + "'" +id+"'");
              
              while ( rs.next() ) {
                   if (rs.getInt("credits") != 0) { 
                       credits = rs.getInt("credits");
                           cal=credits*1000;
                       System.out.println("credits= "+credits);
                       sum+=cal;
                       amt.setText(String.valueOf(cal));
                       credit.setText(String.valueOf(credits));
                      
                     }
              }
              rs.close();
              stmt.close();
              c.close();

              } catch( Exception e ) {
                  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                  System.exit(0);
              }
      }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    	sum=0;
    }

}
