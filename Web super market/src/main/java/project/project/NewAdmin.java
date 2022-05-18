package project;


import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/NewAdmin")
public class NewAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		String nonPassword = request.getParameter("password");
		
		String password = Encryption.encrypt(nonPassword);
		
		
		try(Connection con = ConnectionProvider.getConnection()){
			try(PreparedStatement ps = con.prepareStatement("INSERT INTO `webshoppingapp`.`customers` (`email`,  `password`, `userType`, `name`) VALUES (?, ?, ?, ?);")){
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, "admin");
			ps.setString(4, "admin");
			ps.executeUpdate();
			
			response.sendRedirect("adminHome.jsp?msg=welcome");
		}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		catch(Exception e){
			System.out.println(e+"hinfag ");
			response.sendRedirect("newAdmin.jsp?msg=invalid");
		}

	}

}
