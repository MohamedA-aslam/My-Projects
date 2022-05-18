package SupermarketSupreme;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception{
		Customer customerClassObject = new Customer();
		Admin adminClassObject = new Admin();
		System.out.println("Hello!, You've opened supermarket apllication.\n");
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("________________________________________________");
		System.out.println("You want to enter as \n1:Customer (or) 2:Admin");
		int adminOrCustomer = 0;
		//below here we are asking whether you want to login as admin or customer.
		try {
		adminOrCustomer=sc.nextInt();
		
		
		
		switch (adminOrCustomer) {
		case 1:{ 			customerClassObject.welcomeCustomer();	break;	 	}
		case 2:{				adminClassObject.adminPrivilege();  break;		}
		default:{			System.out.println("Unexpected value..... " +  "\n");				continue;	}
		}
		
//		if( adminOrCustomer == 2 ) 									// If pressed
//		else if( adminOrCustomer == 1 ) 						//if press other than 1 or 2 its hould tell us to try again .
//		else 										continue;										//For invalid request it will ask again
//		
//		
		 
		}catch (InputMismatchException e) {
			System.out.println("\n\nTry Again....");
			sc.nextLine();
		}
		System.out.println("_______________________________________________________");
		System.out.println("\n\n\nDo you want to exit (1) or continue(2)...\n\n\n");
		int permission = sc.nextInt();
		if(permission<1)
			break;
		
		}
		sc.close();
				
	
	
	}
}
