package BankAtm;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		//init Scanner
		Scanner sc = new Scanner(System.in);
		
		//init Bank
		Bank theBank = new Bank("Bank of TamilNadu");
		
		//add a user, which also create a savings account
		User aUser = theBank.addUser("Arun", "A", "1234");
		 
		//add checking account for the user
		Account newAccount=new Account("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while(true) {
			//stay in the login prompt until successful login
			curUser=ATM.mainMenuPrompt(theBank,sc);
			
			//stay in main menu until user exists
			curUser=ATM.printMainMenu(curUser,sc);
		}
	}

	

	public static User mainMenuPrompt(Bank theBank, Scanner sc) {
		//inits
		String userID;
		String pin;
		User authUser;
		//promt a user for id/pin until a correct one is reached
		do {
			System.out.printf("\nWelcome to %s\n\n",theBank.getName());
			System.out.print("Enter UserID: ");
			userID = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();
			
			//try to get user object corresponding to user and pin combo
			authUser = theBank.userLogin(userID, pin);
			if (authUser==null) {
				System.out.println("Incorrect User/Pin combination."+" Please try again.");
				
			}
		} while (authUser==null); 
		return authUser;
		
	}
	
	
	private static User printMainMenu(User theUser, Scanner sc) {
		//print summary of the user accounts
		theUser.printAccountsSummary();
		
		//init
		int choice;
		//user menu
		do {
			System.out.printf("Welcome %s What you like to do..?\n",
					theUser.getFirstName()); 
			
			System.out.println("	1) Show account Transaction History");
			System.out.println("	2) Withdrawl");
			System.out.println("	3) Deposit");
			System.out.println("	4) Transfer");
			System.out.println("	5) Quit");
			System.out.println("	6) Exit Program");
			
			System.out.println();
			System.out.print("Enter your Choice: ");
			choice = sc.nextInt();
			if (choice<1||choice>6) {
				System.out.println("Invalid choice please Choose 1 - 6 :");
			}
		} while (choice<1 || choice>6);
		
		//process the choice
		switch(choice) {
		case 1:
			ATM.showTransHistory(theUser,sc);
			break;
		case 2:
			ATM.withdrawlFunds(theUser,sc);
			break;
		case 3:
			ATM.depositFunds(theUser,sc);
			break;
		case 4:
			ATM.transferFunds(theUser,sc);
			break;
		case 5:
			sc.nextLine();
			break;
		case 6:
			System.out.println("Thank You For Banking...!!!");
			System.exit(0);
		}
		//re-display this menu until the user wants to quit
		if (choice!=6) {
			ATM.printMainMenu(theUser, sc);
		}
		return null;
	
	}



	public static void depositFunds(User theUser, Scanner sc) {
		
		//inits 
		int toAcc;
		String memo;
		double amount;
		double accBalance;
		
		//get the account to transfer from
		do {
			System.out.printf("Enter the number (1 - %d) of the account\n"+"To Deposit: ",theUser.numAccounts());
			toAcc = sc.nextInt()-1;
			if (toAcc<0 || toAcc>=theUser.numAccounts()) {
				System.out.println("Invalid Account. please try again.");
			}
		} while (toAcc<0 || toAcc>=theUser.numAccounts());
		accBalance = theUser.getAccBalance(toAcc);
		
		do {
			System.out.printf("Enter the amount to transfer (Max $%.02f): $",accBalance);
			amount=sc.nextDouble();
			if (amount<0) {
				System.out.println("Amount must be greater than zero.");
			}
		} while (amount<0);
		//gobble up rest of previous amount
		sc.nextLine();
		//get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		theUser.addAcctTransaction(toAcc, amount, memo);
	}



	public static void withdrawlFunds(User theUser, Scanner sc) {
		//inits 
				int fromAcc;
				String memo;
				double amount;
				double accBalance;
				
				//get the account to transfer from
				do {
					System.out.printf("Enter the number (1 - %d) of the account\n"+"To Withdraw from: ",theUser.numAccounts() );
					fromAcc = sc.nextInt()-1;
					if (fromAcc<0 || fromAcc>=theUser.numAccounts()) {
						System.out.println("Invalid Account. please try again.");
					}
				} while (fromAcc<0 || fromAcc>=theUser.numAccounts());
				accBalance = theUser.getAccBalance(fromAcc);
				
				do {
					System.out.printf("Enter the amount to Withdraw (Max $%.02f): $",accBalance);
					amount=sc.nextDouble();
					if (amount<0) {
						System.out.println("Amount must be greater than zero.");
					} else if(amount>accBalance){
						System.out.printf("Amount must not be greater than\n"+"Balance of $%.02f.\n",accBalance);
						
					}
				} while (amount<0||amount>accBalance);
				//gobble up rest of previous amount
				sc.nextLine();
				//get a memo
				System.out.print("Enter a memo: ");
				memo = sc.nextLine();
				theUser.addAcctTransaction(fromAcc, -1*amount, memo);
		
	}



	public static void transferFunds(User theUser, Scanner sc) {
		//inits 
		int fromAcc;
		int toAcc;
		double amount;
		double accBalance;
		
		//get the account to transfer from
		do {
			System.out.printf("Enter the number (1 - %d) of the account\n"+"To transfer from: ",theUser.numAccounts() );
			fromAcc = sc.nextInt()-1;
			if (fromAcc<0 || fromAcc>=theUser.numAccounts()) {
				System.out.println("Invalid Account. please try again.");
			}
		} while (fromAcc<0 || fromAcc>=theUser.numAccounts());
		accBalance = theUser.getAccBalance(fromAcc);
		
		//get the account to TRANSFER TO
		do {
			System.out.printf("Enter the number (1 - %d) of the account\n"+"To transfer to: " ,theUser.numAccounts());
			toAcc = sc.nextInt()-1;
			if (toAcc<0 || toAcc>=theUser.numAccounts()) {
				System.out.println("Invalid Account. please try again.");
			}
		} while (toAcc<0 || toAcc>=theUser.numAccounts());
		
		//get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (Max $%.02f): $",accBalance);
			amount=sc.nextDouble();
			if (amount<0) {
				System.out.println("Amount must be greater than zero.");
			} else if(amount>accBalance){
				System.out.printf("Amount must not be greater than\n"+"Balance of $%02.f.\n",accBalance);
				
			}
		} while (amount<0||amount>accBalance);
		//finally do the transfer
		
		theUser.addAcctTransaction(fromAcc,-1*amount,String.format("Transfer to the account %s", theUser.getAcctUUID(toAcc)));
		theUser.addAcctTransaction(toAcc,amount,String.format("Transfer to the account %s", theUser.getAcctUUID(fromAcc)));
		
	}



	public static void showTransHistory(User theUser, Scanner sc) {
		int theAcc;
		//get account whose transaction history to look at
		do {
			System.out.printf("Enter the number (1-%d) of the account " +" whose transaction you want to see:" , theUser.numAccounts());
			theAcc = sc.nextInt()-1;
			if (theAcc<0 || theAcc>=theUser.numAccounts()) {
				System.out.println("Invalid Account. please try again.");
			}
		} while (theAcc<0 || theAcc>=theUser.numAccounts());
		//print transaction history
		theUser.printAccTransHistory(theAcc);
	}
}



















