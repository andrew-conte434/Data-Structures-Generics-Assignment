package HW17;

public class InvalidSSNException extends Exception{
	String message;
	
	public InvalidSSNException(String ssn) {
		this.message = "Error: " + ssn + " is not a valid Social Security Number - try again";
	}
	
	public String getMessage() {
		return message;
	}
}
