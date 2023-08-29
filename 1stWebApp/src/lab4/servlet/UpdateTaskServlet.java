package lab4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab4.db.DbConnector;
import lab4.db.model.User;

/**
 * Servlet implementation class UpdateTaskServlet
 */
@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final HttpSession session = request.getSession();
		final User sessionUser = (User) session.getAttribute("user");
		final Integer taskid = (Integer) session.getAttribute("taskid");
		
		if (sessionUser == null) {
			// Redirect User to Login Page
			response.sendRedirect( "Login.html"); 
		}
		else {
			try {
				// Update Status
				final DbConnector db = DbConnector.getInstance();
				db.openDbConnection();
				
				db.updateTaskStatus(Integer.parseInt(request.getParameter("my-select")),taskid);
				db.closeDbConnection();
				
				response.setContentType("text/html; charset=UTF-8");
				final PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html>");
		        out.println("<html><head>");
		        out.println("<title>PROJECTNAME</title>\n");
		        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/WebApp2.css\" >");
		        out.println("</head><body>");
		        
		        out.print("<p>Hello: " + sessionUser.getUsername() + " <a href=\"LogoutServletex\">Logout</a></p>");
		        out.print("<p>Status Changed </p>");
		        out.print("<a href=\"ViewTasksServlet\">Go to main page.</a>");
		        out.println("</body></html>");
				
				out.close();
			} catch (Throwable t) {
				// Inform the user in case of an Error
				final String errMsg = "Storing Reservation Problem ... " + t.getMessage() + " Ask system administrators for details !";
				response.getWriter().append(errMsg);
				t.printStackTrace();
			}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
