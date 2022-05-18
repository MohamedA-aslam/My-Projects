package SupermarketSupreme;


import java.sql.SQLException;
import java.util.Scanner;

public class Customer {
	
	int balanceAmount=0;
	int moneySpent=0;
	
	// Object Declaration
	
	MarketDAO dao = new MarketDAO();
	Admin adminClassObject = new Admin();
	Scanner sc = new Scanner(System.in);
	CustomerInfo customerInfoClassObject = new CustomerInfo();
	int customerId;
	void welcomeCustomer() throws SQLException {
		
		dao.connect();
		
		System.out.println("---------------------------------------------");
		System.out.println("Welcome to Supermarket Supreme.");
		System.out.println("---------------------------------------------");
		System.out.println("Please Enter CustomerId");
		
		System.out.println("Customer ID:");
		customerId = sc.nextInt();						
		
		customerInfoClassObject=dao.getCustomer(customerId);
		
		
		while(true) {
		if(customerInfoClassObject==null) {
			System.out.println("Please Enter valid Id");
			System.out.println("Customer ID:");
			customerId = sc.nextInt();						//
			customerInfoClassObject=dao.getCustomer(customerId);	
		}
		else				break;
		}
		
		
		balanceAmount=customerInfoClassObject.balance;
		while(balanceAmount<10) {
			
			System.out.println("Sorry for the inconivience,"+customerInfoClassObject.customerName+" .It seem you have no balance.\nPlease tell admin to assign money for you.\nThank you for your patience.\n");
			adminClassObject.adminPrivilege();
			customerInfoClassObject=dao.getCustomer(customerId);
			balanceAmount=customerInfoClassObject.balance;
		}
		System.out.println("\n\nThank you for choosing our service ,."+customerInfoClassObject.customerName+".\nHere is our products you can choose from.\nYour Total money is."+this.balanceAmount);
		
		dao.getAllItem();
		int remainingMoney=this.balanceAmount;
		while(true) {
			System.out.println("\n\n\n\n\n\n");
			System.out.println("Please select your desired item's Id number to put it in your cartor\n(-1)to remove items or\n(-2) to clear your cart. or\n(0) to bill.");
			int cartItem = sc.nextInt();
			
			/*
			 * 		'0' Exiting the process
			 */
			
			if(cartItem==0) break;
			else if(cartItem == -2)	dao.emptyCart();
			else if(cartItem == -1) {
				dao.bill();
				System.out.println("which item do you want to delete");
				int removeItem=sc.nextInt();
				dao.alterCart(removeItem);
				Item it=dao.getItem(removeItem);
				remainingMoney+=it.rateOfTheProduct;
				System.out.println("Removed.. Remaining Money :"+remainingMoney);
				System.out.println();
			}
			
			
			/*
			 * Retrieve data from selected row
			 */
			
			
			
			else{
				Item itemClassObject = dao.getItem(cartItem);
				if(itemClassObject == null)	continue;
				remainingMoney-=itemClassObject.rateOfTheProduct;
				if(remainingMoney==0) break;
				else if(remainingMoney<0) {
					System.out.println("you spending more than you are assign for we are checking you out");
					break;
				}
			
			
			/*
			 * displaying retrieved data 
			 */
			
			System.out.println(itemClassObject.productId+" - "+itemClassObject.productName+" - "+itemClassObject.expiryDate+" - Rs."+itemClassObject.rateOfTheProduct);
			System.out.println("Added.. Remaining Money :"+remainingMoney);
			
			/*
			 * inserting retrieve data to the cart
			 */
			dao.insertIntoCart(itemClassObject);
			System.out.println("\nCart after updation");
			dao.bill();
			
			
			
			
			}
			}
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Thank you,Aslam. for choosing our service.\nHere is your bill");
		dao.bill();
		moneySpent=this.balanceAmount-remainingMoney;
		System.out.println("_____________________________________________________");
		System.out.println("Total spent: Rs."+moneySpent);
		System.out.println("Your Remaining money:"+remainingMoney);
		System.out.println("Have a nice day");
		dao.updateMoney(customerId, remainingMoney);
		dao.purchase(customerInfoClassObject.customerId);
		dao.emptyCart();
		if(dao.con!=null) {
			dao.con.close();
		}
		}
		
	
}
