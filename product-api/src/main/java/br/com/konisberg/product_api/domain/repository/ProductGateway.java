package br.com.konisberg.product_api.domain.repository;

import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.domain.entity.Product;

public interface ProductGateway extends GenericRepository<Product, ProductForm>{
}
