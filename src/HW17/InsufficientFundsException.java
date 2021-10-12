package HW17;

public class InsufficientFundsException extends Exception{
	String message;
	
	public InsufficientFundsException(Account copy, double amount) {
		message = String.format("Error: Insufficient funds - try again%n"
							  + "Withdrawal Amount: $%.2f%n"
							  + "Current Balance: $%.2f%n", amount, copy.getAccountBalance());
	}
	
	public String getMessage() {
		return message;
	}
}
