package zoho;

import java.util.Scanner;

public class fib {
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		input.close();
		int temp=0;
		for(int i=1;i<=num;i++) {
			temp = temp+ i;
		}
		
		System.out.println(temp);
	}

}
