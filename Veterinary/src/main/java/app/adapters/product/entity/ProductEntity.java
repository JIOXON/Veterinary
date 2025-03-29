package app.adapters.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import app.domain.models.*;
@Entity
@Table (name = "product")
public class ProductEntity {
	public  ProductEntity(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.price = product.getPrice();
	}
	@Id
    @Column(name="product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="price")
    private double price;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public ProductEntity() {
	}
}