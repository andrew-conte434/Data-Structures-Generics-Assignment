package HW17;

public class BouncedCheckException extends Exception{
	String message;
	public BouncedCheckException() {
		this.message = "Insufficient funds - bounce fee of $1.50 charged";
	}
	
	public String getMessage() {
		return message;
	}
}
