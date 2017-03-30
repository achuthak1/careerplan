package application;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

//Class used to print reciept in form of Jasper Report
public class reciept extends JFrame {
	Connection conn=databaseConnectivity.Connect();
	PreparedStatement pst=null;
	ResultSet rs=null;
	public reciept() throws HeadlessException{
		
	}
	
	//Displaying the receipt
	public void showReciept(String loc){
		try{
		    //JasperReport jasperReport=JasperCompileManager.compileReport("src/report/status.jasper");
			JasperPrint JasperPrint=JasperFillManager.fillReport(loc,null,conn);
			JRViewer viewer=new JRViewer(JasperPrint);
			viewer.setOpaque(true);
			viewer.setVisible(true);
			
			this.add(viewer);
			this.setSize(1280,720);
			this.setVisible(true);
			
			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(rootPane,e.getMessage());
		}
	}

}
