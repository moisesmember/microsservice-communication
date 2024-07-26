package br.com.konisberg.product_api.domain.repository;

import br.com.konisberg.product_api.application.form.CategoryForm;
import br.com.konisberg.product_api.domain.entity.Category;
import br.com.konisberg.product_api.infra.config.exception.SuccessResponse;

public interface CategoryGateway extends GenericRepository<Category, CategoryForm, SuccessResponse>{
}
