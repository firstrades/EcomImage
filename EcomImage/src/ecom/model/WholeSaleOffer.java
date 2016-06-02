package ecom.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ecom.common.ConnectionFactory;

public class WholeSaleOffer implements Serializable {
	private static final long serialVersionUID = -8401736384071746587L;
	
	
	private long productId;
	private int qty;
	private double discount;
	
	
	public long getProductId() {
		return productId;
	}
	public int getQty() {
		return qty;
	}
	public double getDiscount() {
		return discount;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	//----------------------------------------------------------------------DAO
	
	public static WholeSaleOffer getWholeSaleOffer(long productId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		ResultSet resultSet = null;	
		WholeSaleOffer offer = null;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "SELECT * FROM whole_sale_offer WHERE product_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);				
			preparedStatement.setLong(1, productId);   
			 			
			resultSet = preparedStatement.executeQuery();	
			 			
			if (resultSet.next()) {
				offer = new WholeSaleOffer();
				offer.setProductId(resultSet.getLong  ("product_id"));
				offer.setQty      (resultSet.getInt   ("qty"       ));
				offer.setDiscount (resultSet.getDouble("discount"  ));
			}
			
			connection.commit();
			
			System.out.println("SQL getWholeSaleOffer Executed");
			
			return offer;			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {				
			offer = null;
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}			
		
		return null;
	}//getWholeSaleOffer
	
	
	public static void main(String...args) {
		
		WholeSaleOffer saleOffer = getWholeSaleOffer(2L);
		System.out.println(saleOffer);
	}
}
