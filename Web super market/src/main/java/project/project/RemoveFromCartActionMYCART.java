package project;
import java.io.IOException;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
 * 
 * this class will increase quantity in cart
 * 
 */





@WebServlet("/RemoveFromCartActionMYCART")
public class RemoveFromCartActionMYCART extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartActionMYCART() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession Session = request.getSession();
		String id = request.getParameter("id");
		String email = Session.getAttribute("email").toString();
		String idAndEmail = id+"."+email;

		@SuppressWarnings("unused")
		int check = 0;
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
			st.execute("UPDATE `webshoppingapp`.`cart` SET `quantity` = quantity-1 WHERE (`idAndEmail` = '"+idAndEmail+"');");
			st.execute("delete from cart where quantity =0");
			response.sendRedirect("myCart.jsp?msg=removed");
			} catch (Exception e) {
				System.out.println(e+"add to cart action");
				response.sendRedirect("myCart.jsp?msg=invalid");
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

