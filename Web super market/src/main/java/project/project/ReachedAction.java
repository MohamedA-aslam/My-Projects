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
@WebServlet("/ReachedAction")
public class ReachedAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReachedAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sno = request.getParameter("sNo");
		String currpage = request.getParameter("currpage");
		System.out.println(currpage);

		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
			st.execute("UPDATE `webshoppingapp`.`bill` SET `product reached or not` = 'reached' WHERE (`sno` = '"+sno+"') ;");
			if(currpage.equals("unreached")) {
				response.sendRedirect("unreachedPurchase.jsp");
			}
			else {
				response.sendRedirect("allPurchase.jsp");
			}
			} 
		catch (Exception e) {
				System.out.println(e+" all reAction");
				
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

