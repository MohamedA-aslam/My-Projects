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
 * Servlet implementation class EditProductAction
 */
@WebServlet("/EditProductAction")
public class EditProductAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession Session = request.getSession();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		Session.getAttribute("email");
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
			st.execute("UPDATE `webshoppingapp`.`product` SET `productName` = '"+name+"', `price` = '"+price+"' WHERE (`idproduct` = "+id+");");
			
			response.sendRedirect("editProduct.jsp?msg=done");
			}
		catch(Exception e){
			response.sendRedirect("editProduct.jsp?msg=invalid");
			System.out.println(e+" edit product action ");
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
