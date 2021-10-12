package HW17;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CDAccount extends Account{
	Calendar maturityDate = Calendar.getInstance();
	
	public CDAccount(Depositor depositor, int accountNumber, String accountType,
			double accountBalance, Calendar maturityDate) {
		super(depositor, accountNumber, accountType, accountBalance);
		this.maturityDate.setTime(maturityDate.getTime());
	}
	
	//copy constructor
	public CDAccount(CDAccount account) {
		super(account);
		maturityDate = account.getMaturityDate();
		}
	
	public CDAccount(Calendar maturityDate) {
		super();
		this.maturityDate.setTime(maturityDate.getTime());
		this.accountType = "CD";
	}
	
	//returns maturityDate
	public Calendar getMaturityDate() {
		return maturityDate;
	}
	
	//sets new maturity date
	public void setMaturitydate(Calendar newDate) {
		maturityDate.setTime(newDate.getTime());
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = maturityDate.getTime();
		String formattedDate = sdf.format(date);
		return (super.toString() + String.format("%15s", formattedDate));
	}
}
