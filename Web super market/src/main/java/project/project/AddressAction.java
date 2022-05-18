package project;



import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddressAction
 */
@WebServlet("/AddressAction")
public class AddressAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String fullAddress ="";
		HttpSession Session = request.getSession();
		String email = Session.getAttribute("email").toString();
		
		String fullName = request.getParameter("fullName");
		String plotNumber = request.getParameter("plotNumber");
		String streetName = request.getParameter("streetName");
		String cityName = request.getParameter("cityName");
		String state = request.getParameter("state");
		String Pincode = request.getParameter("Pincode");
		String subtotal = request.getParameter("total");
		//Double.parseDouble(request.getParameter("total"));
		System.out.println(subtotal+" here");
		int id ;
		String cartProductName = "";
		Float price;
		int quantity;

		fullAddress = fullName+","+plotNumber+","+streetName+","+cityName+","+state+","+Pincode;
		Connection con = ConnectionProvider.getConnection();
		ResultSet rs = null;
		try(Statement st = con.createStatement()){
		rs = st.executeQuery("select * from cart where email='"+email+"'");
		while(rs.next())
		{
			id =  rs.getInt(3);
			cartProductName = rs.getString(4);
			price = rs.getFloat(5);
			quantity = rs.getInt(6);
			
			try (PreparedStatement ps = con.prepareStatement("INSERT INTO `webshoppingapp`.`bill` (`email`, `id`, `name`, `price`, `quantity`, `address`) VALUES (?, ?, ?, ?, ?, ?);");){
				
				ps.setString(1, email);
				ps.setInt(2, id);
				ps.setString(3, cartProductName);
				ps.setFloat(4, price);
				ps.setInt(5, quantity);
				ps.setString(6, fullAddress);
				ps.executeUpdate();
				
					} catch (Exception e) {
				System.out.println(e+"  inserting into bill ");
				
					}
			try (Statement stmt = con.createStatement()){
				
				stmt.execute("UPDATE `webshoppingapp`.`customers` SET `total money spent` = `total money spent`+'"+subtotal+"' WHERE (`email` = '"+email+"');");
				
			}catch (Exception ex) {
				System.out.println(ex+"  address action ");
			}
			
		}
		try(Statement s = con.createStatement()){
			s.execute("UPDATE `webshoppingapp`.`customers` SET `redemption` = `redemption`-1 WHERE (`email` = '"+email+"');");
		}
		try(Statement s = con.createStatement()){
			s.execute("UPDATE `webshoppingapp`.`customers` SET `redemption` = '0' WHERE (`redemption`<0);");
		}
		st.execute("DELETE FROM `webshoppingapp`.`cart` WHERE (`email` = '"+email+"');");
		response.sendRedirect("home.jsp?msg=bill");
		}
		catch(Exception e){System.out.println(e+ " statement st try");}
		finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { System.out.println(e);}
		    }
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { System.out.println(e);}
		    }
		}
		
	}

}
