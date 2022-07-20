package BankAtm;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;


public class User{
	private String firstName;
	private String lastName;
	private String uuid;
	private byte pinHash[];            //MD5 hash for pin
	private ArrayList<Account> accounts;
	
	
	/**
	 * create new user CONSTRUCTOR
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @param bank
	 */
	public User(String firstName,String lastName,String pin, Bank theBank) {
		
		//User name
		this.firstName=firstName;
		this.lastName=lastName;
		
		//using MD5 hash for storing pin for security
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());//converting to MD5 hash for security
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//get a new -("user")-, unique universal ID
		this.uuid=theBank.getNewUserUUID();
		
		//create empty list of accounts
		this.accounts=new ArrayList<Account>();
		
		//print a log message
		System.out.printf("New User %s %s with ID %s is created.\n",lastName,firstName,this.uuid);
	}

	/**
	 * add an account for the user
	 * @param anAcct   the account to add
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
		
		
	}
	
	/**
	 * return the users uuid
	 * @return
	 */
	public String getUUID() {
		return this.uuid;
		
	}
	
	/**
	 * check whether the given pin matches the user pin
	 * @param apin
	 * @return boolean 
	 */
	public boolean validatePin(String apin) {
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			 return MessageDigest.isEqual(md.digest(apin.getBytes()), this.pinHash);//converting to MD5 hash for security
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}return false;
		
	}

	public String getFirstName() {
		
		return this.firstName;
	}

	public void printAccountsSummary() {
		System.out.printf("\n\n%s's Acoount summary\n",this.firstName);
		for (int i = 0; i < this.accounts.size(); i++) {
			System.out.printf("  %d) %s\n",i+1,this.accounts.get(i).getSummaryLine());
		}
		System.out.println();
	}

	public int numAccounts() {
		return this.accounts.size();
		 
	}

	public void printAccTransHistory(int accIndex) {
		this.accounts.get(accIndex).printAccTransHistory();
		
	}
	
	/**
	 * get balance of specific account
	 * @param fromAcc
	 * @return
	 */
	public double getAccBalance(int fromAcc) {
		
		return this.accounts.get(fromAcc).getBalance(); 
	}

	public String getAcctUUID(int fromAcc) {
		
		return this.accounts.get(fromAcc).getUUID();
	}

	public void addAcctTransaction(int accIndex, double amount, String memo) {
		this.accounts.get(accIndex).addTransaction(amount,memo);
		
	}
	
	
}





















