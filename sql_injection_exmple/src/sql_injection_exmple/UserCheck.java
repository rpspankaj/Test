package sql_injection_exmple;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserCheck
 */
@WebServlet("/UserCheck")
public class UserCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String user = request.getParameter("user");
            Connection conn = null;
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "userdb";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "root";
            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url + dbName, userName, password);

                Statement st = conn.createStatement();
                String query = "SELECT * FROM  tbluser where userid='"+user+"'";
                out.println("Query : " + query);
                System.out.printf(query);
                ResultSet res = st.executeQuery(query);

                out.println("\nResults");
                while (res.next()) {
                	out.println("\n");
                    String s = res.getString("firstname");
                    String s1 = res.getString("userid");
                    out.println("&nbsp;&nbsp;&nbsp;" + s+"&nbsp;&nbsp;&nbsp;" + s1+ "<br>");
                }
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            out.close();
        }
	}
}
