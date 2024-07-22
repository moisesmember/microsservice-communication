package br.com.konisberg.product_api.infra.repository;

import br.com.konisberg.product_api.infra.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
}
