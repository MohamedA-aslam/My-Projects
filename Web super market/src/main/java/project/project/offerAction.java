package project;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class offerAction
 */
@WebServlet("/offerAction")
public class offerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession Session = request.getSession();
		String email = Session.getAttribute("email").toString();
		float offer;
		ResultSet rs = null;
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
    		rs = st.executeQuery("select `discount percentage` from customers where email='"+email+"'AND redemption >0 ;");
    		while(rs.next())
    		{
    			offer = rs.getFloat(1);
    			System.out.println(offer);
    			
    			response.sendRedirect("myCart.jsp?offer="+offer);
    		}
    		response.sendRedirect("myCart.jsp");
	}
	catch (Exception e) {
		System.out.println(e+" offeraction");
		
	}
	finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
			
		}	
	}

}
