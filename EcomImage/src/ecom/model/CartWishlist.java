package ecom.model;

import java.io.Serializable;

public class CartWishlist implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private long   id;
	private long   productId;
	private long   userId;
	private String cartOrWishlist;
	private int    qty;
	private String size;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCartOrWishlist() {
		return cartOrWishlist;
	}
	public void setCartOrWishlist(String cartOrWishlist) {
		this.cartOrWishlist = cartOrWishlist;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
