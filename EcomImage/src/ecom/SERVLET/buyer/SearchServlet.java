package ecom.SERVLET.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecom.DAO.Buyer.BuyerSearchDAO;
import ecom.DAO.Buyer.SearchDAO;
import ecom.beans.Handler;
import ecom.common.FrequentUse;
import ecom.model.Product;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SearchDAO searchDAO;
	private Handler handler;
    
	@Override
	public void init() throws ServletException {
		this.searchDAO = SearchDAO.getInstance();
		this.handler = Handler.getInstance();
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
		HttpSession session = request.getSession();
		
		if (servletPath.equals("/searchByKeyWord")) {
			
			System.out.println("Entered searchByKeyWord");
			
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
		
		else if (servletPath.equals("/searchProductsWithKeyWords")) {
			
			System.out.println("Entered searchProductsWithKeyWords");			
			
			//getRequest
			String keyWords = request.getParameter("keyWords");
			
			//Database
			String[] twoKeys = handler.getTwoKeys(keyWords);
			String[] threeKeys = handler.getThreeKeys(keyWords);
			
			BuyerSearchDAO buyerSearchDAO = BuyerSearchDAO.getInstance();
			List<Product> productBeanList1 = null;
			if (twoKeys != null) {
				productBeanList1 = buyerSearchDAO.searchBySubCategory(twoKeys, null);
			} else {
				productBeanList1 = buyerSearchDAO.searchBySubCategory(threeKeys, null);
			}
			
			handler.searchBySubCategory_BuyerServlet(productBeanList1, session, null, request, FrequentUse.MAX, response);
			
			
			/*//------------------------------------------
			PrintWriter out = response.getWriter();
			if (twoKeys != null) {
				out.println(twoKeys[0] + " " + twoKeys[1]);
			} else {
				out.println(threeKeys[0] + " " + threeKeys[1] + " " + threeKeys[2]);
			}*/
		}//searchProductsWithKeyWords
	}

}

//http://localhost:8080/EcomImage/searchByKeyWord?keyWord=mob
