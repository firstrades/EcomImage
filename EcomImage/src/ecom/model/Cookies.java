package ecom.model;

import java.io.Serializable;
import java.util.List;

public class Cookies implements Serializable {
	private static final long serialVersionUID = 3193792614094278648L;
	
	
	private List<Long> productIds;
	private List<String> cartWishlist;
	private int qty;
	private String size;
	
	
	public List<Long> getProductIds() {
		return productIds;
	}
	public List<String> getCartWishlist() {
		return cartWishlist;
	}
	public int getQty() {
		return qty;
	}
	public String getSize() {
		return size;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
	public void setCartWishlist(List<String> cartWishlist) {
		this.cartWishlist = cartWishlist;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
