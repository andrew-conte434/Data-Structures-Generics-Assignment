package HW17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Bank {
	
	ArrayList<Account> accounts;
	
	ArrayList<Integer> acctNumQuickSortKey;
	ArrayList<Integer> acctNumBubbleSortKey;
	ArrayList<Integer> acctNumInsertionSortKey;
	ArrayList<Integer> ssnQuickSortKey;
	ArrayList<Integer> ssnBubbleSortKey;
	ArrayList<Integer> ssnInsertionSortKey;
	ArrayList<Integer> nameQuickSortKey;
	ArrayList<Integer> nameBubbleSortKey;
	ArrayList<Integer> nameInsertionSortKey;
	ArrayList<Integer> balanceQuickSortKey;
	ArrayList<Integer> balanceBubbleSortKey;
	ArrayList<Integer> balanceInsertionSortKey;
	double totalAccts = 0.0;
	double totalSavings = 0.0;
	double totalChecking = 0.0;
	double totalCD = 0.0;
	
	Bank(){
		
		accounts = new ArrayList<Account>();
	    
	    acctNumQuickSortKey = new ArrayList<Integer>();
	    acctNumBubbleSortKey = new ArrayList<Integer>();
	    acctNumInsertionSortKey = new ArrayList<Integer>();
	    ssnQuickSortKey = new ArrayList<Integer>();
	    ssnBubbleSortKey = new ArrayList<Integer>();
	    ssnInsertionSortKey = new ArrayList<Integer>();
	    nameQuickSortKey = new ArrayList<Integer>();
	    nameBubbleSortKey = new ArrayList<Integer>();
	    nameInsertionSortKey = new ArrayList<Integer>();
	    balanceQuickSortKey = new ArrayList<Integer>();
	    balanceBubbleSortKey = new ArrayList<Integer>();
	    balanceInsertionSortKey = new ArrayList<Integer>();
	    
	}
	
	/**
	 * Method readInitial()
	 * Input: Strings first, last, ssn, accountNumber, accountType
	 * 		  Double accountBalance
	 * Process: Creates instances of Account and Depositer
	 * 			Checks the account type
	 * 			Creates account 
	 */
	public void createAccount(String first, String last, String ssn, 
			int accountNumber, String accountType, double accountBalance,
			Calendar maturityDate, TransactionTicket ticket) {
		
		Depositor depositor = new Depositor(first, last, ssn);
		TransactionReceipt receipt;
		
		if(accountType.equals("Checking")) {
			Account newAccount = new CheckingAccount(depositor, accountNumber,
					accountType, accountBalance);
			accounts.add(newAccount);
			receipt = new TransactionReceipt(ticket, true, newAccount.getAccountBalance(), 
					newAccount.getAccountBalance());
			accounts.get(accounts.size() - 1).addReceipt(receipt);
			totalAccts += accountBalance;
			totalChecking += accountBalance;
		}
		
		if(accountType.equals("Savings")) {
			Account newAccount = new SavingsAccount(depositor, accountNumber,
					accountType, accountBalance);
			accounts.add(newAccount);
			receipt = new TransactionReceipt(ticket, true, newAccount.getAccountBalance(), 
					newAccount.getAccountBalance());
			accounts.get(accounts.size() - 1).addReceipt(receipt);
			totalAccts += accountBalance;
			totalSavings += accountBalance;
		}
		
		if(accountType.equals("CD")) {
			Account newAccount = new CDAccount(depositor, accountNumber,
					accountType, accountBalance, maturityDate);
			accounts.add(newAccount);
			receipt = new TransactionReceipt(ticket, true, newAccount.getAccountBalance(), 
					newAccount.getAccountBalance());
			accounts.get(accounts.size() - 1).addReceipt(receipt);
			totalAccts += accountBalance;
			totalCD += accountBalance;
		}
		
		//adds index to sort keys
		
		acctNumQuickSortKey.add(acctNumQuickSortKey.size());
		acctNumBubbleSortKey.add(acctNumBubbleSortKey.size());
		acctNumInsertionSortKey.add(acctNumInsertionSortKey.size());
		ssnQuickSortKey.add(ssnQuickSortKey.size());
		ssnBubbleSortKey.add(ssnBubbleSortKey.size());
		ssnInsertionSortKey.add(ssnInsertionSortKey.size());
		nameQuickSortKey.add(nameQuickSortKey.size());
		nameBubbleSortKey.add(nameBubbleSortKey.size());
		nameInsertionSortKey.add(nameInsertionSortKey.size());
		balanceQuickSortKey.add(balanceQuickSortKey.size());
		balanceBubbleSortKey.add(balanceBubbleSortKey.size());
		balanceInsertionSortKey.add(balanceInsertionSortKey.size());
		
		sortAll();
		}
	
	/**
	 * Method addAccount
	 * Input:
	 * 	references to account information
	 * 	reference to maturity date Calendar object
	 * 	reference to TransactionTicket
	 * Process
	 * 	creates TransactionTicket object
	 * 	checks accountType and adds new Account object to ArrayList of accounts
	 * 	initializes TransactionTicket and adds it to new account's Arraylist of receipts
	 * Output:
	 * 	returns copy of receipt
	 */
	public TransactionReceipt addAccount(int accountNumber, Depositor depositor,
			String accountType, double initialBalance, Calendar maturityDate,
			TransactionTicket ticket) {
		TransactionReceipt receipt;
		//index of new account
		int index = accounts.size();
		
		if(accountType.equals("Checking")) {
			accounts.add(new CheckingAccount());
		}
		
		if(accountType.equals("Savings")) {
			accounts.add(new SavingsAccount());
		}
		
		if(accountType.equals("CD")) {
			accounts.add(new CDAccount(maturityDate));
			accounts.get(index).setAccountBalance(initialBalance);
			totalAccts += initialBalance;
			totalCD += initialBalance;
		}
		
		accounts.get(index).setDepositor(depositor); //sets depositor of new account
		accounts.get(index).setAccountNumber(accountNumber); //sets account number of new account
		
		//adds index to sort keys
		
		acctNumQuickSortKey.add(acctNumQuickSortKey.size());
		acctNumBubbleSortKey.add(acctNumBubbleSortKey.size());
		acctNumInsertionSortKey.add(acctNumInsertionSortKey.size());
		ssnQuickSortKey.add(ssnQuickSortKey.size());
		ssnBubbleSortKey.add(ssnBubbleSortKey.size());
		ssnInsertionSortKey.add(ssnInsertionSortKey.size());
		nameQuickSortKey.add(nameQuickSortKey.size());
		nameBubbleSortKey.add(nameBubbleSortKey.size());
		nameInsertionSortKey.add(nameInsertionSortKey.size());
		balanceQuickSortKey.add(balanceQuickSortKey.size());
		balanceBubbleSortKey.add(balanceBubbleSortKey.size());
		balanceInsertionSortKey.add(balanceInsertionSortKey.size());
		
		sortAll();
		
		receipt = new TransactionReceipt(ticket, true, initialBalance, initialBalance);
		receipt.setMaturityDate(maturityDate);
		return new TransactionReceipt(receipt);
	}
	
	private void sortAll() {
		sortAcctsByAcctNum();
		sortAcctsBySSN();
		sortAcctsByName();
		sortAcctsByBalance();
	}
	
	private void sortAcctsByAcctNum() {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < accounts.size(); i++) {
			values.add(accounts.get(i).getAccountNumber());
		}
		genericInsertionSort(acctNumInsertionSortKey, values);
		genericBubbleSort(acctNumBubbleSortKey, values);
		genericQuickSort(acctNumQuickSortKey, values, 0, acctNumQuickSortKey.size() - 1);
	}
	
	private void sortAcctsBySSN() {
		ArrayList<String> values = new ArrayList<String>();
		for(int i = 0; i < accounts.size(); i++) {
			values.add(accounts.get(i).getDepositor().getSSN());
		}
		genericInsertionSort(ssnInsertionSortKey, values);
		genericBubbleSort(ssnBubbleSortKey, values);
		genericQuickSort(ssnQuickSortKey, values, 0, ssnQuickSortKey.size() - 1);
	}
	
	private void sortAcctsByName() {
		ArrayList<Name> values = new ArrayList<Name>();
		for(int i = 0; i < accounts.size(); i++) {
			values.add(accounts.get(i).getDepositor().getName());
		}
		genericInsertionSort(nameInsertionSortKey, values);
		genericBubbleSort(nameBubbleSortKey, values);
		genericQuickSort(nameQuickSortKey, values, 0, nameQuickSortKey.size() - 1);
	}
	
	private void sortAcctsByBalance() {
		ArrayList<Double> values = new ArrayList<Double>();
		for(int i = 0; i < accounts.size(); i++) {
			values.add(accounts.get(i).getAccountBalance());
		}
		genericInsertionSort(balanceInsertionSortKey, values);
		genericBubbleSort(balanceBubbleSortKey, values);
		genericQuickSort(balanceQuickSortKey, values, 0, balanceQuickSortKey.size() - 1);
	}
	/**
	private void insertionSortGeneric(ArrayList<Integer> sortKey, Comparator<Account> comparator) {
		int cand, index;
		for(int pos = 1; pos < nameKey.size(); pos++) {
			cand = pos;
			index = sortKey.get(pos);
			
			while(cand > 00 && comparator.compare(accounts.get(index), accounts.get(sortKey.get(cand - 1))) < 0){
				sortKey.set(cand, sortKey.get(cand - 1));
				cand--;
			}
			sortKey.set(cand,  index);
		}
	}
	**/
	
	private <T extends Comparable<T>> void genericInsertionSort(ArrayList<Integer> sortKey,
			ArrayList<T> values) {
		int cand, index;
		for(int pos = 1; pos < sortKey.size(); pos++) {
			cand = pos;
			index = sortKey.get(pos);
			
			while(cand > 0 && values.get(index).compareTo(values.get(sortKey.get(cand - 1))) < 0){
				sortKey.set(cand, sortKey.get(cand - 1));
				cand--;
			}
			//compares account numbers if values are equal
			Comparator<Account> comparator = new compareByAcctNum();
			while(cand > 0 && values.get(index).compareTo(values.get(sortKey.get(cand - 1))) == 0
						   && comparator.compare(accounts.get(index), accounts.get(sortKey.get(cand - 1))) < 0) {
				sortKey.set(cand, sortKey.get(cand - 1));
				cand--;
			}
			sortKey.set(cand, index);
		}
	}
	
	private <T extends Comparable<T>> void genericBubbleSort(ArrayList<Integer> sortKey, 
			ArrayList<T> values){
		int index1, index2;
		boolean swapped;
		do {
			swapped = false;
			for(int i = 0; i < sortKey.size() - 1; i++) {
				index1 = sortKey.get(i);
				index2 = sortKey.get(i + 1);
				if(values.get(index1).compareTo(values.get(index2)) > 0) {
					sortKey.set(i, index2);
					sortKey.set(i + 1, index1);
					swapped = true;
				} else if(values.get(index1).compareTo(values.get(index2)) == 0) {
					Comparator<Account> comparator = new compareByAcctNum();
					if(comparator.compare(accounts.get(index1), accounts.get(index2)) > 0) {
						sortKey.set(i, index2);
						sortKey.set(i + 1, index1);
						swapped = true;
					}
				}
			}
		} while(swapped);
	}
	
	private <T extends Comparable<T>> void genericQuickSort(ArrayList<Integer> sortKey, 
			ArrayList<T> values, int start, int end) {
		int p;
		if(start <= end) {
			p = genericPartition(sortKey, values, start, end);
			genericQuickSort(sortKey, values, start, p - 1);
			genericQuickSort(sortKey, values, p + 1, end);
		}
	}
	
	private <T extends Comparable<T>> int genericPartition(ArrayList<Integer> sortKey, 
			ArrayList<T> values, int start, int end) {
		int pivotPosition = start;
		int index;
		for(int i = pivotPosition + 1; i <= end; i++) {
			index = sortKey.get(i);
			if(values.get(index).compareTo(values.get(sortKey.get(pivotPosition))) < 0) {
				//move i to the right of the pivot value
				sortKey.set(i, sortKey.get(pivotPosition + 1));
				sortKey.set(pivotPosition + 1, index);
				//swap i with the pivot value
				sortKey.set(pivotPosition + 1, sortKey.get(pivotPosition));
				sortKey.set(pivotPosition, index);
				
				pivotPosition++;
			} else if(values.get(index).compareTo(values.get(sortKey.get(pivotPosition))) == 0) {
				//compare by account number if values are equal
				Comparator<Account> comparator = new compareByAcctNum();
				if(comparator.compare(accounts.get(index), accounts.get(sortKey.get(pivotPosition))) < 0) {
					//move i to the right of the pivot value
					sortKey.set(i, sortKey.get(pivotPosition + 1));
					sortKey.set(pivotPosition + 1, index);
					//swap i with the pivot value
					sortKey.set(pivotPosition + 1, sortKey.get(pivotPosition));
					sortKey.set(pivotPosition, index);
				}
			}
		}
		return pivotPosition;
		
	}

	//returns sum of balances in all accounts
	public double getTotalAccts() {
		return totalAccts;
	}
	
	//returns sum of balances in all savings accounts
	public double getSavingsAccts() {
		return totalSavings;
	}
	
	//returns sum of balances in all checking accounts
	public double getCheckingAccounts() {
		return totalChecking;
	}
	
	//returns sum of balances in all CD accounts
	public double getCDAccts() {
		return totalCD;
	}
	/**
	 * public getAccountsSize()
	 * Input: none
	 * Process: returns size of ArrayList accounts
	 */
	public int getAccountsSize() {
		return accounts.size();
	}
	
	//getter methods to return each sort key
	//ssn keys
	public ArrayList<Integer> getSSNBubbleKey(){ return ssnBubbleSortKey;}
	public ArrayList<Integer> getSSNInsertionKey(){ return ssnInsertionSortKey;}
	public ArrayList<Integer> getSSNQuickKey(){ return ssnQuickSortKey;}
	
	
	//name keys
	public ArrayList<Integer> getNameBubbleKey(){ return nameBubbleSortKey;}
	public ArrayList<Integer> getNameInsertionKey(){ return nameInsertionSortKey;}
	public ArrayList<Integer> getNameQuickKey(){ return nameQuickSortKey;}
	
	
	//account number keys
	public ArrayList<Integer> getAcctNumBubbleKey(){ return acctNumBubbleSortKey;}
	public ArrayList<Integer> getAcctNumInsertionKey(){ return acctNumInsertionSortKey;}
	public ArrayList<Integer> getAcctnumQuickKey(){ return acctNumQuickSortKey;}
	
	//account balance keys
	public ArrayList<Integer> getBalanceBubbleKey(){
		sortAcctsByBalance();
		return balanceBubbleSortKey;}
	public ArrayList<Integer> getBalanceInsertionKey(){ 
		sortAcctsByBalance();
		return balanceInsertionSortKey;}
	public ArrayList<Integer> getBalanceQuickKey(){ 
		sortAcctsByBalance();
		return balanceQuickSortKey;}
	
	/**
	 * public printAccount
	 * Input: int index
	 * Process: returns accounts(index).toString()
	 */
	public String printAccount(int index) {
		return accounts.get(index).toString();
	}
	
	/**
	 * Method: findAcctByAcctNum
	 * Input:
	 * 	int accountNumber
	 * Process:
	 * 	Performs a binary search on account number sort key
	 * 	returns index of account if found
	 * 	returns -1 if not found
	 */
	public int findAcctByAcctNum(int accountNumber) {
		int mid, index, cand;
		int start = 0;
		int end = accounts.size() - 1;
		while(start <= end) {
			
			mid =  start + ((end - start) / 2);
			index = acctNumQuickSortKey.get(mid);
			cand = accounts.get(index).getAccountNumber();
			
			if(accountNumber < cand) {
				end = mid - 1;
			} else if (accountNumber > cand){
				start = mid + 1;
			} else {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * Method getAccountsSSN
	 * Input:
	 * 	Social Security Number
	 * Process:
	 * 	Searches accounts ArrayList for all accounts under given SSN
	 * Output:
	 * 	returns ArrayList accountsSSN
	 */
	public ArrayList<Account> getAccountsSSN(String ssn) {
		ArrayList<Account> accountsSSN = new ArrayList<Account>();
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getDepositor().getSSN().equals(ssn)) {
				accountsSSN.add(accounts.get(i));
			}
		}
		
		//returns copy of ArrayList containing found accounts
		return new ArrayList<Account>(accountsSSN);
	}

	/**
	 * Method getAccount()
	 * Input:
	 * 	int index
	 * Process:
	 * 	creates new Account object using copy constructor
	 * 	returns copy
	 */
	public Account getAccount(int index) {
		Account copy = accounts.get(index);
		switch(copy.getAccountType()) {
			case "Savings":
				SavingsAccount saAccount = new SavingsAccount((SavingsAccount)copy);
				copy = new Account(saAccount);
				break;
			case "Checking":
				CheckingAccount chAccount = new CheckingAccount((CheckingAccount)copy);
				copy = new Account(chAccount);
				break;
			case "CD":
				CDAccount cdAccount = new CDAccount((CDAccount)copy);
				copy = new Account(cdAccount);
				break;
		}
		return copy;
	}
	
	/**
	 * Method getBalance()
	 * Input:
	 * 	reference to transaction ticket
	 * 	reference to index of account in ArrayList of accounts
	 * Process:
	 * 	creates TransactionReceipt object and assigns it the return value
	 * 	of account.getBalance()
	 * Output:
	 * 	returns copy of TransactionReceipt
	 */
	public TransactionReceipt getBalance(TransactionTicket ticket, int index) throws IOException{
		TransactionReceipt receipt = accounts.get(index).getBalanceInquiry(ticket);
		return new TransactionReceipt(receipt);
	}
	
	/**
	 * Method makeDeposit()
	 * Input:
	 * 	reference to transaction ticket
	 * 	reference to index of account in ArrayList of accounts
	 * Process:
	 * 	creates copy of account
	 * 	checks if account is a CDAccount. If yes, checks its maturity date
	 * 	if maturity date has not passed, throws MaturityDateException and exits method.
	 * 	Creates TransactionReceipt object and assigns it the return value of account.makeDeposit()
	 * Output:
	 * 	returns copy of TransactionReceipt
	 */
	public TransactionReceipt makeDeposit(TransactionTicket ticket, int index) {
		Account copy = this.getAccount(index);
		TransactionReceipt receipt = copy.makeDeposit(ticket);
		this.acceptChanges(copy, index, receipt);
		this.addAmount(copy, ticket);
		return new TransactionReceipt(receipt);
	}
	
	/**
	 * Method makeWithdrawal()
	 * Input:
	 * 	reference to transaction ticket
	 * 	reference to index of account in ArrayList of accounts
	 * Process:
	 * 	creates copy of account
	 * 	checks if account is a CDAccount. If yes, checks its maturity date
	 * 	if maturity date has not passed, throws MaturityDateException and exits method.
	 * 	Creates TransactionReceipt object and assigns it the return value of account.makeWithdrawal()
	 * Output:
	 * 	returns copy of TransactionReceipt
	 */
	public TransactionReceipt makeWithdrawal(TransactionTicket ticket, int index) throws IOException{
		Account copy = this.getAccount(index); //creates account copy
		TransactionReceipt receipt = copy.makeWithdrawal(ticket); //makes withdrawal from copy of account
		this.acceptChanges(copy, index, receipt); //makes changes to original account
		this.addAmount(copy, ticket); //subtracts transaction amount from total amount in bank
		return new TransactionReceipt(receipt);
	}
	
	/**
	 * Method clearCheck()
	 * Input:
	 * 	ticket - reference to TransactionTicket object
	 * 	index - index of account in ArrayList of accounts
	 * 	check - reference to Check object
	 * Process
	 * 	creates copy of account at given index
	 * 	creates TransactionReceipt object and assigns it to the return value of Account.clearCheck
	 * Output
	 * 	returns copy of TransactionReceipt
	 */
	public TransactionReceipt clearCheck(TransactionTicket ticket, int index, Check check) throws IOException{
		Account copy = new Account(accounts.get(index));
		TransactionReceipt receipt = copy.clearCheck(ticket, check);
		this.acceptChanges(copy, index, receipt);
		
		//subtracts bounce fee from static variable if transaction failed
		if(!receipt.getSuccessIndicatorFlag()) {
			totalAccts -= 1.5;
			switch(copy.getAccountType()) {
				case "Checking":
					totalChecking -= 1.5;
					break;
				case "Savings":
					totalSavings = 1.5;
					break;
				case "CD":
					totalCD = 1.5;
					break;
			}
			return new TransactionReceipt(receipt);
		}
		
		this.addAmount(copy, ticket);
		return new TransactionReceipt(receipt);
	}
	
	/**
	 * Method deleteAccount()
	 * Input:
	 * 	reference to index number of account to be deleted
	 * Process:
	 * 	searches ArrayList of accounts for given account number
	 * 	deletes account when found
	 * 	cancels transaction if account balance is greater than zero
	 * Output:
	 * 	returns copy of Transaction Receipt
	 */
	public TransactionReceipt deleteAccount(TransactionTicket ticket, int index) {
		TransactionReceipt receipt;
		Account copy = new Account(accounts.get(index));
		accounts.remove(index);
		
		//removes highest index
		int max = Collections.max(acctNumQuickSortKey);
		
		acctNumQuickSortKey.remove(acctNumQuickSortKey.indexOf(max));
		acctNumBubbleSortKey.remove(acctNumBubbleSortKey.indexOf(max));
		acctNumInsertionSortKey.remove(acctNumInsertionSortKey.indexOf(max));
		ssnQuickSortKey.remove(ssnQuickSortKey.indexOf(max));
		ssnBubbleSortKey.remove(ssnBubbleSortKey.indexOf(max));
		ssnInsertionSortKey.remove(ssnInsertionSortKey.indexOf(max));
		nameQuickSortKey.remove(nameQuickSortKey.indexOf(max));
		nameBubbleSortKey.remove(nameBubbleSortKey.indexOf(max));
		nameInsertionSortKey.remove(nameInsertionSortKey.indexOf(max));
		balanceQuickSortKey.remove(balanceQuickSortKey.indexOf(max));
		balanceBubbleSortKey.remove(balanceBubbleSortKey.indexOf(max));
		balanceInsertionSortKey.remove(balanceInsertionSortKey.indexOf(max));
		
		sortAll();
		
		receipt = new TransactionReceipt(ticket, true, 0, 0);
		return new TransactionReceipt(receipt);
	}

	/**
	 * Method closeAccount()
	 * Input:
	 * 	reference to TransactionTicket object
	 * 	index of account
	 * Process:
	 * 	creates TransactionReceipt object and assigns it the return value of account.setStatus
	 * Output:
	 * 	returns copy of TransactionReceipt
	 */
	public TransactionReceipt closeAccount(TransactionTicket ticket, int index) {
		TransactionReceipt receipt;
		Account copy = new Account(accounts.get(index));
		receipt = copy.closeAccount(ticket);
		this.acceptChanges(copy, index, receipt);
		return receipt;
	}
	
	/**
	 * Method reopenAccount()
	 * Input:
	 * 	reference to TransactionTicket object
	 * 	index of account
	 * Process:
	 * 	creates TransactionReceipt object and assigns it the return value of account.setStatus
	 * Output:
	 * 	returns copy of TransactionReceipt
	 */
	public TransactionReceipt reopenAccount(TransactionTicket ticket, int index) {
		TransactionReceipt receipt;
		Account copy = new Account(accounts.get(index));
		receipt = copy.reopenAccount(ticket);
		this.acceptChanges(copy, index, receipt);
		return receipt;
	}
	/**
	 * Method acceptChanges()
	 * Input:
	 * 	reference to copy of Account object
	 * 	reference to index of account in accounts ArrayList
	 * 	reference to transaction receipt
	 * Process:
	 * 	updates original database with changes after successful transaction
	 * 	if account is CD Account, sets maturity date to equal new maturity
	 * 	date on receipt
	 * Returns:
	 * 	void
	 */
	public void acceptChanges(Account copy, int index, TransactionReceipt receipt) {
		if(copy.equals(accounts.get(index))) {
			accounts.get(index).setAccountBalance(receipt.getPostTransactionBalance());
			accounts.get(index).setStatus(copy.getStatus());
			if(copy.getAccountType().equals("CD")) {
				Calendar newMaturityDate = receipt.getMaturityDate();
				((CDAccount)accounts.get(index)).setMaturitydate(newMaturityDate);
			}
		}
	}
	
	/**
	 * Method addFailureReceipt
	 * Input:
	 * 	index - integer value of account's index
	 * 	receipt - reference to TransactionReceipt object
	 * Process:
	 * 	Adds receipt to account's ArrayList of TransactionReceipts
	 * Returns:
	 * 	void
	 */
	public void addFailureReceipt(int index, TransactionReceipt receipt) {
		accounts.get(index).transactionReceipts.add(receipt);
	}
	
	/**
	 * Method addAmount
	 * Input:
	 * 	copy - reference to Account object
	 * 	ticket - reference to TransactionTicket object
	 * Process
	 * 	adds amount of transaction to bank's static variables
	 * 	subtracts if transaction type is withdrawal or clear check
	 * Output:
	 * 	void
	 */
	public void addAmount(Account copy, TransactionTicket ticket) {
		String type = ticket.getTypeOfTransaction();
		double amount = ticket.getAmountOfTransaction();
		if(type.equals("Deposit")) {
			totalAccts += amount;
			switch(copy.getAccountType()) {
				case "Checking":
					totalChecking += amount;
					break;
				case "Savings":
					totalSavings += amount;
					break;
				case "CD":
					totalCD += amount;
					break;
			}
		}
		
		if(type.equals("Withdrawal") || type.equals("Clear Check")) {
			totalAccts -= amount;
			switch(copy.getAccountType()) {
				case "Checking":
					totalChecking -= amount;
					break;
				case "Savings":
					totalSavings -= amount;
					break;
				case "CD":
					totalCD -= amount;
					break;
			}
		}
	}
}