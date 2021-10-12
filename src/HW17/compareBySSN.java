package HW17;

import java.util.Comparator;

public class compareBySSN implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		String ssn1 = o1.getDepositor().getSSN();
		String ssn2 = o2.getDepositor().getSSN();

		if(ssn1.compareTo(ssn2) == 0) {
			Integer acctNum1 = o1.getAccountNumber();
			Integer acctNum2 = o2.getAccountNumber();
			return acctNum1.compareTo(acctNum2);
		}
		
		return ssn1.compareTo(ssn2);
	}

}
