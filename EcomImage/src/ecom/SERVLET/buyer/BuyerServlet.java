package ecom.SERVLET.buyer;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import ecom.DAO.Buyer.BuyerSearchDAO;
import ecom.DAO.User.UserDAO;
import ecom.Implementation.Courier.SOAP.EstimatedRateAndDeliveryBean;
import ecom.Implementation.Courier.SOAP.SearchLocationByPostal;
import ecom.Implementation.Project.MultiShippingDelivery;
import ecom.Interface.Courier.EstimatedRateAndDelivery;
import ecom.Interface.Courier.SearchLocationByPostalInterface;
import ecom.beans.BuyerServletHelper;
import ecom.beans.CartAttributesBean;
import ecom.beans.TransientData;
import ecom.model.CartWishlist;
import ecom.model.CustomerOrderHistroy;
import ecom.model.DeliveryAddress;
import ecom.model.Product;
import ecom.model.TwoObjects;
import ecom.model.User;

public class BuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BuyerServletHelper helper;
	private BuyerSearchDAO     buyerSearchDAO;
	
	@Override
	public void init() {
		this.helper         = BuyerServletHelper.getNewInstance();
		this.buyerSearchDAO = BuyerSearchDAO.getInstance();
	}
	
	@Override
	public void destroy() { 
		System.gc();
		System.out.println("BuyerServlet Destroyed"); 
	};
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
		
		HttpSession session = request.getSession();
		
		if (servletPath.equals("/SearchBySubCategory")) {
			
			System.out.println("Entered SearchBySubCategory");
			Integer MAX = 50;
			
			/******************************************
			 			*  Get Request  *
			 ******************************************/
			String errorMsg = null;
			
			String subCategory = request.getParameter("subCategory");         //System.out.println(subCategory);
			
			if	(request.getParameter("errorMsg") != null)	{ System.out.println("Jewel");
				errorMsg = (String) request.getParameter("errorMsg");
			}
			
			/******************************************
			 	* Database Search for product table *
			 ******************************************/			
			List<Product> productBeanList1 = buyerSearchDAO.searchBySubCategory(subCategory);	
			
			/*********************************************
			 				* Process *
			 *********************************************/
			
			List<Product> productBeanList = new ArrayList<>();
			
			if (productBeanList1.size() < MAX)    // set MAX
				MAX = productBeanList1.size();
			
			for (int i = 0; i < MAX; i++) {
				
				Product productBean = new Product();				
				
				productBean = productBeanList1.get(i);	System.out.println(i);	//Make a list of MAX data out of total list		
				productBeanList.add(productBean);
			}
			
			//int productBeanList1_Size = productBeanList1.size();
			
			/******************************************
			 			* Set Session *
			 ******************************************/			
			session.setAttribute("productBeanList1", productBeanList1);			
			
			/******************************************
			 			* Set Request *
			 ******************************************/
			if (errorMsg != null)
				request.setAttribute("errorMsg", errorMsg);
			
			request.setAttribute("productBeanList", productBeanList);
			request.setAttribute("MAX", MAX);
			
			/******************************************
			 			* Next Page *
			 ******************************************/
			request.getRequestDispatcher("jsp_Buyer/BuyerSearchPage.jsp").forward(request, response);
	
		} 
		
		else if (servletPath.equals("/SearchBySubCategory_Ajax")) {
			
			System.out.println("Entered SearchBySubCategory_Ajax");
			
			Integer MAX = 50;
			
			/******************************************
			 			*  Get Request  *
			 ******************************************/			
			//String subCategory = request.getParameter("subCategory");         System.out.println(subCategory);
			String MIN1        = request.getParameter("MAX1");              //System.out.println("MIN1: " + MIN1);
			
			/******************************************
			 			*  Get Session  *                   
			 ******************************************/			
			@SuppressWarnings("unused")
			String buyerCode = (String) session.getAttribute("buyerCode");    // no use till now
			@SuppressWarnings("unchecked")
			List<Product> productBeanList1 = (List<Product>) session.getAttribute("productBeanList1");
			
			/******************************************
							* Process *
			******************************************/
			
			int MIN = Integer.parseInt(MIN1);
			int itemMAX = MIN + MAX;			
			int productBeanList1_Size = productBeanList1.size();
			boolean residueProduct = false;
			
			if (itemMAX > productBeanList1_Size) {
				
				itemMAX = productBeanList1_Size;
				residueProduct = true;
			}			
			
			List<Product> productBeanList = new ArrayList<>();
			
			for (int i = MIN; i < itemMAX; i++) {
				
				Product productBean = new Product();				
				
				productBean = productBeanList1.get(i);				
				productBeanList.add(productBean);
			}
			
			
			
			/******************************************
			 	* Database Search for product table *
			 ******************************************/
			//BuyerSearchDAO buyerSearchDAO = new BuyerSearchDAO();
			//List<ProductBean> productBeanList = buyerSearchDAO.searchBySubCategory(subCategory);		
			
			/*********************************************
			 				* Create JSON data *
			 *********************************************/
			
			JSONObject jsonObject;
			JSONArray jsonArray = new JSONArray();		
			JSONObject object = new JSONObject();
			
			try {
				
				for (Product bean : productBeanList) {
					
					jsonObject = new JSONObject();
					
					jsonObject.put("productId",    bean.getProductId());
					jsonObject.put("category",     bean.getCategory());
					jsonObject.put("companyName",  bean.getCompanyName());			
					/*jsonObject.put("kf1",          bean.getKeyFeatures().getKf1());
					jsonObject.put("kf2",          bean.getKeyFeatures().getKf2());
					jsonObject.put("kf3",          bean.getKeyFeatures().getKf3());
					jsonObject.put("kf4",          bean.getKeyFeatures().getKf4());*/
					jsonObject.put("discount",     bean.getPrice().getDiscount());
					jsonObject.put("listPrice",    bean.getPrice().getListPrice());
					jsonObject.put("salePriceCustomer",    bean.getPrice().getSalePriceCustomer());
					jsonObject.put("productName",  bean.getProductName());
					jsonObject.put("sellerCode",   bean.getSellerId());
					jsonObject.put("subCategory",  bean.getSubCategory());
				
					jsonArray.put(jsonObject);
				}
				
				object.put("list", jsonArray);
				object.put("max", itemMAX);
				if (productBeanList.isEmpty())
					object.put("hideLoadMore", true);
				if (residueProduct == true)
					object.put("residueProduct", residueProduct);
				
				
				
			} catch (JSONException e) {		
				e.printStackTrace();
			}		
			
			System.out.println(jsonArray.toString());
			
			/*******************************************
			 				* Next Page *
			 *******************************************/
			response.setContentType("application/json");
			response.getWriter().write(object.toString());
	
		}	
		
		else if (servletPath.equals("/AddToCartOrWishlist")) {
			
				System.out.println("Entered AddToCartOrWishlist");
				
				/*************** Get Request **************/
				
					String size            = (String) request.getParameter("size");  
					//  for add to cart/wishlist
					String move             = (String) request.getParameter("move");  				
					String productId111     = (String) request.getParameter("productId");
					String cartOrWishlist   = (String) request.getParameter("cartOrWishlist"); 
					
				/*************** Get Session **************/
					
					User user = (User) session.getAttribute("user");
					
				/*************** Process ******************/
					
					Long productId = 0L;
					int qty = 0;
					if (productId111 != null) {
						productId = Long.parseLong(productId111);
						int stock = TransientData.getStock(productId);
						qty = (stock == 0) ? 0 : 1; 
					}
					
					//int size = Integer.parseInt(size1);  
					
				/*************** Database *****************/
				
					List<TwoObjects<Product, CartWishlist>> productBeanAndCW = null;//, productBeanAndQtyList1 = null;					
					
					if (move == null) {
						productBeanAndCW = buyerSearchDAO.addToCartOrWishList(productId, user.getUserInfo().getId(), cartOrWishlist, size);
						//productBeanAndQtyList1 = productBeanAndQtyList;
					}
					else  { //  for add to cart/wishlist
						productBeanAndCW = buyerSearchDAO.moveToCartOrWishList(productId, user.getUserInfo().getId(), cartOrWishlist,
								qty, size);
					}
					
				/*************** Thread Call For API Rate & Delivery ****************/
					
					List<TwoObjects<BigDecimal, String>> apiDataList = null;
					
					//if (productBeanAndQtyList1 != null) {
						//ApiDataMultiThreadBean getApiDataMultiThread  = new ApiDataMultiThreadBean(productBeanAndCW, user, request, response);
						//apiDataList = getApiDataMultiThread.getRateAndDeliveryList();
					//}
						
						MultiShippingDelivery multiShippingDelivery = new MultiShippingDelivery(productBeanAndCW);
						apiDataList = multiShippingDelivery.getRateAndDeliveryList();
					
				/********** Set Session *******************/
					//if (productBeanAndQtyList1 != null) {						
						session.setAttribute("apiDataList", apiDataList);
					//}
					
				/************** Set Request ***************/
					
					request.setAttribute("productBeanAndCW", productBeanAndCW);
					request.setAttribute("apiDataList", apiDataList);
					
				/************** Next Page *****************/
					
					if (move == null) {
					
							if (cartOrWishlist.equals("cart")) {  // cart page					
								request.getRequestDispatcher("jsp_Buyer/Cart.jsp").forward(request, response);
							} else {                              // wishlist page
								request.getRequestDispatcher("jsp_Buyer/Wishlist.jsp").forward(request, response);
							}	
					} else {   
							// toggle the pages for 'Add to cart/wishlist'
							if (cartOrWishlist.equals("cart")) {  // wishlist page					
								request.getRequestDispatcher("jsp_Buyer/Wishlist.jsp").forward(request, response);
							} else {                              // cart page
								request.getRequestDispatcher("jsp_Buyer/Cart.jsp").forward(request, response);
							}	
					}
			
		}
		
		else if (servletPath.equals("/DeleteFromCartWishlistTable")) {
			
			System.out.println("Entered DeleteFromCartWishlistTable");
			
				/*************** Get Request **************/
				
					String productId111     = (String) request.getParameter("productId");
					String cartOrWishlist   = (String) request.getParameter("cartOrWishlist"); 
				
				/*************** Get Session **************/
				
					User user = (User) session.getAttribute("user");
				
				/*************** Process ******************/
				
					Long productId = 0L;
					if (productId111 != null)
						productId = Long.parseLong(productId111);
					
				/*************** Database *****************/				
					
					boolean status = false;
					
					status = buyerSearchDAO.deleteCartOrWishListItem(productId, user.getUserInfo().getId(), cartOrWishlist);
					
					CartAttributesBean cartAttributesBean = CartAttributesBean.getInstance();
					int    totalQty = cartAttributesBean.getTotalQty(user.getUserInfo().getId());
					double totalSum = cartAttributesBean.getTotalAmount(user.getUserInfo().getId());
					
				/************** Set Request ***************/
					
					JSONObject jsonObject = new JSONObject();
					
					if (status == true) {
						
							try {
								jsonObject.put("status", true);
								jsonObject.put("totalQty", totalQty);
								jsonObject.put("totalSum", totalSum);
							} catch (JSONException e) {							
								e.printStackTrace();
							}
					} else {
						
							try {
								jsonObject.put("status", false);
							} catch (JSONException e) {							
								e.printStackTrace();
							}
					}
					
					response.setContentType("application/json");
					response.getWriter().write(jsonObject.toString());   System.out.println(jsonObject.toString());
			
		}
		
		else if (servletPath.equals("/InsertQtyToCart")) {
			
				System.out.println("Entered InsertQtyToCart");
			
				/*************** Get Request **************/
			
				String productId111   = (String) request.getParameter("productId");
				String qty111         = (String) request.getParameter("qty"); 
				int    itemNo         = Integer.parseInt(request.getParameter("itemNo"));
				long   cartWishlistID = Integer.parseInt(request.getParameter("cartWishlistID"));
		
				/*************** Get Session **************/
			
				User user = (User) session.getAttribute("user");				
				@SuppressWarnings("unchecked")
				List<TwoObjects<BigDecimal, String>> apiDataList = (List<TwoObjects<BigDecimal, String>>) session.getAttribute("apiDataList");
			
				/*************** Process ******************/
			
				Long productId = Long.parseLong(productId111);        
				int qty        = Integer.parseInt(qty111);
				int stock      = TransientData.getStock(productId);
				int tempQty    = qty;
				
				qty = (qty > stock) ? stock : qty;   //if (qty > stock)  qty = stock;	
				
				
				
				/*************** Database *****************/			
				
				int qty1 = buyerSearchDAO.insertQtyOfRow(user.getUserInfo().getId(), qty, productId, cartWishlistID);
				
				CartAttributesBean cartAttributesBean = CartAttributesBean.getInstance();
				int                totalQty           = cartAttributesBean.getTotalQty   (user.getUserInfo().getId());
				double             totalSum           = cartAttributesBean.getTotalAmount(user.getUserInfo().getId());
				
				/***************** API ********************/
				EstimatedRateAndDelivery estimatedRateAndDelivery = null;
				try {
					estimatedRateAndDelivery = EstimatedRateAndDeliveryBean.getNewInstance(productId, user, qty1);
				} catch (SOAPException e) {
					System.out.println("SOAPException" + e);
					request.getRequestDispatcher("ErrorPages/ConnectionError.jsp").forward(request, response);
				} catch (ParserConfigurationException e) {
					System.out.println("ParserConfigurationException");
					e.printStackTrace();
				} catch (SAXException e) {
					System.out.println("SAXException");
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("ParseException");
					e.printStackTrace();
				}
				
				BigDecimal rate = estimatedRateAndDelivery.getRate();    
				String delivery = estimatedRateAndDelivery.getDelivery();  System.out.println(rate + " " + delivery);
				
				//Insert into session that already existed
				int i = 0;
				for (TwoObjects<BigDecimal, String> twoObjects : apiDataList) {
					
					if (i == itemNo) {
						twoObjects.setObj1(rate);
						twoObjects.setObj2(delivery);
					}
				}
				
				/************* Set Session *************/
				session.setAttribute("apiDataList", apiDataList);
				
				/************** Set Request ***************/
				
				JSONObject jsonObject = new JSONObject();				
				
					
				try {
					if (tempQty > stock)
						jsonObject.put("stock", "In Stock: " + stock + " only");
					
					jsonObject.put("qty",      qty1);
					jsonObject.put("totalQty", totalQty);
					jsonObject.put("totalSum", totalSum);
					if (rate.doubleValue() != 0)
						jsonObject.put("rate",     rate);
					jsonObject.put("delivery", delivery);
				} catch (JSONException e) {							
					e.printStackTrace();
				}
				
				
				response.setContentType("application/json");
				response.getWriter().write(jsonObject.toString());   
			
		}  // InsertQtyToCart
		
		else if (servletPath.equals("/Account")) {
			
			System.out.println("Entered Account");
		}
		
		else if (servletPath.equals("/CallRegistrationPage")) {
			
			request.getRequestDispatcher("jsp_Buyer/Registration.jsp").forward(request, response);
		}
		
	
		//Soumya - Not Use The Servlet
		else if (servletPath.equals("/CheckUserId")) {
			
			System.out.println("Enter CheckUserId");
			
			PrintWriter out = response.getWriter();
			
			String User_Id = request.getParameter("user_id").trim();
			String msg = "";
			
			try {
				
				msg = UserDAO.CheckUserId(User_Id);
				System.out.println(msg+"YYYYYYYYYY");
				
				if (msg != null) {
					
					if (msg.equalsIgnoreCase("User_Id Is Already exists")) {
						
						out.print("User_Id Is Already exists");
					} else {
						
						out.print("User_Id Is Accepted");
					}
			} 
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		 }
		
		else if(servletPath.equals("/OrderHistroy")) {
			
			System.out.println("Enter OrderHistroy");
			
			/******* Get Session ******/
			User user = (User) session.getAttribute("user");
			
			/******* DataBase *******/				
			List<String> orderIds                             = buyerSearchDAO.getOrderIdForCustomer  (user);		
			List<CustomerOrderHistroy> customerOrderHistroys = buyerSearchDAO.getCustomerOrderHistroy(user);
			
			/******* Process *******/	
			//To arrange orders with same OrderTableId			
			Map<String, List<CustomerOrderHistroy>> map = helper.getCustomerOrderHistroyMap(orderIds, customerOrderHistroys);
			
			/********* Set Request *********/
			request.setAttribute("map", map);
			
			/****** NextPage ******/
			request.getRequestDispatcher("jsp_Buyer/Orderhistory.jsp").forward(request, response);

		}
		
		else if(servletPath.equals("/CancelOrderFromCustomer")) {
			
			System.out.println("Enter CancelOrderFromCustomer");
			
			/******* Get Request **********/			
			long orderTableId = Long.parseLong(request.getParameter("orderTableId"));  //System.out.println(orderTableId);
			
			/***** DataBase *********/			
			boolean status = buyerSearchDAO.setItemCancelOfCustomer(orderTableId);
			
			/***** Json value for calling page *********/
			
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("status", status);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
		} //CancelOrderFromCustomer
		
		else if(servletPath.equals("/GetDeliveryAddressCustomer")) {
			
			System.out.println("Enter GetDeliveryAddressCustomer");
			
			/************* Get Request ***************/
			long userId = Long.parseLong(request.getParameter("userId"));  
			
			/************* Get Session ***************/
			User user = (User) session.getAttribute("user");
			
			/*********** Database ************/			
			DeliveryAddress deliveryAddress = buyerSearchDAO.getDeliveryAddressCustomer(user, userId);
			
			/********* Json Data for Next Page **********/
			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.put("address",    deliveryAddress.getAddress() );
				jsonObject.put("address1",   deliveryAddress.getAddress1());
				jsonObject.put("city",       deliveryAddress.getCity()    );
				jsonObject.put("company",    deliveryAddress.getCompany() );
				jsonObject.put("contact",    deliveryAddress.getContact() );
				jsonObject.put("country",    deliveryAddress.getCountry() );
				jsonObject.put("email",      deliveryAddress.getEmail()   );
				jsonObject.put("firstName", deliveryAddress.getfName()   );
				jsonObject.put("id",         deliveryAddress.getId()      );
				jsonObject.put("lastName",  deliveryAddress.getlName()   );
				jsonObject.put("pin",        deliveryAddress.getPin()     );
				jsonObject.put("state",      deliveryAddress.getState()   );
				jsonObject.put("userId",     deliveryAddress.getUserId()  );
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
			
		} // GetDeliveryAddressCustomer
		
		else if(servletPath.equals("/CheckPincode")) {
			
			System.out.println("Enter CheckPincode");
			
			/************* Get Request ***************/
			String pincode = request.getParameter("pincode"); 
			
			
			JSONObject jsonObject = new JSONObject();
			boolean status = false, notANumber = true, noNet = false, error = false;
			
			try {
			
					long pin = Long.parseLong(pincode);					
					
					
					/*********** API ************/				
					SearchLocationByPostalInterface searchLocationByPostalInterface;
					
					try {
						
						searchLocationByPostalInterface = SearchLocationByPostal.getNewInstance(pin);
						status = searchLocationByPostalInterface.isLocationAvailable();
						
					} catch (SOAPException e) {						
						e.printStackTrace();
						System.out.println(e);
						noNet = true;
					} catch (ParserConfigurationException e1) {
						error = true;
						e1.printStackTrace();
					} catch (SAXException e1) {
						error = true;
						e1.printStackTrace();
					} catch (ParseException e1) {
						error = true;
						e1.printStackTrace();
					} catch (XMLStreamException e) {
						error = true;
						e.printStackTrace();
					} catch (FactoryConfigurationError e) {
						error = true;
						e.printStackTrace();
					}
			
			
			} catch (NumberFormatException e) {
				System.out.println("Not A Number");
				notANumber = false;
			}
			
			
			/********* Json Data for Next Page **********/
			
			
			try {
				jsonObject.put("status", status);
				jsonObject.put("noNet", noNet);
				jsonObject.put("error", error);
				
				if (!notANumber)
					jsonObject.put("notANumber", false);
				else
					jsonObject.put("notANumber", true);
				
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString());
			
			
		} // CheckPincode
		
	}
}
