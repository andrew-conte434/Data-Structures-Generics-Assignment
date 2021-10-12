package HW17;

import java.util.Comparator;

public class compareByName implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		
		Name name1 = o1.getDepositor().getName();
		Name name2 = o2.getDepositor().getName();
	
		if(name1.equals(name2)) {
			Integer acctNum1 = o1.getAccountNumber();
			Integer acctNum2 = o2.getAccountNumber();
			return acctNum1.compareTo(acctNum2);
		}
		
		return name1.compareTo(name2);
	}

}
