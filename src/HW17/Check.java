package HW17;

import java.util.Calendar;

public class Check {
	double checkAmount;
	Calendar dateOfCheck = Calendar.getInstance();
	int accountNumber;
	
	public Check(double checkAmount, Calendar dateOfCheck, int accountNumber) {
		this.checkAmount = checkAmount;
		this.dateOfCheck.setTime(dateOfCheck.getTime());
		this.accountNumber = accountNumber;
	}
	
	public Check(Check check) {
		this.checkAmount = check.getCheckAmount();
		this.dateOfCheck.setTime(check.getDateOfCheck().getTime());
		this.accountNumber = check.accountNumber;
	}
	
	//returns checkAmount
	public double getCheckAmount() {
		return checkAmount;
	}
	
	//returns dateOfCheck
	public Calendar getDateOfCheck() {
		return dateOfCheck;
	}
	
	//returns accountNumber
	public int getAccountNumber() {
		return accountNumber;
	}
}
