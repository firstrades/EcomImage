package ecom.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ecom.common.ConnectionFactory;

public class SubCategory implements Serializable {
	private static final long serialVersionUID = -9214565403164016780L;

	private int id;
	private String subCategory;
	
	public int getId() {
		return id;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	//------------------------------------------------------------------------------------------getSubCategories
	
	public List<SubCategory> getSubCategorys(int categoryId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;	
		List<SubCategory> subCategories = new ArrayList<SubCategory>();
		SubCategory subCategory = null;
				
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "select subCategory_id, subCategory from category_subcategory_mapping where categoryList_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			 			
			resultSet = preparedStatement.executeQuery();	
			
			while (resultSet.next()) {	
				
				subCategory = new SubCategory();
				
				subCategory.setId(resultSet.getInt("subCategory_id"));
				subCategory.setSubCategory(resultSet.getString("subCategory"));
				
				subCategories.add(subCategory);
			}
			
			connection.commit();
			
			System.out.println("SQL getCategoryList Executed");			
						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();				
		} finally {		
			try { resultSet.close();         } catch (SQLException e)  { e.printStackTrace();  }
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();			
		}	
		
		return subCategories;
	}
	
	
	public int deleteASubCategory(int subCategoryId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;	
		int status = 0;
		
		try {
			connection = ConnectionFactory.getNewConnection();
			connection.setAutoCommit(false);
			
			sql = "delete from category_subcategory_mapping where subCategory_id = ?";
				
			preparedStatement = connection.prepareStatement(sql);			 
			preparedStatement.setInt(1, subCategoryId);
			 			
			status = preparedStatement.executeUpdate();		// 0 if not executed(may be row was not there), 1 if executed
			
			connection.commit();
			
			System.out.println("SQL deleteASubCategory Executed");			
						
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {			
			try { connection.rollback();     } catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace();
			//return status;
		} finally {				
			try { preparedStatement.close(); } catch (SQLException e)  { e.printStackTrace();  }
			try { connection.close();        } catch (SQLException e)  { e.printStackTrace();  }
			System.gc();			
		}		
		
		return status;
	}//deleteASubCategory
	
	public static void main(String...args) {
		
		SubCategory subCategory = new SubCategory();
		List<SubCategory> subCategories = subCategory.getSubCategorys(1);
		
		for (SubCategory subCategory2 : subCategories) {
			
			System.out.println(subCategory2.getSubCategory());
		}
	}
}
