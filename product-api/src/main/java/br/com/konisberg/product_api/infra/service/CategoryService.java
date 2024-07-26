package br.com.konisberg.product_api.infra.service;

import br.com.konisberg.product_api.application.form.CategoryForm;
import br.com.konisberg.product_api.domain.entity.Category;
import br.com.konisberg.product_api.domain.repository.CategoryGateway;
import br.com.konisberg.product_api.infra.config.exception.SuccessResponse;
import br.com.konisberg.product_api.infra.config.exception.ValidationException;
import br.com.konisberg.product_api.infra.model.CategoryModel;
import br.com.konisberg.product_api.infra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService implements CategoryGateway {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Category> findAll() {
        return Category.ofList(categoryRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return Category.of(categoryRepository.findById(id).orElseThrow(() -> new ValidationException("There's no Category for the given ID.")));
    }

    @Override
    public Category create(CategoryForm param) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setDescription(param.getDescription());
        categoryModel.setCreationDate(new Date());
        return Category.of(categoryRepository.save(categoryModel));
    }

    @Override
    public Category update(Integer id, CategoryForm param) {
        CategoryModel categoryFound = categoryRepository.findById(id).orElseThrow(() -> new ValidationException("There's no Category for the given ID."));
        categoryFound.setDescription(param.getDescription());
        categoryFound.setLastModifiedDate(new Date());
        return Category.of(categoryRepository.save(categoryFound));
    }

    @Override
    public SuccessResponse delete(Integer id) {
        if(Boolean.TRUE.equals(productService.existsByCategoryId(id))) {
            throw new ValidationException("You cannot delete this category because it's already defined by a product.");
        }
        categoryRepository.deleteById(id);
        return SuccessResponse.create("The Category was deleted.");
    }

    @Override
    public long count(String search) {
        return categoryRepository.count();
    }

    @Override
    public boolean existsById(Integer id) {
        return categoryRepository.existsById(id);
    }
}
