package app.ports;

import app.domain.models.Product;

public interface ProductPort {
	public void sellProduct(Product product);
    public Product getProductById(Long productId);
}
