package SupermarketSupreme;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
	
	Scanner sc = new Scanner(System.in);
	CustomerInfo customerInfoClassObject = new CustomerInfo();
	MarketDAO dao = new MarketDAO();
	Item itemClassObject = new Item();
	
	public void adminLogin() {
		while(true) {
			dao.connect();
			/*
			 * 
			 * Admin login process.
			 * 
			 */
			try {
			System.out.println("\n\n\n");
			System.out.println("__________________________________________");
			System.out.println("Welcome to Admin Login .\n Would like to\n "
					+ "(1) login as existing user "
					+ "(2) create new one ");
			
			int adminLoginOption = sc.nextInt();
			if(adminLoginOption==1)	{
				while(true) {
				if(dao.checkForAdmin()) break;
				else continue;
				}
			}
			else if(adminLoginOption==2)	dao.registerNewAdmin();
			else 	{System.out.println("Enter valid option"); continue;}
			break;
			}
			catch (InputMismatchException e) {
				System.out.println("\n\nTry Again....");
				sc.nextLine();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
		
	
	
	public void adminPrivilege() throws SQLException {
		
		adminLogin();
		
		
		/*Admin's tasks below.
		 * 
		 * Some of the objects creation for this class.
		 * 
		 */
		
		
		
		
		while(true) {
//		dao.connect();	
		System.out.println("\n\n\n_____________________");
		System.out.println("Do you want to \n1:Enter new Items\n2:Fetch data\n3:Create a new Customer Account and assign money\n4:To display all items\n5:See All Customer Details\n6:Fetch a customer details\n7:Add money to existing customer\n8:Review purchase history\n0:Exit");
		int menuOption=sc.nextInt();
		
		
		/*
		 * 
		 * To add new entries of product/items.
		 * 
		 */
		
		if(menuOption==1) {											//modify it as switch case.
			
			System.out.println("Enter Product Name:");
			itemClassObject.productName=sc.next();
			System.out.println("Enter the expiry date (YYYY,MM,DD):");
			itemClassObject.expiryDate=sc.next();
			System.out.println("Enter product rate:");
			itemClassObject.rateOfTheProduct=sc.nextInt();
		
		dao.addItem(itemClassObject);
		}
		/*
		 * 
		 * To Display data for selected Id.
		 * 
		 */
		else if(menuOption==2) {
			System.out.println("Enter valid id to fetch data");
			int fetchId=sc.nextInt();
			itemClassObject=dao.getItem(fetchId);
			if(itemClassObject == null)				continue;
			System.out.println(itemClassObject.productId+" - "+itemClassObject.productName+" - "+itemClassObject.expiryDate+" - Rs."+itemClassObject.rateOfTheProduct);

			}
		
		
		else if(menuOption==3) 			dao.registerCustomer();								//To registor new customer and sending details to customer table.
		else if(menuOption==4)			dao.getAllItem();   								// display all of the items from supermarket table.
		else if(menuOption==5)			dao.getCustomerDatabase();							// display all of the customers detail.
		else if(menuOption==6){ 
						System.out.println("Enter Customer ID: "); 
						int customerID=sc.nextInt();
						
						customerInfoClassObject=dao.getCustomer(customerID);									//gets specific row of data and returns as an object of a class we created. 
						if(customerInfoClassObject == null)						continue;
						System.out.println("CustomerID :"+customerInfoClassObject.customerId);
						System.out.println("Customer Name :"+customerInfoClassObject.customerName);
						System.out.println("Phone Number  :"+customerInfoClassObject.phoneNumber);
						System.out.println("Email Address :"+customerInfoClassObject.customerEmail);
						System.out.println("Remaining Balance:"+customerInfoClassObject.balance);
		}
		else if(menuOption==7) 			dao.addmoney();										// Add money to customer by customerId in customer table.													
		else if(menuOption==8)			dao.purchaseHistory();								//Displaying details of purchase table which shows whole purchase history.
		else if(menuOption==0)			break;												// Exiting the while loop.
		else							System.out.println("Enter valid option");
		dao.disconnect();
		
		}
	}
}
