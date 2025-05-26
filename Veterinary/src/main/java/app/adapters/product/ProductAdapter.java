package app.adapters.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.models.Product;
import app.adapters.product.entity.ProductEntity;
import app.ports.*;
import app.adapters.product.repository.ProductRepository;

@Service
public class ProductAdapter implements ProductPort{
	
	@Autowired
    private ProductRepository productRepository;
	
	
	@Override
	public void sellProduct(Product product) {
		ProductEntity productEntity = new ProductEntity(product);
        productRepository.save(productEntity);
        product.setProductId(productEntity.getProductId());
	}

	
	
	@Override
	public Product getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElse(null);
        if (productEntity != null) {
            return ProductEntity(productEntity);
        }
        return null;
    }
	private Product ProductEntity(ProductEntity productEntity) {
        Product product = new Product();
        product.setProductId(productEntity.getProductId());
        product.setProductName(productEntity.getProductName());
        product.setPrice(productEntity.getPrice());
        return product;
    }

}