package zoho;

import java.util.Scanner;

public class stringcompress {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s1 = input.nextLine();
		input.close();
		char[] c=s1.toCharArray();
		for(int i=0;i<c.length;i++){
	        if(c[i]>='0' && c[i]<='9'){
	            for(int j=0;j<c[i]-48;j++){
	                System.out.printf("%c",c[i-1]);
	            }
	        }
	    }
		
		
	}

}
