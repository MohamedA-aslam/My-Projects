package project;


import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SignupAction")
public class SignupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupAction() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		
		String nonPassword = request.getParameter("password");
		
		String password = Encryption.encrypt(nonPassword);
		
		
		try(Connection con = ConnectionProvider.getConnection()){
			try(PreparedStatement ps = con.prepareStatement("INSERT INTO `webshoppingapp`.`customers` (`email`, `name`, `phoneno`, `password`) VALUES (?, ?, ?, ?);")){
			ps.setString(1, email);
			ps.setString(2, name);
			ps.setString(3, phoneNumber);
			ps.setString(4, password);
			ps.executeUpdate();
			HttpSession Session = request.getSession();
			Session.setAttribute("email", email);
			response.sendRedirect("home.jsp?msg=welcome");
		}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		catch(Exception e){
			System.out.println(e+"hinfag ");
			response.sendRedirect("register.jsp?msg=invalid");
		}

	}

}
