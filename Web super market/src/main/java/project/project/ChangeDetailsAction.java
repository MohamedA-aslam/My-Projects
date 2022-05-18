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
 * Servlet implementation class ChangeDetailsAction
 */
@WebServlet("/ChangeDetailsAction")
public class ChangeDetailsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
    
    public ChangeDetailsAction() {
        super();
        // TODO Auto-generated constructor stub
    }
 */
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession Session = request.getSession();
		
		String name = request.getParameter("newName");
		String phoneNumber = request.getParameter("newNumber");
		String nonPassword = request.getParameter("newPassword");
		String password = Encryption.encrypt(nonPassword);
		String email = Session.getAttribute("email").toString();
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
			st.execute("UPDATE `webshoppingapp`.`customers` SET `name` = '"+name+"', `phoneno` = '"+phoneNumber+"', `password` = '"+password+"' WHERE (`email` = '"+email+"');");
			response.sendRedirect("changeDetails.jsp?msg=done");
			}
		catch(Exception e){
			response.sendRedirect("changeDetails.jsp?msg=invalid");
			System.out.println(e+" changeDetails.jsp action ");
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
