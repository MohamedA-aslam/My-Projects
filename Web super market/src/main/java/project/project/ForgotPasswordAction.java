package project;



import java.sql.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ForgotPasswordAction")
public class ForgotPasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	String email = request.getParameter("email");
		String number = request.getParameter("phoneNumber");
		String nonPassword = request.getParameter("newPassword");
		String password = Encryption.encrypt(nonPassword);
		
		
		ResultSet r = null;
		int check = 0;
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
			
			r = st.executeQuery("select *from customers where email='"+email+"' AND phoneno= '"+number+"'");
			while(r.next())
			{
				check=1;
				
				st.executeUpdate("UPDATE `webshoppingapp`.`customers` SET `password` = '"+password+"' WHERE (`email` = '"+email+"');");
				HttpSession Session = request.getSession();
				Session.setAttribute("email", email);
				response.sendRedirect("forgotPassword.jsp?msg=done");
			}
			
			if(check==0){
				response.sendRedirect("forgotPassword.jsp?msg=invalid");
			}
			
		}
		catch(Exception e){
			System.out.println(e+" forgotpassword");
		}
		finally {
			if (r != null) {
		        try {
		            r.close();
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
