package HW17;

import java.lang.Exception;

public class InvalidMenuSelectionException extends Exception{
	String message;
	public InvalidMenuSelectionException(char choice) {
		this.message = "Error: " + choice + " is an invalid selection -  try again";
		
	}
	
	public String getMessage() {
		return message;
	}
}
