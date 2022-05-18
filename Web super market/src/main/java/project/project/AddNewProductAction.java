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
 * Servlet implementation class AddNewProductAction
 */
@WebServlet("/AddNewProductAction")
public class AddNewProductAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProductAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		
		Connection con = ConnectionProvider.getConnection();

		try(Statement st = con.createStatement()){
			
			
			st.execute("INSERT INTO `webshoppingapp`.`product` (`productName`, `price`) VALUES ('"+name+"', '"+price+"');");
			HttpSession session = request.getSession();
			session.getAttribute("adminEmail");
			response.sendRedirect("addNewProduct.jsp?msg=done");
			}catch(Exception e){
			response.sendRedirect("addNewProduct.jsp?msg=invalid");
			System.out.println(e+" addnew product action ");
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
