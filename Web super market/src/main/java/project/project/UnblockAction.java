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
@WebServlet("/UnblockAction")
public class UnblockAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UnblockAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("customerEmail");
		System.out.println(email);

		
		try(Connection con = ConnectionProvider.getConnection()){			
			try(Statement st = con.createStatement()) {
				st.execute("UPDATE `webshoppingapp`.`customers` SET `status` = 'unblocked' WHERE (`email` = '"+email+"');");
				
				response.sendRedirect("allcustomer.jsp");	
			} catch (Exception e) {
				System.out.println(e);
			}			
			} catch (Exception e) {
				System.out.println(e+"add to cart action");
				
			}
	}

}
