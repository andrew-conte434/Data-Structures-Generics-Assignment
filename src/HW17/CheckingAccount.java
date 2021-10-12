package HW17;

public class CheckingAccount extends Account{
	
	public CheckingAccount(Depositor depositor, int accountNumber, String accountType,
			double accountBalance){
		super(depositor, accountNumber, accountType, accountBalance);
	}
	
	//copy constructor
	public CheckingAccount(CheckingAccount account) {
		super(account);
	}
	
	//no-arg constructor
	public CheckingAccount() {
		super();
		this.accountType = "Checking";
	}
}
