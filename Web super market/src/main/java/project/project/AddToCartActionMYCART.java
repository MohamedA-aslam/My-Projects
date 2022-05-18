package project;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
 * 
 * this class will increase quantity in cart
 * 
 */





@WebServlet("/AddToCartActionMYCART")
public class AddToCartActionMYCART extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartActionMYCART() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession Session = request.getSession();
		String id = request.getParameter("id");
		String email = Session.getAttribute("email").toString();
		String idAndEmail = id+"."+email;
		float productPrice = 0;
		String productName = "";
		@SuppressWarnings("unused")
		int check = 0;
		Connection con = ConnectionProvider.getConnection();
		ResultSet rs = null;
		try(Statement st = con.createStatement()){
			rs = st.executeQuery("select * from product where idproduct="+id);
			while(rs.next())
			{
				productName = rs.getString(2);
				productPrice = rs.getInt(3);
			}
				try (PreparedStatement ps = con.prepareStatement("INSERT INTO `webshoppingapp`.`cart` (`idAndEmail`, `email`, `id`, `name`, `price`) values(?,?,?,?,?)ON DUPLICATE KEY UPDATE quantity=quantity+1");){
			check = 1;
			ps.setString(1, idAndEmail);
			ps.setString(2, email);
			ps.setString(3, id);
			ps.setString(4, productName);
			ps.setFloat(5, productPrice);
			ps.executeUpdate();
			response.sendRedirect("myCart.jsp?msg=done");
				} catch (Exception e) {
			System.out.println(e+"  Wrong Product Id !!");
			response.sendRedirect("myCart.jsp?msg=invalid");
				}

			}
		catch (Exception e) {
				System.out.println(e+"add to cart action");
				response.sendRedirect("myCart.jsp?msg=invalid");
			}
		finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { System.out.println(e);}
		    }
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { System.out.println(e);}
		    }
		}
	}

}
