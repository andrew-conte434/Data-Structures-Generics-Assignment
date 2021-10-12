package HW17;

public class InvalidDeletionException extends Exception{
	String message;
	InvalidDeletionException(double balance){
		message = String.format("You must have $0 balance to delete your account%n"
							  + "Your current balance is: $%.2f", balance);
	}
	public String getMessage() {
		return message;
	}
}
