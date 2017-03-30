package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Lab implements Initializable {
	 @FXML
	    private Button Submit;
	    @FXML
	    private Label LabInfo;
	@FXML
	private void SubmitAction(ActionEvent event)throws IOException{
		System.out.println("submit button for Lab fine has been clicked");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		LabInfo.setText("The amount to be paid for the id card is 300");
	}

	

}
