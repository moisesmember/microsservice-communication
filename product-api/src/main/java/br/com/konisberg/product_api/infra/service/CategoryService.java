package br.com.konisberg.product_api.infra.service;

import br.com.konisberg.product_api.application.form.CategoryForm;
import br.com.konisberg.product_api.domain.entity.Category;
import br.com.konisberg.product_api.domain.repository.CategoryGateway;
import br.com.konisberg.product_api.infra.model.CategoryModel;
import br.com.konisberg.product_api.infra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryGateway {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return Category.ofList(categoryRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return Category.of(categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found")));
    }

    @Override
    public Category create(CategoryForm param) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setDescription(param.getDescription());
        return Category.of(categoryRepository.save(categoryModel));
    }

    @Override
    public Category update(Integer id, CategoryForm param) {
        CategoryModel categoryFound = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        categoryFound.setDescription(param.getDescription());
        return Category.of(categoryFound);
    }

    @Override
    public Category delete(Integer id) {
        CategoryModel categoryFound = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        categoryRepository.delete(categoryFound);
        return Category.of(categoryFound);
    }

    @Override
    public long count(String search) {
        return categoryRepository.count();
    }
}
