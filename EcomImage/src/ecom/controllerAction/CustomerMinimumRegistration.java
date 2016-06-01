package ecom.controllerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ecom.DAO.User.CreateUserDAO;
import ecom.DAO.User.UserDAO;
import ecom.model.User;

public class CustomerMinimumRegistration {
	//External
	private String userId;
	private String password;
	//Internal
	private String errorMessage;
	private UserDAO userDAO;
	private int userIdNo;  //'0' user exists, 'maxId > 0' user created, '-1' some error occurred
	private String nextPage;	
	private User user;
	
	public CustomerMinimumRegistration() {
		userDAO = UserDAO.getInstance();
		errorMessage = null;
		userIdNo = -1;
		nextPage = null;
		user = null;
	}

	public String execute(HttpSession session, HttpServletRequest request) {
		
		try {
			/********* Database ***********/				
			userIdNo = CreateUserDAO.createCustomer(userId, password);
			
			if (userIdNo > 0)
				user = userDAO.getUser(userIdNo);
			else if (userIdNo == 0)
				throw new Exception();
			else if (userIdNo < 0)
				throw new Exception();
			
			/*********** setSession **************/
			session.setAttribute("user", user);
			
			nextPage = "jsp_Buyer/BuyerMainPanel.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			if (userIdNo > 0) {
				CreateUserDAO.deleteCustomer(userIdNo);
			}
			if (userIdNo == 0)
				errorMessage = "User already exists!";
			else
				errorMessage = "Some problem occured!";
			//error page
			nextPage = "ErrorPages/errorMessage.jsp";
		}
		
		request.setAttribute("errorMessage", errorMessage);
		
		return nextPage;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}






































/*else if (servletPath.equals("/customerMinimumRegistration")) {

System.out.println("Entered customerMinimumRegistration");

int userIdNo = -1;  //'0' user exists, 'maxId > 0' user created, '-1' some error occurred
String nextPage = null;
String errorMessage = null;
User user = null;

try {

	*//*********** getRequest ************//*			
	String userId   = request.getParameter("userId")  .trim();    
	String password = request.getParameter("password").trim(); 			
	
	*//********* Database ***********//*				
	userIdNo = CreateUserDAO.createCustomer(userId, password);
	
	if (userIdNo > 0)
		user = userDAO.getUser(userIdNo);
	else if (userIdNo == 0)
		throw new Exception();
	else if (userIdNo < 0)
		throw new Exception();
	
	*//*********** setSession **************//*
	session.setAttribute("user", user);
	
	nextPage = "jsp_Buyer/BuyerMainPanel.jsp";
	
} catch (Exception e) {				
	e.printStackTrace();
	if (userIdNo > 0) {
		CreateUserDAO.deleteCustomer(userIdNo);
	}
	if (userIdNo == 0)
		errorMessage = "User already exists!";
	else
		errorMessage = "Some problem occured!";
	//error page
	nextPage = "ErrorPages/errorMessage.jsp";
}

request.setAttribute("errorMessage", errorMessage);			
request.getRequestDispatcher(nextPage).forward(request, response);

}//customerMinimumRegistration
*/	
