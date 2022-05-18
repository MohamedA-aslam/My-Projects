package project;
import java.io.IOException;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class BlockAction
 */
@WebServlet("/BlockAction")
public class BlockAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BlockAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("customerEmail");
		
		Connection con = ConnectionProvider.getConnection();
		
		try(Statement st = con.createStatement()){
			
			
			
			
			st.execute("UPDATE `webshoppingapp`.`customers` SET `status` = 'blocked' WHERE (`email` = '"+email+"');");
			
			response.sendRedirect("allcustomer.jsp");
			} catch (Exception e) {
				System.out.println(e+"add to cart action");
				
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
