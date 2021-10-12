package HW17;

import java.util.Comparator;

public class compareByBalance implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		Double balance1 = o1.getAccountBalance();
		Double balance2 = o2.getAccountBalance();
		
		if(balance1.compareTo(balance2) == 0) {
			Integer acctNum1 = o1.getAccountNumber();
			Integer acctNum2 = o2.getAccountNumber();
			return acctNum1.compareTo(acctNum2);
		}
		
		return balance1.compareTo(balance2);
	}

}
