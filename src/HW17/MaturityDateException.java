package HW17;

import java.util.Calendar;

public class MaturityDateException extends Exception {
	String message;
	String date;
	
	public MaturityDateException(Calendar maturityDate, String transactionType) {
		this.message = String.format("Transaction Type: %s%n"
								   + "Error - maturity date not reached%n"
								   + "You cannot make transactions on this account until: %d/%d/%d %n", 
								   transactionType, maturityDate.get(Calendar.MONTH) + 1, maturityDate.get(Calendar.DAY_OF_MONTH), 
									  maturityDate.get(Calendar.YEAR));
	}
	
	public String getMessage() {
		return message;
	}
}
