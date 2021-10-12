package HW17;

public class AccountAlreadyExistsException extends Exception{
	String message;
	AccountAlreadyExistsException(int accountNumber){
		message = String.format("Transaction Type: Open Account%n"
							  + "Error: %d already exists", accountNumber);
	}
	
	public String getMessage() {
		return message;
	}
}
