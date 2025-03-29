package app.adapters.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByProductId(long productId);

}
