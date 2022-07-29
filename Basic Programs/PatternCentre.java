package zoho;

import java.util.Scanner;

public class PatternCentre {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.close();
		for(int i=1;i<=n;i++) {
			for(int j=i;j<=n;j++) {//decreasing order i to n
				System.out.print(" ");
			} 
			for(int k=1;k<i;k++) {//increasing order 1 to i
				System.out.print("*");
			}
			for(int k=1;k<=i;k++) {
				System.out.print("*");
			}
			System.out.println("");
		} 
		
	}
}

