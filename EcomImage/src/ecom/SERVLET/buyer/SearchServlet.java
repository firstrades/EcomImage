package ecom.SERVLET.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.DAO.Buyer.SearchDAO;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SearchDAO searchDAO;
    
	@Override
	public void init() throws ServletException {
		this.searchDAO = SearchDAO.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered SearchServlet");
		
		String servletPath = request.getServletPath();
		
		if (servletPath.equals("/searchByKeyWord")) {
			
			System.out.println("Entered SearchServlet");
			
			//getRequest
			String keyWord = request.getParameter("keyWord");
			
			//Database
			Set<String> keyWordSet = new TreeSet<String>();
			searchDAO.searchKeyWordsFromCompanyName(keyWord, keyWordSet);
			searchDAO.searchKeyWordsFromSubCategory(keyWord, keyWordSet);
			searchDAO.searchKeyWords(keyWord, keyWordSet);
			
			
			//------------------------------------------
			PrintWriter out = response.getWriter();
			for (String key : keyWordSet) {
				out.println(key);
			}
		}//searchByKeyWord
	}

}

//http://localhost:8080/EcomImage/searchByKeyWord?keyWord=mob
