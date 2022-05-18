package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class discountAction
 */
@WebServlet("/discountAction")
public class discountAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("customerEmail");
		String discount = request.getParameter("discount");
		String redemption = request.getParameter("redemption");
		System.out.println(discount+ "%"+redemption);
		
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
			st.execute("UPDATE `webshoppingapp`.`customers` SET `discount percentage` = '"+discount+"', redemption = '"+redemption+"' WHERE (`email` = '"+email+"');");
			
			response.sendRedirect("allcustomer.jsp");
			} 
		catch (Exception e) {
				System.out.println(e+" discount action");
				
			}
		finally {
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}
	}

}
