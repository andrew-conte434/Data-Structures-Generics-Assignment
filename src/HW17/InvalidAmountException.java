package HW17;

public class InvalidAmountException extends Exception{
	String message;
	
	public InvalidAmountException(double amount) {
		message = "Error: " + amount + " is not a valid amount - try again";
	}
	
	public String getMessage() {
		return message;
	}
}
