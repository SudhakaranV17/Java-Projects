/**
 * 
 */
package com.Basic;

import java.util.Scanner;

/**
 * @author Sudhakar
 *
 */
public class Fib {

	/**
	 * @param input: Interger value to find a fibonacci series with or without recursion.
	 * @return fibonacci series total sum value.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean flag =true;
		while(flag) {
			System.out.println("Enter 1 for Fib without recursion."
					+ "\nEnter 2 for Fib using recursion."
					+ "\nEnter 3 for EXIT."
					+ "\nEnter your choice.");
			byte choice = sc.nextByte();
			
			switch (choice) {
			case 1:
				System.out.println("Enter a number: ");
				short num = (short) sc.nextInt();
				fibWR(num);
				break;
			case 2:
				System.out.println("Enter a number: ");
				short num1 = (short) sc.nextInt();
				short total=(short) FibRec(num1);
				System.out.println("Fibonacci of a given number: "+total);
				break;
			case 3:
				flag = false;
				break;
			default:
				System.out.println("\nEnter a valid choice.");
				break;
			}
			
		}
		sc.close();
	}
		/**
		 * This method calculates fibonacci without recursion.
		 * @param input: A positive Integer.
		 * @return Fibonacci number of given input.
		 */	
		public static void fibWR(short num) {
		int sum =0;
		for (int i = 0; i <= num; i++) {
			sum = sum +i;			
		}
		System.out.println("Fibonacci of a given number: "+sum);

	}
	/**
	 * This method calculates fibonacci using recursion.
	 * @param input: A positive Integer.
	 * @return Fibonacci number of given input.
	 */
	public static int FibRec(short num) {
		if (num>0) {
			return  num+FibRec((short) (num-1));
		}
		return num;
		
	}

}
