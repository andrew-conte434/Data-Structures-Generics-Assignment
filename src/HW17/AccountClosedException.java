package HW17;

public class AccountClosedException extends Exception{
	String message;
	public AccountClosedException(int accountNumber) {
		message = "Error: " + accountNumber + " is closed - try again";
		}
	
	public String getMessage() {
		return message;
	}
}
