package project;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;	

	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String emailId = request.getParameter("email");
		String nonPassword = request.getParameter("password");
		String password = Encryption.encrypt(nonPassword);
		
		
		int z=0;
		ResultSet rs = null;
		Connection con = ConnectionProvider.getConnection();
		try(Statement st = con.createStatement()){
			rs = st.executeQuery("select *from customers where email='"+emailId+"' AND password= '"+password+"'");
			while(rs.next())
			{
				String who = rs.getString(6);
				if(who.equals("admin")) {
				z=1;
				HttpSession Session = request.getSession();
				Session.setAttribute("adminEmail", emailId);
				response.sendRedirect("adminHome.jsp");
				}
				else if(who.equals("customer")) {
					z=1;
					HttpSession Session = request.getSession();
					Session.setAttribute("email", emailId);
					response.sendRedirect("home.jsp");
					}
			}
			if(z==0)
				response.sendRedirect("login.jsp?msg=notexist");
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e+"  loginaction");
			response.sendRedirect("login.jsp?msg=invalid");
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
