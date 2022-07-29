package zoho;

import java.util.Scanner;

public class Amstrong {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		input.close();
		int temp;
		int check = 0;
		int originalNumber=num;
		//power
		String str = Integer.toString(num);  
		// computing the size of the string  
		int size = str.length();
		
		while (num>0) {
			temp=num%10;
			check=(int) (check+(Math.pow(temp, size)));
			num=num/10;
		}
		if (check==originalNumber) {
			System.out.println("It is amstrong number.");
		}
		else {
			System.out.println("It is not amstrong.");
		}

	}

}
