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
@WebServlet("/MessageUsAction")
public class MessageUsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession Session = request.getSession();
		String email = Session.getAttribute("email").toString();
		
		String fName = request.getParameter("firstname");
		String lName = request.getParameter("lastname");
		String country = request.getParameter("country");
		String subject = request.getParameter("subject");
		Connection con = ConnectionProvider.getConnection();
		

			try (PreparedStatement ps = con.prepareStatement("INSERT INTO `webshoppingapp`.`query`(`email`,`Fname`,`Lname`,`Country`,`Subject`) VALUES (?,?,?,?,?);")){
				
				ps.setString(1, email);
				ps.setString(2, fName);
				ps.setString(3, lName);
				ps.setString(4, country);
				ps.setString(5, subject);
				
				ps.executeUpdate();
				
					} catch (Exception e) {
				System.out.println(e+"  inserting into message ");
				
					}
		finally {
			
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { System.out.println(e);}
		    }
			response.sendRedirect("home.jsp");
		}
		
	}

}