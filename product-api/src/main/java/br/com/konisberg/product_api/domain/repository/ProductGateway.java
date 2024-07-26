package br.com.konisberg.product_api.domain.repository;

import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.domain.entity.Product;

import java.util.List;

public interface ProductGateway extends GenericRepository<Product, ProductForm>{

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByCategoryId(Integer id);

    List<Product> findBySupplierId(Integer id);

    Boolean existsByCategoryId(Integer id);

    Boolean existsBySupplierId(Integer id);

}
