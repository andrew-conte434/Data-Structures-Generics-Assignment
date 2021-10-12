package HW17;

import java.util.Calendar;

public class PostDatedCheckException extends Exception{

	String message;
	
	public PostDatedCheckException(Calendar date) {
		message = String.format("Error: Post-dated check%n"
							  + "Please cash check on or after: %d/%d/%d", 
							  date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), 
							  date.get(Calendar.YEAR));
	}
	
	public String getMessage() {
		return message;
	}
}
