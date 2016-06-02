package ecom.validations;

import javax.servlet.http.HttpServletRequest;

public class CustomerRegistrationValidation implements Validation {

	private String[] requestParameters;	
	
	@Override
	public void setRequestParameters(String[] requestParameters) {
		this.requestParameters = requestParameters;		System.out.println("Setter");
	}
	
	@Override
	public String validate(HttpServletRequest request) {  System.out.println("String2: "+requestParameters[0]);
		
		//------------------------------------------------Initialize
		
		String userId = requestParameters[0];
		String password = requestParameters[1];
		
		String errorMessage = null;
		String nextPage = null;
		
		
		
		
		//------------------------------------------------Validation
		
		boolean isUserExists = userId != null && userId != "";
		boolean isPasswordExists = password != null && password != "";
		
		if (!isUserExists) {
			errorMessage = "User cannot be empty";   System.out.println(errorMessage);
		} else if (!isPasswordExists) {
			errorMessage = "Password cannot be empty";    System.out.println(errorMessage);
		}
		
		if (isUserExists) {
			//Check validity of mobile number
			for (int i = 0; i < userId.length(); i++) {
				if ("0123456789".indexOf(userId.charAt(i)) == -1) {
					errorMessage = "Please insert valid mobile number as user id.";    System.out.println(errorMessage);
					break;
				}
			}
		}
		
		
		
		//------------------------------------------------NextPage
		
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
			nextPage = "ErrorPages/errorMessage.jsp";
		}
		
		return nextPage;  // if nextPage = null, then no error occurred.
	}

		
}
