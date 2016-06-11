package ecom.model;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import ecom.common.ConnectionFactory;

public class CategoryList implements Serializable {
	private static final long serialVersionUID = -8097720731671155713L;

	private int id;
	private String category;
	private List<String> subCategory;
	
	
	public int getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	public List<String> getSubCategory() {
		return subCategory;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSubCategory(List<String> subCategory) {
		this.subCategory = subCategory;
	}
	
	
	//------------------------------------------------------------------------------DOW
	
	public synchronized static int addCategory(String category) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		String sql = null;
		int tableId = -1;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "{call addCategory(?,?)}";
				
			callableStatement = connection.prepareCall(sql);				
			callableStatement.registerOutParameter(1, Types.INTEGER); 
			callableStatement.setString(2, category);
			 			
			callableStatement.execute();	
			 			
			tableId = callableStatement.getInt(1);
			
			connection.commit();
			
			System.out.println("SQL addCategory Executed");			
						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			
		} finally {				
			try { callableStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();
		}	
		
		return tableId;
	}//addCategory
	
	
	public boolean deleteCategory(int tableId) {
		
		
		
		
		return false;
	}
	
	
	public static void main(String...args) {
		
		int tableId = addCategory("jewel3");
		
		System.out.println(tableId);
	}
	
}
