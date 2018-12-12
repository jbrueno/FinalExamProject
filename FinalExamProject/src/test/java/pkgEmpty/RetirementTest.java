package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

public class RetirementTest {

	@Test
	public void TestPMT() {
		
		double dAnnualReturnWorking = 0.07;
		int iYearsToWork = 40;
		double dAnnualReturnRetired = 0.02;
		int iYearsRetired = 20;
		double dRequiredIncome = 10000.00;
		double dMonthlySSI = 2642.00;
		double ExpectedPayment = 554.13;

		Retirement rtm = new Retirement();
		rtm.setiYearsToWork(iYearsToWork);
		rtm.setdAnnualReturnWorking(dAnnualReturnWorking);
		rtm.setdAnnualReturnRetired(dAnnualReturnRetired);
		rtm.setiYearsRetired(iYearsRetired);
		rtm.setdRequiredIncome(dRequiredIncome);
		rtm.setdMonthlySSI(dMonthlySSI);
		
		double PMT = rtm.AmountToSave();
		
		assertEquals(ExpectedPayment, Math.abs(PMT), 0.01);
	}
	
	
	@Test
	public void TestPV() {
		
		double dAnnualReturnRetired = 0.02;
		int iYearsRetired = 20;
		double dRequiredIncome = 10000.00;
		double dMonthlySSI = 2642.00;
		double ExpectedTotal = 1454485.55;
		
		Retirement rtm = new Retirement();
		rtm.setdAnnualReturnRetired(dAnnualReturnRetired);
		rtm.setiYearsRetired(iYearsRetired);
		rtm.setdRequiredIncome(dRequiredIncome);
		rtm.setdMonthlySSI(dMonthlySSI);
		
		double PV = rtm.TotalAmountSaved();
		
		assertEquals(ExpectedTotal, Math.abs(PV), 0.01);
	}
	

}
