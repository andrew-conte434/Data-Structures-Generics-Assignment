package HW17;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class HW17 {
	public static void main(String[] args) throws IOException, ParseException
	{
		//variable declarations
		Bank bank = new Bank();								//instantiate the Bank
	    char choice;										//menu item selected
	    boolean notDone = true;								//loop control flag
	    
	    
	    // open input test cases file
	    File testFile = new File("testCases.txt");
	    
	    //create Scanner object
	    Scanner kybd = new Scanner(testFile);
	    //Scanner kybd = new Scanner(System.in);

	    // open the output file
	    PrintWriter outFile = new PrintWriter("myoutputHW17.txt");
	    //PrintWriter outFile = new PrintWriter(System.out);

	    /* first part */
	    /* fill and print initial database */
	    readAccts(bank);
	    
	    //print initial database sorted and unsorted
	    printAccts(bank, outFile);
	    
	    /* second part */
	    /* prompts for a transaction and then */
	    /* call functions to process the requested transaction */
	    do
	    {
	       // menu();
	        choice = kybd.next().charAt(0);
	        try
	        {
	        	switch(choice)
	        	{
	            	case 'q':
	            	case 'Q':
	            		notDone = false;
	            		printAccts(bank,outFile);
	            		break;
	            	case 'b':
	            	case 'B':
	            		balance(bank,outFile,kybd);
	            		break;
	            	case 'd':
	            	case 'D':
	            		deposit(bank,outFile,kybd);
	            		break;
	            	case 'w':
	            	case 'W':
	            		withdrawal(bank,outFile,kybd);
	            		break;
	            	case 'c':
	            	case 'C':
	            		clearCheck(bank,outFile,kybd);
	            		break;
	            	case 'n':
	            	case 'N':
	            		newAcct(bank,outFile,kybd);
	            		break;
	            	case 's':
	            	case 'S':
	            		closeAcct(bank,outFile,kybd);
	            		break;
	            	case 'r':
	            	case 'R':
	            		reopenAcct(bank,outFile,kybd);
	            		break;
	            	case 'x':
	            	case 'X':
	            		deleteAcct(bank,outFile,kybd);
	            		break;
	            	case 'i':
	            	case 'I':
	            		acctInfo(bank,outFile,kybd);
	            		break;
	            	case 'h':
	            	case 'H':
	            		acctInfoWithTransactionHistory(bank,outFile,kybd);
	            		break;
	            	default:
	            		throw new InvalidMenuSelectionException(choice);
        		}
	        }
	        catch (InvalidMenuSelectionException ex) {
        		outFile.println(ex.getMessage());
        		outFile.println();
        		outFile.flush();
	        }
	        
	        // give user a chance to look at output before printing menu
	        //pause(kybd);
	    } while (notDone);
	    
	    //close the output file
	    outFile.close();
	    
	    //close the test cases input file
	    kybd.close();
	    
	    System.out.println();
	    System.out.println("The program is terminating");
	}

	/* Method readAccts()
	 * Input:
	 *  bank - reference to the Bank object
	 *  maxAccts - maximum number of active accounts allowed
	 * Process:
	 *  Reads the initial database of accounts
	 * Output:
	 *  Fills in the initial array of Account objects within the Bank object
	 *  and also set the initial number of active accounts (stored in the Bank object)
	 */
	public static void readAccts(Bank bank) throws IOException, ParseException {
		
		File initialDatabase = new File("initialDatabase.txt");
		Scanner sc = new Scanner(initialDatabase); 
		
		String first, last, ssn, accountType; //initialize account information
		int accountNumber;
		double accountBalance;
		String strDate = null;
		Calendar maturityDate = Calendar.getInstance();
		TransactionTicket ticket;
		
		//creates formatter to convert String to Date
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		while(sc.hasNextLine()) {
			first = sc.next(); //reads account information
			last = sc.next();
			ssn = sc.next();
			accountNumber = sc.nextInt();
			accountType = sc.next();
			accountBalance = sc.nextDouble();
			if(accountType.equals("CD")) {
				strDate = sc.next();
				//converts date to Calendar object
				maturityDate.setTime(formatter.parse(strDate));          
			} 
			ticket = new TransactionTicket(Calendar.getInstance(), "Open Account", 0, 0);
			
			bank.createAccount(first, last, ssn, accountNumber, accountType, 
					accountBalance, maturityDate, ticket);
			maturityDate.clear();
		}
		
		sc.close();
	}

	/* Method printAccts:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 * Process:
	 *  Prints the database of accounts
	 * Output:
	 *  Prints the database of accounts
	*/
	public static void printAccts(Bank bank, PrintWriter outFile) throws IOException {
		ArrayList<Integer> sortKey;
		printStars(outFile);
		outFile.printf("%10s %10s%15s%15s %15s %15s %15s %n", "First Name", "Last Name",
				"SSN", "Number", "Type", "Balance", "Maturity Date");
		printStars(outFile);
		for(int i = 0; i < bank.getAccountsSize(); i++) {
			outFile.println(bank.printAccount(i));
		}
		
		int index;
		
		sortKey = bank.getSSNBubbleKey();
		
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by SSN(Bubble Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getSSNInsertionKey();
		
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by SSN(Insertion Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getSSNQuickKey();
		
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by SSN(Quick Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getNameBubbleKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Name(Bubble Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getNameInsertionKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Name(Insertion Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getNameQuickKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Name(Quick Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getAcctNumBubbleKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Account Number(Bubble Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getAcctNumInsertionKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Account Number(Insertion Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getAcctnumQuickKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Account Number(Quick Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getBalanceBubbleKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Account Balance(Bubble Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getBalanceInsertionKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Account Balance(Insertion Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		sortKey = bank.getBalanceQuickKey();
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Account Balance(Quick Sort)");
		printStars(outFile);
		index = 0;
		for(int i = 0; i < sortKey.size(); i++) {
			index = (int) sortKey.get(i);
			outFile.println(bank.printAccount(index));
		}
		
		outFile.printf("%nTotal amount in bank: $%.2f%n"
						+ "Total amount in checking accounts: $%.2f%n"
						+ "Total amount in savings accounts: $%.2f%n"
						+ "Total amount in CD accounts: $%.2f%n", bank.getTotalAccts(), bank.getCheckingAccounts(),
						bank.getSavingsAccts(), bank.getCDAccts());
		outFile.println();
	}
	
	/* Method printAcctsBySSNSortKey:
	 * Input:
	 *  bank - reference to the Bank object
	 *  ssnKey - reference to the SSN sort key
	 *  outFile - reference to the output file
	 * Process:
	 *  Prints the database of accounts sorted by social security number
	 * Output:
	 *  Prints the database of accounts sorted by social security number
	*/
	public static void printAcctsBySSNSortKey(Bank bank, ArrayList<Integer> ssnKey, PrintWriter outFile) {
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by SSN");
		printStars(outFile);
		int index = 0;
		for(int i = 0; i < ssnKey.size(); i++) {
			index = (int) ssnKey.get(i);
			outFile.println(bank.printAccount(index));
		}
	}
	
	/* Method printAcctsByNameSortKey:
	 * Input:
	 *  bank - reference to the Bank object
	 *  nameKey - reference to the name sort key
	 *  outFile - reference to the output file
	 * Process:
	 *  Prints the database of accounts sorted by name
	 * Output:
	 *  Prints the database of accounts sorted by name
	*/
	public static void printAcctsByNameSortKey(Bank bank, ArrayList<Integer> nameKey, PrintWriter outFile) {
		int index;
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Name");
		printStars(outFile);
		for(int i = 0; i < nameKey.size(); i++) {
			index = (int) nameKey.get(i);
			outFile.println(bank.printAccount(index));
		}
	}
	
	/* Method printAcctsByAcctNumSortKey:
	 * Input:
	 *  bank - reference to the Bank object
	 *  accountNumberKey - reference to the account number sort key
	 *  outFile - reference to the output file
	 * Process:
	 *  Prints the database of accounts sorted by account number
	 * Output:
	 *  Prints the database of accounts sorted by account number
	*/
	public static void printAcctsByAcctNumSortKey(Bank bank, ArrayList<Integer> accountNumberKey, 
			PrintWriter outFile) {
		int index;
		printStars(outFile);
		outFile.printf("%50s%n", "Sorted by Account Number");
		printStars(outFile);
		for(int i = 0; i < accountNumberKey.size(); i++) {
			index = (int) accountNumberKey.get(i);
			outFile.println(bank.printAccount(index));
		}
	}
	
	/**
	 * Method printStars()
	 * Input:
	 *  none
	 * Process:
	 *  prints string of 100 stars
	 * Output:
	 *  string of stars
	 */
	public static void printStars(PrintWriter outFile) {
		for(int i = 0; i < 100; i++) {
			outFile.print('*');
		}
		outFile.println();
	}
	
	/* Method menu()
	 * Input:
	 *  none
	 * Process:
	 *  Prints the menu of transaction choices
	 * Output:
	 *  Prints the menu of transaction choices
	 */
	public static void menu() {
		System.out.println("SELECT ONE OF THE FOLLOWING:");
		System.out.println("W - Withdrawal");
		System.out.println("D - Deposit");
		System.out.println("C - Clear Check");
		System.out.println("N - New Account");
		System.out.println("I - Account Info (without transaction history");
		System.out.println("H - Account Info (with transaction history");
		System.out.println("S - Close Account");
		System.out.println("R - Reopen closed account");
		System.out.println("X - Delete account");
		System.out.println("Q - Quit");
		System.out.println();
	}
	
	/* Method closeAcct():
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	 * Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.closeAccount() to execute the transaction
	 *  If the account exists, the account is closed (or stays closed)
	 *  Otherwise, an error message is printed
	 * Output:
	 *  If the account exists, the account is closed (or stays closed)
	 *  Otherwise, an error message is printed
	 */
	public static void closeAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
		int accountNumber = kybd.nextInt();
		int index = bank.findAcctByAcctNum(accountNumber);

		outFile.printf("Account Number: %d%n", accountNumber);
		
		if(!isValidAccount(index, outFile, accountNumber)) {
			return;
		}
		
		Account copy = bank.getAccount(index);
		outFile.printf("Account Type: %s%n", copy.getAccountType());
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Close Account", 0.0, 0);
		TransactionReceipt receipt;
		
		receipt = bank.closeAccount(ticket, index);
		
		//System.out.println(receipt);
		outFile.println(receipt);
	}
	
	/* Method ReopenAcct():
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	 * Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.reopenAccount() to execute the transaction
	 *  If the account exists, the account is reopened (or remains open)
	 *  Otherwise, an error message is printed
	 * Output:
	 *  If the account exists, the account is reopened (or remains open)
	 *  Otherwise, an error message is printed
	 */
	public static void reopenAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
		int accountNumber = kybd.nextInt();
		int index = bank.findAcctByAcctNum(accountNumber);

		outFile.printf("Account Number: %d%n", accountNumber);
		
		if(!isValidAccount(index, outFile, accountNumber)) {
			kybd.nextLine();
			return;
		}
		
		Account copy = new Account(bank.getAccount(index));
		outFile.printf("Account Type: %s%n", copy.getAccountType());
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Reopen Account", 0.0, 0);
		TransactionReceipt receipt;
		
		receipt = bank.reopenAccount(ticket, index);

		//System.out.println(receipt);
		outFile.println(receipt);
		outFile.println();
	}
	
	/* Method balance:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	 * Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.getBalance() to execute the transaction
	 *  If the account exists, the balance is printed
	 *  Otherwise, an error message is printed
	 * Output:
	 *  If the account exists, the balance is printed
	 *  Otherwise, an error message is printed
	 */
	public static void balance(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
		int accountNumber = kybd.nextInt();
		int index = bank.findAcctByAcctNum(accountNumber);
		
		outFile.printf("Account Number: %d%n", accountNumber);
		
		if(!isValidAccount(index, outFile, accountNumber)) {
			return;
		}
		
		Account copy = new Account(bank.getAccount(index)); //creates copy of account
		outFile.printf("Account Type: %s%n", copy.getAccountType());
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Balance Inquiry", 0.0, 0);
		TransactionReceipt receipt;
		//exits method if account is closed
		if(!isOpen(copy, outFile)) { 
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Account closed");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;                                        
		}
		
		receipt = bank.getBalance(ticket, index);
		
		//System.out.println(receipt);
		outFile.println(receipt);
		//System.out.println();
		outFile.println();
	}	
	
	/* Method deposit:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	 * Process:
	 *  Prompts for the requested account
	 *  Prompts for the amount to deposit
	 *  Creates TransactionTicket object
	 *  Calls bank.makeDeposit() to execute the transaction
	 *  If the transaction is valid, it makes the deposit and prints the new balance
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid deposit, the deposit transaction is printed
	 *  Otherwise, an error message is printed
	 */
	public static void deposit(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
		int accountNumber = kybd.nextInt(); //reads account number from keyboard
		int index = bank.findAcctByAcctNum(accountNumber); //gets index of account in bank's ArrayList
		double amount = kybd.nextDouble(); //reads amount of transaction
		int term = kybd.nextInt(); //reads term of CD

		outFile.printf("Account Number: %d%n", accountNumber);
		
		//exits method if account is invalid
		if(!isValidAccount(index, outFile, accountNumber)) {
			return;
		}
		
		Account copy = new Account(bank.getAccount(index)); //creates copy of account
		outFile.printf("Account Type: %s%n", copy.getAccountType());
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Deposit", amount, term);
		TransactionReceipt receipt;
		
		//exits method if maturity date has not passed
		if(bank.accounts.get(index) instanceof CDAccount) {
			if (!maturityDatePassed(((CDAccount)bank.accounts.get(index)).getMaturityDate(), 
					outFile, "Deposit")){
				receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
				receipt.setReasonForFailure("Maturity date not reached");
				bank.addFailureReceipt(index, new TransactionReceipt(receipt));
				return;
			}
		}
		
		//exits method if account is closed
		if(!isOpen(copy, outFile)) { 
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Account closed");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;                                        
		}
		
		//tests if amount is valid, exits method if not
		if(!isValidAmount(amount, outFile)) {
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Invalid amount");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;
		}

		receipt = bank.makeDeposit(ticket, index);
		
		//System.out.println(receipt.toString());
		outFile.println(receipt.toString());
		
		//System.out.println();
		outFile.println();
	}

	/* Method withdrawal:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Prompts for the amount to withdraw
	 *  Creates TransactionTicket object
	 *  Calls bank.makeWithdrawal() to execute the transaction
	 *  If the transaction is valid, it makes the withdrawal and prints the new balance
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid withdrawal, the withdrawal transaction is printed
	 *  Otherwise, an error message is printed
	 */
	public static void withdrawal(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
		int accountNumber = kybd.nextInt(); //reads account number from keyboard
		int index = bank.findAcctByAcctNum(accountNumber); //gets index of account in bank's ArrayList
		double amount = kybd.nextDouble(); //reads amount of transaction
		int term = kybd.nextInt(); //reads term of CD

		outFile.printf("Account Number: %d%n", accountNumber);
		
		//checks for valid account
		if(!isValidAccount(index, outFile, accountNumber)) {
			return;
		}
		
		Account copy = new Account(bank.getAccount(index)); //creates copy of account
		outFile.printf("Account Type: %s%n", copy.getAccountType());
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Withdrawal", amount, term);
		TransactionReceipt receipt;
		
		//exits method if account is closed
		if(!isOpen(copy, outFile)) { 
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Account closed");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;                                        
		}
		
		//exits method if maturity date has not passed
		if(bank.accounts.get(index) instanceof CDAccount) {
			if (!maturityDatePassed(((CDAccount)bank.accounts.get(index)).getMaturityDate(), 
					outFile, "Withdrawal")){
				receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
				receipt.setReasonForFailure("Maturity date not reached");
				bank.addFailureReceipt(index, new TransactionReceipt(receipt));
				return;
			}
		}
				
		//tests if amount is valid, ends transaction if not
		if(!isValidAmount(amount, outFile)) {
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Invalid amount");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;
		}
		
		//throws InsufficientFundsException and exits method if amount entered by user exceeds
		//account balance
		try {
			if(amount > copy.accountBalance) {
				throw new InsufficientFundsException(copy, amount);
			} 
		} catch (InsufficientFundsException ex){
			outFile.println(ex.getMessage());
			outFile.println();
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Insufficient funds");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;
		}
		
		receipt = bank.makeWithdrawal(ticket, index);
		
		//System.out.println(receipt);
		outFile.println(receipt.toString());
		//System.out.println();
		outFile.println();
	}

	/* Method clearCheck:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Prompts for the amount to withdraw
	 *  Creates a Check object
	 *  Creates TransactionTicket object
	 *  Calls bank.clearCheck() to execute the transaction
	 *  If the transaction is valid, it makes the withdrawal and prints the new balance
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid withdrawal, the withdrawal transaction is printed
	 *  Otherwise, an error message is printed
	 */
	public static void clearCheck(Bank bank, PrintWriter outFile, Scanner kybd) 
								  throws IOException, ParseException {
		
		int accountNumber = kybd.nextInt(); 
		int index = bank.findAcctByAcctNum(accountNumber);
		double checkAmount = kybd.nextDouble(); //reads check amount
		Calendar dateOfCheck = Calendar.getInstance(); //creates Calendar object
		
		//creates formatter to convert String to Date
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String date = kybd.next(); //reads date of check
		dateOfCheck.setTime(formatter.parse(date)); //convert date string into Calendar object
		
		outFile.println("Account Number: " + accountNumber);
		
		//checks for valid account
		if(!isValidAccount(index, outFile, accountNumber)) {
			return;
		}
		
		Account copy = new Account(bank.getAccount(index)); //creates copy of account
		outFile.printf("Account Type: %s%n", copy.getAccountType());
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Clear Check", checkAmount, 0);
		TransactionReceipt receipt;
		
		//exits method if account is closed
		if(!isOpen(copy, outFile)) { 
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Account closed");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;                                        
		}
		
		//exits method if maturity date has not passed
		if(bank.accounts.get(index) instanceof CDAccount) {
			if (!maturityDatePassed(((CDAccount)bank.accounts.get(index)).getMaturityDate(), 
					outFile, "Clear Check")){
				receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
				receipt.setReasonForFailure("Maturity date not reached");
				bank.addFailureReceipt(index, new TransactionReceipt(receipt));
				return;
			}
		}
		
		//tests if amount is valid, ends transaction if not
		if(!isValidAmount(checkAmount, outFile)) {
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Invalid amount");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;
		}		
		
		Check check = new Check(checkAmount, dateOfCheck, accountNumber);
		
		//exits method if check is post-dated
		if(postDated(check, outFile)) {
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Post-dated check");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;
		}
		
		//exits method if check is too old
		if(checkTooOld(check, outFile)) {
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Check too old");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;
		}
				
		receipt = new TransactionReceipt(bank.clearCheck(ticket, index, new Check(check)));
		
		//System.out.println(receipt);
		outFile.println(receipt);
		//System.out.println();
		outFile.println();
	}

	/* Method newAcct:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the new account info
	 *  Calls bank.openNewAccount() to execute the transaction
	 *  If the transaction is valid, a confirmation message is printed  
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid transaction, a confirmation message is printed  
	 *  Otherwise, an error message is printed
	 */
	public static void newAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException, ParseException {
		String first, last, ssn, accountType; //initialize account information
		int accountNumber;
		double initialBalance = 0.0;
		Calendar maturityDate = Calendar.getInstance();
		//creates SimpleDateFormat object to read dates of form "mm/dd/yyyy"
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		accountNumber = kybd.nextInt(); //reads account information
		first = kybd.next();
		last = kybd.next();
		ssn = kybd.next();
		accountType = kybd.next();
		
		outFile.printf("Account Number: %d%n"
					 + "Account Type: %s%n", accountNumber, accountType);
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Open Account", 0, 0);
		
		//checks if account already exists, exits method if it does
		try {
			if(bank.findAcctByAcctNum(accountNumber) != -1) {
				throw new AccountAlreadyExistsException(accountNumber);
			} 
		}catch(AccountAlreadyExistsException ex) {
				outFile.println(ex.getMessage());
				outFile.println();
				return;
			}
		
		//gets initial balance and maturity date if account is CD
		if(accountType.equals("CD")) { 
			initialBalance = kybd.nextDouble();
			ticket.setAmountOfTransaction(accountNumber);
			String date = kybd.next();
			maturityDate.setTime(formatter.parse(date));
		} else {
			kybd.nextLine();
		}
		
		//checks is SSN is valid
		try {
			if(ssn.length() != 9) {
				throw new InvalidSSNException(ssn);
			}
		} catch (InvalidSSNException ex) {
			outFile.println(ex.getMessage());
			outFile.println();
			return;
		}
		Depositor depositor = new Depositor(first, last, ssn);
		TransactionReceipt receipt;
		receipt = bank.addAccount(accountNumber, depositor, accountType, initialBalance, maturityDate, ticket);
		outFile.println(receipt);
		//System.out.println();
		
	}

	/* Method deleteAcct:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested account
	 *  Creates TransactionTicket object
	 *  Calls bank.deleteAccount() to execute the transaction
	 *  If the transaction is valid, a confirmation message is printed  
	 *  Otherwise, an error message is printed
	 * Output:
	 *  For a valid transaction, the transaction result is printed
	 *  Otherwise, an error message is printed
	 */
	public static void deleteAcct(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException{
		int accountNumber = kybd.nextInt();
		int index = bank.findAcctByAcctNum(accountNumber);

		outFile.println("Account Number: " + accountNumber);
		
		//checks for valid account
		if(!isValidAccount(index, outFile, accountNumber)) {
			return;
		}
		
		Account copy = new Account(bank.getAccount(index));
		outFile.printf("AccountType: %s%n", copy.getAccountType());
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Delete Account", 0, 0);
		TransactionReceipt receipt;

		try{
			if(copy.getAccountBalance() > 0) {
				throw new InvalidDeletionException(copy.getAccountBalance());
			}
		} catch (InvalidDeletionException ex) {
			outFile.println(ex.getMessage());
			outFile.println();
			receipt = new TransactionReceipt(ticket, false, copy.getAccountBalance(), copy.getAccountBalance());
			receipt.setReasonForFailure("Check too old");
			bank.addFailureReceipt(index, new TransactionReceipt(receipt));
			return;
		}
		
		receipt = bank.deleteAccount(ticket, index);
		
		//System.out.println(receipt);
		outFile.println(receipt);
		//System.out.println();
		outFile.println();
	}

	/* Method acctInfo:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested SSN
	 *  searches the account database for matching accounts
	 *  and prints the account info for each matching account
	 *  If there are no matching accounts, an error message is printed
	 * Output:
	 *  For a valid transaction, the transaction result is printed
	 *  Otherwise, an error message is printed
	 */
	public static void acctInfo(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException {
		String ssn = kybd.next();	
		
		//checks if SSN is valid
		try {
			if(ssn.length() != 9) {
				throw new InvalidSSNException(ssn);
			}
		} catch (InvalidSSNException ex) {
			//System.out.println(ex.getMessage());
			//System.out.println();
			outFile.println(ex.getMessage());
			outFile.println();
			return;
		}
		
		ArrayList<Account> accounts = bank.getAccountsSSN(ssn);
		
		//prints error message and returns if no accounts are found
		if(accounts.isEmpty()) {
			//System.out.printf("No accounts found for %s%n%n", ssn);
			outFile.printf("No accounts found for %s%n%n", ssn);
			return;
		}
		
		TransactionTicket ticket = new TransactionTicket(Calendar.getInstance(), "Account Info", 0.0, 0);
		//System.out.println(ticket.toString());
		outFile.println(ticket.toString());
		
		for(int i = 0; i < accounts.size(); i++) {
			//System.out.println(accounts.get(i).toString());
			outFile.println(accounts.get(i).toString());
		}
		//System.out.println();
		outFile.println();
	}
	
	/* Method acctInfoWithTransactionHistory:
	 * Input:
	 *  bank - reference to the Bank object
	 *  outFile - reference to the output file
	 *  kybd - reference to the "test cases" input file
	* Process:
	 *  Prompts for the requested SSN
	 *  searches the account database for matching accounts
	 *  and prints the account info for each matching account
	 *  If there are no matching accounts, an error message is printed
	 * Output:
	 *  For a valid transaction, the transaction result is printed
	 *  Otherwise, an error message is printed
	 */
	public static void acctInfoWithTransactionHistory(Bank bank, PrintWriter outFile, Scanner kybd) throws IOException{
	
		String ssn = kybd.next();	
		
		//checks if SSN is valid
		try {
			if(ssn.length() != 9) {
				throw new InvalidSSNException(ssn);
			}
		} catch (InvalidSSNException ex) {
			System.out.println(ex.getMessage());
			System.out.println();
			return;
		}
		
		ArrayList<Account> accounts = bank.getAccountsSSN(ssn); //list of accounts associated with SSN\
		double amount;
		String dateOfTransaction, typeOfTransaction;
		
		//prints error message and returns if no accounts are found
		if(accounts.isEmpty()) {
			//System.out.printf("No accounts found for %s%n%n", ssn);
			outFile.printf("No accounts found for %s%n%n", ssn);
			return;
		}
		
		outFile.printf("%50s %s%n%n", "Accounts associated with: ", ssn);
		//nested for loop to print transaction history for each account found
		for(int i = 0; i < accounts.size(); i++) {
			
			outFile.printf("%50s%n%n", "Account Information");
			outFile.println(accounts.get(i).toString());
			outFile.println();
			outFile.printf("%50s%n%n", "Transaction History");
			outFile.printf("%10s %15s%10s %10s %10s %10s %15s%n", "DATE", "TYPE", "RESULT", "OLD BALANCE",
					"AMOUNT", "NEW BALANCE", "NEW MATURITY DATE");
			ArrayList<TransactionReceipt> receipts = accounts.get(i).getReceipts();
			
			for(int j = 0; j < accounts.get(i).getReceipts().size(); j++) {
				
				dateOfTransaction = receipts.get(j).getTransactionTicket().getDateofTransactionString();
				typeOfTransaction = receipts.get(j).getTransactionTicket().getTypeOfTransaction();
				amount = receipts.get(j).getTransactionTicket().getAmountOfTransaction();
				
				outFile.printf("%10s %15s", dateOfTransaction, typeOfTransaction);
				if(!receipts.get(j).getSuccessIndicatorFlag()) {
					//failure message
					outFile.printf("%12s %s", "FAILED -", receipts.get(j).getTransactionFailureReason());
				} else {
					//success message
					outFile.printf("%10s %10.2f %10.2f %10.2f", "SUCCESS", receipts.get(j).getPreTransactionBalance(),
							amount, receipts.get(j).getPostTransactionBalance());
					if(receipts.get(j).getTransactionTicket().getTermOfCD() > 0) {
						//displays post transaction maturity date if account is CD
						outFile.printf("%15s", receipts.get(j).getmaturityDateString());
					}
				}
				
				outFile.printf("%n");
			}
		}
		outFile.println();
}
	
	/**
	 * Method isValidAccount
	 * Input:
	 * 	index - return value of bank.findaccount
	 * 	outfile - reference to output file
	 *  ticket - reference to Transaction Ticket
	 *  accountNumber - account number read in from keyboard
	 * Process:
	 * 	checks if index is less than 0
	 * 	if no, return true
	 * 	if yes, throw InvalidAccountException
	 * 	print error message and return false
	 */
	public static boolean isValidAccount(int index, PrintWriter outFile, int accountNumber) throws IOException{
		try {
			if(index < 0) {
				throw new InvalidAccountException(accountNumber);
			} else {
				return true;
			}
		} catch(InvalidAccountException ex) {
			//System.out.printf("%s%n%n", ex.getMessage());
			outFile.printf("%s%n%n", ex.getMessage());
			return false;
		}
	}
	
	/**
	 * Method isOpen
	 * Input:
	 * 	copy - reference to account object
	 * 	outFile - reference to output file
	 * Process
	 * 	calls copy.getStatus()
	 * 	throws AccountClosedException if true
	 * Output:
	 * 	Prints error message if account is closed and returns false
	 * 	Returns true is account is open
	 */
	public static boolean isOpen(Account copy, PrintWriter outFile) throws IOException{
		try {
			if(!copy.getStatus()) {
				throw new AccountClosedException(copy.getAccountNumber());
			} else {
				return true;
			}
		} catch (AccountClosedException ex){
			outFile.println(ex.getMessage());
			outFile.println();
			return false;
		}
	}
	
	/**
	 * Method isValidAmount
	 * Input:
	 * 	amount - reference to amount of transaction
	 * 	outFile - reference to output file
	 * Process:
	 * 	throws InvalidAmountException if amount is less than zero
	 * Output:
	 * 	prints error message and returns false if less than zero
	 * 	returns true if greater than zero
	 */
	public static boolean isValidAmount(double amount, PrintWriter outFile) throws IOException{
		try {
			if(amount < 0) {
				throw new InvalidAmountException(amount);
			} else 
				return true;
		} catch (InvalidAmountException ex) {
			outFile.println(ex.getMessage());
			outFile.println();
			return false;
		}
	}
	
	/**
	 * Method maturityDatePassed
	 * Input:
	 * 	maturityDate - reference to CD account's maturity date as Calendar object
	 * 	outFile - reference to output file
	 * Process:
	 * 	creates Calendar object with time set to today
	 * 	compares maturity date to today
	 * Output:
	 * 	throws MaturityDateException if date has not passed and returns true
	 * 	returns false if date has passed
	 */
	public static boolean maturityDatePassed(Calendar maturityDate, PrintWriter outFile,
			String transactionType) throws IOException{
		Calendar today = Calendar.getInstance();
		try {
			if(maturityDate.after(today)) {
				throw new MaturityDateException(maturityDate, transactionType);
			} else
				return true;
		} catch(MaturityDateException ex) {
			outFile.println(ex.getMessage());
			outFile.println();
			return false;
		}
	}
	/**
	 * Method postDated
	 * Input:
	 * 	check - reference to Check object
	 * 	outFile - reference to output file
	 * Process:
	 * 	creates Calendar object with time set to today
	 * 	compares check.dateOfCheck to today
	 * Output:
	 * 	throws PostDatedCheckException if check.dateOfCheck is after today and returns true
	 * 	returns false if check.dateOfCheck is valid
	 */
	
	public static boolean postDated(Check check, PrintWriter outFile) throws IOException{
		Calendar today = Calendar.getInstance();
		try {
			if(check.getDateOfCheck().after(today)) {
				throw new PostDatedCheckException(check.getDateOfCheck());
			} else
				return false;
		} catch(PostDatedCheckException ex) {
			outFile.println(ex.getMessage());
			outFile.println();
			return true;
		}
	}
	
	/**
	 * Method checkTooOld
	 * Input:
	 * 	check - reference to Check object
	 * 	outFile - reference to output file
	 * Process:
	 * 	creates Calendar object and sets time to 6 months ago (expDate)
	 * 	compares check.getDateOfCheck() to expDate
	 * Output:
	 * 	throws CheckTooOldException if dateOfCheck is before expDate and returns true
	 * 	returns false if date of check is valid
	 */
	public static boolean checkTooOld(Check check, PrintWriter outFile) throws IOException{
		Calendar expDate = Calendar.getInstance();
		expDate.add(Calendar.MONTH, -6);
		try {
			if(check.getDateOfCheck().before(expDate)) {
				throw new CheckTooOldException(expDate, check.getDateOfCheck());
			} else
				return false;
		} catch(CheckTooOldException ex) {
			outFile.println(ex.getMessage());
			outFile.println();
			return true;
		}
	}
	/* Method pause() */
	public static void pause(Scanner keyboard){
	}
}
