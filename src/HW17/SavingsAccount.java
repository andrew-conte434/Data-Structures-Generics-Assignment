package HW17;

public class SavingsAccount extends Account{
	
	public SavingsAccount(Depositor depositor, int accountNumber, String accountType,
			double accountBalance) {
		super(depositor, accountNumber, accountType, accountBalance);
	}
	
	public SavingsAccount(SavingsAccount account) {
		super(account);
	}
	//no-arg constructor
	public SavingsAccount() {
		super();
		this.accountType = "Savings";
	}
}
