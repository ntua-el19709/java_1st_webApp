package lab4.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab4.db.DbConnector;
import lab4.db.model.User;
import lab4.db.model.Task;

/**
 * Servlet implementation class ViewTasksServlet
 */
@WebServlet("/ViewTasksServlet")
public class ViewTasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTasksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final HttpSession session = request.getSession();
		
		// Check User Data if being necessary
		if (session.getAttribute("user") == null) {
			// Get Request Parameters
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");
			
			try {
				// Find User (if any)
				DbConnector.getInstance().openDbConnection();
				final User user = DbConnector.getInstance().getUser(username, password);
				DbConnector.getInstance().closeDbConnection();
				System.out.println(user);
				// Update Session Data on condition that the User was found
				if (user != null) {
					session.setAttribute("user", user);
				}
			
			} catch (Throwable t) {
				// Inform the user in case of an Error
				final String errMsg = "Error ... " + t.getMessage() + " Ask system administrators for details !";
				response.getWriter().append(errMsg);
				t.printStackTrace();
				return;
			}
		}
		
		// Get User Data from Session
		final User sessionUser = (User) session.getAttribute("user");
		
		if (sessionUser == null) {
			// Redirect User to Login Page
			response.sendRedirect( "Login.html"); 
		} else {
			response.setContentType("text/html; charset=UTF-8");
			final PrintWriter out = response.getWriter();
			
			out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>PROJECTNAME</title>\n");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/WebApp2.css\" >");
            out.println("</head><body>");
            
            out.print("<p>Hello: " + sessionUser.getUsername() + " <a href=\"LogoutServletex\">Logout</a></p>");
            out.print("<p>Role: "+ sessionUser.getTypeName()+"</p>");
            
            if(sessionUser.getUserType()==2) {
            	final List<Task> Tasklist;
    			try {
    				// Get ALL vehicles
    				DbConnector.getInstance().openDbConnection();
    				Tasklist = DbConnector.getInstance().getTasks(sessionUser.getId());
    				DbConnector.getInstance().closeDbConnection();
    			} catch (Throwable t) {
    				// Inform the user in case of an Error
    				final String errMsg = "Error ... " + t.getMessage() + " Ask system administrators for details !";
    				response.getWriter().append(errMsg);
    				t.printStackTrace();
    				return;
    			}
    			out.print("<h2>View Tasks</h2>");
    			out.println("<table>");
				out.println("<tr>");
				out.println("<th>ID</th>");
				out.println("<th>Title</th>");
				out.println("<th>Description</th>");
				out.println("<th>Status</th>");
				out.println("<th>Date</th>");
				out.println("</tr>");
				for (Task task : Tasklist) {
					int size=50;
					if(size>(task.getComments()).length())size=(task.getComments()).length()-1;
					out.println("<tr>");
					out.println("<td>" + task.getTaskId() + "</td>");
					out.println("<td>" + task.getTitle() + "</td>");
					out.println("<td>" + (task.getComments()).substring(0,size)+ "</td>");
					out.println("<td>" + task.getStatus() + "</td>");
					String pattern="dd/MM/yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					out.println("<td>" + simpleDateFormat.format(task.getStartDate()) + "</td>");
					out.println("<td><form action=\"UpdateTask.jsp\" method=\"POST\">"
							+ "<input type=\"hidden\" name=\"taskid\" value=\"" + task.getTaskId() + "\">"
							+ "<input type=\"hidden\" name=\"stat\" value=\"" + task.getStatus() + "\">"
							+ "<input type=\"submit\" value=\"Change Status\">"
							+ "</form></td>");
					out.println("</tr>");
					out.println("</tr>");
				}
				out.println("</table>");
            }
            
			
			out.println("</body></html>");
			
			out.close();
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
