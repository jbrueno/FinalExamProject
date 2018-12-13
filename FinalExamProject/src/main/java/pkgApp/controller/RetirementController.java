package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;

	@FXML
	private Label lblSaveEachMonth;
	
	@FXML
	private Label lblNeedSaved;
	
	@FXML
	private TextField txtYearsToWork;
	
	@FXML
	private TextField txtYearsRetired;
	
	@FXML 
	private TextField txtRequiredIncome;
	
	@FXML
	private TextField txtMonthlySSI;
	
	@FXML
	private Spinner<Double> spnrAnnualReturnWorking;
	
	@FXML
	private Spinner<Double> spnrAnnualReturnRetired;
	

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		SpinnerValueFactory<Double> spnrAnnualReturnWorkingFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 0.20, 0.00, 0.01);
		spnrAnnualReturnWorking.setValueFactory(spnrAnnualReturnWorkingFactory);
		
		SpinnerValueFactory<Double> spnrAnnualReturnRetiredFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 0.03, 0.00, 0.01);
		spnrAnnualReturnRetired.setValueFactory(spnrAnnualReturnRetiredFactory);
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");
		
		lblSaveEachMonth.setText("$ 0");
		lblNeedSaved.setText("$ 0");
		
		txtYearsToWork.setText("");
		txtYearsRetired.setText("");
		txtRequiredIncome.setText("");
		txtMonthlySSI.setText("");
		
		spnrAnnualReturnWorking.getValueFactory().setValue(0.00);
		spnrAnnualReturnRetired.getValueFactory().setValue(0.00);

	}
	
	
	
	
	@FXML
	public void validateTextEntry() {
		
		try {
		Double dYTW = Double.parseDouble(txtYearsToWork.getText());
		Double dYR = Double.parseDouble(txtYearsRetired.getText());
		Double dRI = Double.parseDouble(txtRequiredIncome.getText());
		Double dMSSI = Double.parseDouble(txtMonthlySSI.getText());
		
		} catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("One or more errors found");
			alert.setContentText("Make sure all inputs are numbers and are not empty");

			alert.showAndWait();
		}
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		
		validateTextEntry();
		
		Retirement rtm = new Retirement();
		rtm.setiYearsToWork(Integer.parseInt(txtYearsToWork.getText()));
		rtm.setdAnnualReturnWorking(spnrAnnualReturnWorking.getValue());
		rtm.setiYearsRetired(Integer.parseInt(txtYearsRetired.getText()));
		rtm.setdAnnualReturnRetired(spnrAnnualReturnRetired.getValue());
		rtm.setdRequiredIncome(Double.parseDouble(txtRequiredIncome.getText()));
		rtm.setdMonthlySSI(Double.parseDouble(txtMonthlySSI.getText()));
		
		String TotalAmountSaved = String.format("%.2f", rtm.TotalAmountSaved());
	    String AmountToSave = String.format("%.2f", rtm.AmountToSave());
			
		lblSaveEachMonth.setText("$ " + AmountToSave);
		lblNeedSaved.setText("$ " + TotalAmountSaved.substring(1));
	}
	
}
