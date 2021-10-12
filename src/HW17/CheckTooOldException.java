package HW17;

import java.util.Calendar;

public class CheckTooOldException extends Exception{
	String message;
	
	public CheckTooOldException(Calendar date, Calendar dateOfCheck) {
		message = String.format("Error: check too old - no checks before: %d/%d/%d may be cashed%n"
							  + "Date of check: %d/%d/%d%n", 
				date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR),
				dateOfCheck.get(Calendar.MONTH) + 1, dateOfCheck.get(Calendar.DAY_OF_MONTH),
				dateOfCheck.get(Calendar.YEAR));
	}
	
	public String getMessage() {
		return message;
	}
}
