package HW17;

public class InvalidAccountException extends Exception{
	String message;
	InvalidAccountException(String accountType){
		this.message = accountType + " is not a valid account type";
	}
	InvalidAccountException(int accountNumber){
		this.message = "Error: " + accountNumber + " does not exist";
	}
	
	public String getMessage() {
		return message;
	}
}
