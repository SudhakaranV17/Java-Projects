package BankAtm;

import java.util.*;

public class Bank {
	private String name;
	private ArrayList<Account> accounts;
	private ArrayList<User> users;
	
	/**CONSTRUCTOR
	 * create a new bank object with empty lists of users and accounts
	 * @param name the name of the bank
	 */
	public Bank(String name) {
		this.name=name;
		this.accounts=new ArrayList<Account>();
		this.users=new ArrayList<User>();
	}
	
	/**
	 * generate a new universal unique id for a user
	 * @return
	 */
	public  String getNewUserUUID() {
		//inits
		String uuid;
		Random rng=new Random();
		int len = 6;
		boolean nonUnique;
		//generate uuid until it is unique
		//continue looping until we get unique ID
		do {
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer)rng.nextInt(10)).toString();			
				
			}
			//check its unique
			nonUnique = false;
			for (User u: this.users) {
				if (uuid.compareTo(u.getUUID())==0) {
					nonUnique =true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuid;
	}
	
	/**
	 * generate a new universally unique ID for an account
	 * @return the uuid
	 */
	public String getNewAccountUUID() {
		//inits
				String uuid;
				Random rng=new Random();
				int len = 10;
				boolean nonUnique;
				//generate uuid until it is unique
				//continue looping until we get unique ID
				do {
					uuid = "";
					for (int i = 0; i < len; i++) {
						uuid += ((Integer)rng.nextInt(10)).toString();			
						
					}
					//check its unique
					nonUnique = false;
					for (Account a: this.accounts) {
						if (uuid.compareTo(a.getUUID())==0) {
							nonUnique =true;
							break;
						}
					}
				} while (nonUnique);
				
				return uuid;
		
	}
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	
	}
	
	public User addUser(String firstname,String lastname,String pin) {
		
		//create a new user object and it to our list
		User newuser = new User(firstname, lastname, pin, this);
		this.users.add(newuser);
		
		//create a new savings account
		//add to holder and bank LISTS
		Account newaccount =new Account("Savings", newuser, this);
				newuser.addAccount(newaccount);//holder object
				this.accounts.add(newaccount);//theBank object
				return newuser;
	}
	/**
	 * create login creditionals
	 * @param userID
	 * @param pin
	 * @return
	 */
	public User userLogin(String userID,String pin) {
		//search through list of users
		for (User u : this.users) {
			
			//check user id is correct
			if (u.getUUID().compareTo(userID)==0 && u.validatePin(pin)) {
				return u;
			}
		}//if we have not find the user and have an incorrect number
		return null;
	}

	public String getName() {
		
		return this.name;
	}

}
