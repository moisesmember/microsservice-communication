package br.com.konisberg.product_api.domain.repository;

import br.com.konisberg.product_api.application.form.CategoryForm;
import br.com.konisberg.product_api.domain.entity.Category;

public interface CategoryGateway extends GenericRepository<Category, CategoryForm>{
}
