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
@WebServlet("/ReviewedAction")
public class ReviewedAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReviewedAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sno = request.getParameter("sno");
		
		Connection con = ConnectionProvider.getConnection();
		
		try(Statement st = con.createStatement()){
			
			
			
			
			st.execute("UPDATE `webshoppingapp`.`query` SET `Status` = 'reviwed' WHERE (`Sno` = '"+sno+"');");
			
			response.sendRedirect("customerMessages.jsp");
			} catch (Exception e) {
				System.out.println(e+" customerMessagesACtion");
				
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
