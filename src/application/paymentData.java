package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class paymentData {

		private final StringProperty usn;
		private final StringProperty name;
		private final StringProperty fastEligibility;
		private final StringProperty annualStatus;
		private final StringProperty fastPayment;
		private final StringProperty RevalAmt;
		private final StringProperty MakeUpAmt;
		private final StringProperty IdPayment;
		private final StringProperty LibraryPayment;

		//Method to store the payment data retrieved from the database to display it in Table View for admin 
		public paymentData(String usn,String name,String fastEligibility,String annualStatus,String fastPayment,String MakeUpAmt,String RevalAmt,String LibraryPayment,String IdPayment){
			this.usn=new SimpleStringProperty(usn);
			this.name=new SimpleStringProperty(name);
			this.fastEligibility=new SimpleStringProperty(fastEligibility);
			this.annualStatus=new SimpleStringProperty(annualStatus);
			this.fastPayment=new SimpleStringProperty(fastPayment);
			this.MakeUpAmt=new SimpleStringProperty(MakeUpAmt);
			this.RevalAmt=new SimpleStringProperty(RevalAmt);
			this.LibraryPayment=new SimpleStringProperty(LibraryPayment);
			this.IdPayment=new SimpleStringProperty(IdPayment);
			
			
		}
		public String getName(){return name.get();}
		public String getUsn(){return usn.get();}
		public String getfastEligibility(){return fastEligibility.get();}
		public String getannualStatus(){return annualStatus.get();}
		public String getfastPayment(){return fastPayment.get();}
		public String getRevalAmt(){return RevalAmt.get();}
		public String getMakeUpAmt(){return MakeUpAmt.get();}
		public String getIdPayment(){return IdPayment.get();}
		public String getLibraryPayment(){return LibraryPayment.get();}
		
		
		public void setUsn(String value){usn.set(value);}
		public void setName(String value){name.set(value);}
		public void setfastEligibility(String value){fastEligibility.set(value);}
		public void setannualStatus(String value){annualStatus.set(value);}
		public void setfastPayment(String value){fastPayment.set(value);}
		public void setRevalAmt(String value){RevalAmt.set(value);}
		public void setMakeUpAmt(String value){MakeUpAmt.set(value);}
		public void setIdPayment(String value){IdPayment.set(value);}
		public void setLibraryPayment(String value){LibraryPayment.set(value);}
		
		
		public StringProperty usnProperty(){return usn;}
		public StringProperty nameProperty(){return name;}
		public StringProperty fastEligibilityProperty(){return fastEligibility;}
		public StringProperty annualProperty(){return annualStatus;}
		public StringProperty fastPaymentProperty(){return fastPayment;}
		public StringProperty RevalAmtProperty(){return RevalAmt;}
		public StringProperty MakeUpAmtProperty(){return MakeUpAmt;}
		public StringProperty IdPaymentProperty(){return IdPayment;}
		public StringProperty LibraryPaymentProperty(){return LibraryPayment;}
	}


