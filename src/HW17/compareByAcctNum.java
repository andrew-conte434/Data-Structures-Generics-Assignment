package HW17;

import java.util.Comparator;

public class compareByAcctNum implements Comparator<Account>{

	@Override
	public int compare(Account o1, Account o2) {
		Integer acctNum1 = o1.getAccountNumber();
		Integer acctNum2 = o2.getAccountNumber();
		return acctNum1.compareTo(acctNum2);
	}

}
