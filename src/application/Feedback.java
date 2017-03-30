package application;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Feedback implements Initializable {

    @FXML
    private Label Feedback;
    @FXML
    private TextArea Text;
    @FXML
    private JFXButton Submit;

    //Event handling method for submit button to update the database corresponding to user with the entered feedback
    @FXML
    private void SubmitAction(ActionEvent event) throws IOException {
        String data = Text.getText();
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/noq_bmsce1.1","root","");
           // c.setAutoCommit(false);
            String query="UPDATE userdata SET feedback=? where session=? ";
            PreparedStatement pst=c.prepareStatement(query);
            pst.setString(1,Text.getText());
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
        Stage stage = (Stage) Submit.getScene().getWindow();
  	    stage.close();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation:");
        alert.setHeaderText("Thank You");
        alert.setContentText("Your Feedback has been recieved succesfully");
        alert.showAndWait();
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
