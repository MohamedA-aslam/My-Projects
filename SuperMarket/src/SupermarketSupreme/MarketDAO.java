package SupermarketSupreme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MarketDAO {
	Connection con=null;
	
	/*
	 * 
	 * Object definition.
	 */
	Scanner sc=new Scanner(System.in);
	Item itemClassObject = new Item();
	CustomerInfo customerClassObject = new CustomerInfo();
	
	
	/*
	 * Connection establishment. 
	 */
	public void connect()
	{
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdb","root","");	
			}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	public void disconnect() throws SQLException {
		if(con!=null) {
			con.close();
		}
	}
	
	/*
	 * 
	 * creating admin account
	 * 
	 */
	public void registerNewAdmin() throws SQLException {
		connect();
		sc.nextLine();
		System.out.println("Enter Your Name : ");
		String newAdminName = sc.nextLine();
		System.out.println("Create Your Password");
		String newAdminPass = sc.next();
		String query="INSERT INTO `jdbcdb`.`admin` (`adminName`, `adminPassword`) VALUES (?,?);";
		PreparedStatement st = con.prepareStatement(query);
		try {
		
		st.setString(1, newAdminName);
		st.setString(2, newAdminPass);
		st.executeUpdate();
		} catch (Exception e) {
		System.out.println(e);		
		}finally {
			
		    if (st != null) {
		        try {
		            st.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
			
		}
		
	}
	
	/*
	 * 
	 * Retrieving data for admin login 
	 * 
	 */
	public boolean checkForAdmin() throws SQLException {
		connect();
		System.out.println("Enter your Admin Login ID :");
		int AdminLoginId = sc.nextInt();
		System.out.println("Enter your Password : ");
		String AdminLoginPass = sc.next();
		Statement s = con.createStatement();
		ResultSet rs = null;
		try {
		
		rs = s.executeQuery("select adminPassword from admin where adminID ="+AdminLoginId);
		rs.next();
		String passFromDatabase = rs.getString(1);
		if(AdminLoginPass.equals(passFromDatabase))   return true;
		
		} catch (Exception e) {
			System.out.println("Wrong Username/Password....\n");
			
		} finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (s != null) {
		        try {
		            s.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
			
		}
		return false;
	}
	
	/*
	 * 
	 *
	 * To retrieve specific Item from the database.
	 * 
	 * 
	 */
	
	public Item getItem(int productId) {
		try	{
			String query = " select * from supermarket where id="+productId;
			
			itemClassObject.productId = productId;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			rs.next();
			
			String name = rs.getString(2);
			itemClassObject.productName = name;
			
			String expiryDate = rs.getString(3);
			itemClassObject.expiryDate = expiryDate;
			
			int rateOfTheProduct = rs.getInt(4);
			itemClassObject.rateOfTheProduct = rateOfTheProduct;
			
			return itemClassObject;			//supposed to return object but getting exception ;
			
		}catch (InputMismatchException e) {
			System.out.println("Invalid Product ID , Please check menu before trying again");
			
		} 
		catch (Exception ex) {
				System.out.println("Invalid Product ID , Please check menu before trying again");
			}
		return null;
		}
	/*
	 * 
	 * Print all from table supermarket.
	 * 
	 */
	public void getAllItem() {
		Statement s = null;
		ResultSet rs = null;
	try {
			
			String query = " select * from supermarket ";
			s=con.createStatement();
			rs= s.executeQuery(query);
			System.out.println("--------------------------------------------------------");
			System.out.println("ID | Product Name |Expiry Date|Price Rate ");
			while(rs.next()) {
			System.out.println("--------------------------------------------------------");
			String name = rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getDate(3)+" | Rs."+rs.getInt(4);
			System.out.println(name);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (s != null) {
		        try {
		            s.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}
	}
	/*
	 * To Add New items into the supermarket database.
	 * 
	 */
	public void addItem(Item itemObj1) {
		String query="INSERT INTO `jdbcdb`.`supermarket` (`Pname`, `Expiry date`, `Price`) VALUES (?,?,?);";
		PreparedStatement st = null;
		try
		{
		st = con.prepareStatement(query);
		st.setString(1, itemObj1.productName);
		st.setString(2, itemObj1.expiryDate);
		st.setInt(3, itemObj1.rateOfTheProduct);
		st.executeUpdate();
		} catch (Exception e) {
		System.out.println("Hello");		
		} finally {
		    if (st != null) {
		        try {
		            st.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}
		
	}
	
	
	/*
	 * 
	 * 
	 */
	
	public CustomerInfo getCustomer(int itemObj1) {
		Statement s = null;
		ResultSet rs = null;
		try	{
			String query = " select * from customer where idCustomer="+itemObj1;
			
			customerClassObject.customerId=itemObj1;
			s=con.createStatement();
			rs= s.executeQuery(query);
			rs.next();
			
			String name = rs.getString(2);
			customerClassObject.customerName=name;
			
			int phone= rs.getInt(3);
			customerClassObject.phoneNumber=phone;
			
			String email=rs.getString(4);
			customerClassObject.customerEmail=email;
			
			int balance=rs.getInt(5);
			customerClassObject.balance=balance;
			
			return customerClassObject;			//supposed to return object but getting exception ;
			} catch (SQLException ex) {
				System.out.println("Invalid Customer ID !!!!!");
			} catch (Exception e) {
				System.out.println("Invalid Customer ID !!!!!");
			} finally {
				if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			    if (s != null) {
			        try {
			            s.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			    
			}
		
		return null;
		}
	public void addmoney() {
		System.out.println("Enter Customer Id:");
		int customerId = sc.nextInt();
		System.out.println("How much you want to assign: ");
		int paise = sc.nextInt();
		try (Statement stmt = con.createStatement();){
			
			String query = "UPDATE `jdbcdb`.`customer` SET `Balance` = Balance+"+paise+" WHERE (`idCustomer` = '"+customerId+"');";
			stmt.execute(query);
			
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	
	
	public void updateMoney(int productId,int amount) {
		try (Statement stmt = con.createStatement();){
			
			String query = "UPDATE `jdbcdb`.`customer` SET `Balance` = "+amount+" WHERE (`idCustomer` = "+productId+");";
			stmt.execute(query);
			
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	
	/*
	 * To get Customer Database
	 * 
	 */
	public void getCustomerDatabase() {
		Statement s = null;
		ResultSet rs = null;
		try {
			String query = " select * from Customer ";
			s=con.createStatement();
			rs= s.executeQuery(query);
			System.out.println("--------------------------------------------------------");
			System.out.println("CustomerID|Customer Name|Phone|Email ID|Current Balance ");
			while(rs.next()) {
			System.out.println("--------------------------------------------------------");
			String name = rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | Rs."+rs.getString(4)+" | "+rs.getInt(5);
			
			System.out.println(name);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (s != null) {
		        try {
		            s.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}
	}
	
	

	/*
	 * 
	 * Email validation 
	 * 
	 */
	
	public static boolean emailValidation(String email){
        String regex = "^(.+)@(.+)$";  
            Pattern pattern = Pattern.compile(regex);  
            Matcher matcher = pattern.matcher(email);  
            return matcher.matches();
    }
	
	/*
	 * To register customer
	 */
	
	void registerCustomer(){
		
		String customerName="";
		int phoneNo;
		String customerEmail="";
		sc.nextLine();
		System.out.println("Enter Customer's Name:");
		customerName=sc.nextLine();
		
		System.out.println("Enter Customer Number:");
		phoneNo=sc.nextInt();
		
		System.out.println("Enter Customer EmailID:");
		while(true) {
		customerEmail=sc.next();
		if(emailValidation(customerEmail)) break;
		else System.out.println("Inavild Email!!!\nEnter Valid Email id:");
		}
		System.out.println("Enter total money for Customer to use :");
		int paper=sc.nextInt();
		
		try (PreparedStatement st = con.prepareStatement("INSERT INTO `jdbcdb`.`customer` (`CustomerName`, `PhoneNumber`, `CustomerEmail`, `Balance`) VALUES (?, ?, ?, ?);");)
		{
			st.setString(1, customerName);
			st.setInt(2, phoneNo);
			st.setString(3, customerEmail);
			st.setInt(4, paper);
			st.executeUpdate();
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/*
	 * Billing
	 */
	public void bill() {
		Statement s = null;
		ResultSet rs = null;
		try {
			String query = " select * from cart ";
			s=con.createStatement();
			rs= s.executeQuery(query);
			System.out.println("--------------------------------------------------------");
			System.out.println("ID | Product Name |Expiry Date|Price Rate|quantity ");
			while(rs.next()) {
			System.out.println("--------------------------------------------------------");
			String name = rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getDate(3)+" | Rs."+rs.getInt(4)+"|"+rs.getInt(5);
			
			System.out.println(name);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (s != null) {
		        try {
		            s.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}
	}
	
	
	/*
	 * Cart
	 * 
	 */

	
	void insertIntoCart(Item itemObj1) {
		int quantity =1;
		String query="insert into cart values(?,?,?,?,?)ON DUPLICATE KEY UPDATE quantity=quantity+1";
		try (PreparedStatement st = con.prepareStatement(query);){
		
		st.setInt(1, itemObj1.productId);
		st.setString(2, itemObj1.productName);
		st.setString(3, itemObj1.expiryDate);
		st.setInt(4, itemObj1.rateOfTheProduct);
		st.setInt(5,quantity);
		st.executeUpdate();
	}catch (Exception e) {
		System.out.println("Wrong Product Id !!");
	}
		
	}
	
	
	
	/*
	 * truncate Cart/emptying it
	 */
	
	public void emptyCart() {
		try (Statement stmt = con.createStatement();){
		String query = "Truncate table cart";
		stmt.execute(query);
		System.out.println("Cart emptied");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/*
	 * Removing item
	 */
	public void alterCart(int id) {
		try (Statement stmt = con.createStatement();){
			
			String query = "update cart set quantity=quantity-1 where id ="+id;
			stmt.execute(query);	
			stmt.execute("DELETE FROM cart WHERE quantity=0 AND id="+id);
			System.out.println("\nUpdated Cart");
			bill();
		}catch (Exception e) {
			System.out.println("Invalid Customer ID !!!!!");
		}
	}
	
	/*
	 * 
	 * Transferring cart items to purchase table 
	 * 
	 */
	
	public void purchase(int customerId) {
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 String sql = "INSERT INTO purchase("+ "id,"+ "ProductName,"+ "Price,"+ "quantity,"+"customerId)"+ "VALUES(?,?,?,?,?)";

	            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

	          ps = con.prepareStatement(sql);

	            rs = st.executeQuery("SELECT * FROM cart");
	            while (rs.next()) {
	               	int nm = rs.getInt(1);
	               	String ac = rs.getString(2);
	                int log = rs.getInt(4);
	                int pass = rs.getInt(5);

	                ps.setInt(1, nm);
	                ps.setString(2, ac);
	                ps.setInt(3, log);
	                ps.setInt(4, pass);
	                ps.setInt(5, customerId);

	                ps.executeUpdate();
	            }
		}
		catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		} finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (st != null) {
		        try {
		            st.close();
		        } catch (SQLException e) { /* Ignored */}
		    
		    }
		}
	}
	
	
	/*
	 * Display whole purchase history.
	 */
	
	public void purchaseHistory() {
		Statement s = null;
		ResultSet rs = null;
		try {
			String query = " select * from purchase ";
			s = con.createStatement();
			rs = s.executeQuery(query);
			System.out.println("--------------------------------------------------------");
			System.out.println("ID | Product Name |Price Rate|quantity|Customer Name ");
			while(rs.next()) {
			System.out.println("--------------------------------------------------------");
			String name = rs.getInt(1)+" | "+rs.getString(2)+" \t| "+rs.getInt(3)+" \t| Rs."+rs.getInt(4)+"|\t"+rs.getString(5);
			
			System.out.println(name);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (s != null) {
		        try {
		            s.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}
	}
}
