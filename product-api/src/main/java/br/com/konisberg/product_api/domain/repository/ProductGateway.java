package br.com.konisberg.product_api.domain.repository;

import br.com.konisberg.product_api.application.form.ProductCheckStockForm;
import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.application.form.ProductStockForm;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.infra.config.exception.SuccessResponse;

import java.util.List;

public interface ProductGateway extends GenericRepository<Product, ProductForm, SuccessResponse>{

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByCategoryId(Integer id);

    List<Product> findBySupplierId(Integer id);

    Boolean existsByCategoryId(Integer id);

    Boolean existsBySupplierId(Integer id);

    void updateProductStock(ProductStockForm productStockForm);

    SuccessResponse checkProductsStock(ProductCheckStockForm request);
}
