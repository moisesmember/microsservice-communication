package br.com.konisberg.product_api.application.usecase;

import br.com.konisberg.product_api.application.dto.CategoryDTO;
import br.com.konisberg.product_api.application.form.CategoryForm;
import br.com.konisberg.product_api.application.interator.CategoryInterator;
import br.com.konisberg.product_api.domain.repository.CategoryGateway;
import br.com.konisberg.product_api.infra.util.ValidationUtils;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;

@Named
public class CategoryUseCase implements CategoryInterator {

    private final CategoryGateway categoryGateway;

    public CategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @SneakyThrows
    @Transactional
    @Override
    public CategoryDTO create(CategoryForm categoryForm) {
        ValidationUtils.validateNotEmpty(categoryForm.getDescription(), "category's description");
        return CategoryDTO.from(categoryGateway.create(categoryForm));
    }

    @SneakyThrows
    @Transactional
    @Override
    public CategoryDTO update(Integer id, CategoryForm categoryForm) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "category's id");
        ValidationUtils.validateNotEmpty(categoryForm.getDescription(), "category's description");
        return CategoryDTO.from(categoryGateway.update(id, categoryForm));
    }

    @Override
    public CategoryDTO searchById(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "category's id");
        return CategoryDTO.from(categoryGateway.findById(id));
    }

    @Override
    public List<CategoryDTO> searchAll() {
        return CategoryDTO.fromList(categoryGateway.findAll());
    }

    @SneakyThrows
    @Transactional
    @Override
    public CategoryDTO delete(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "category's id");
        return CategoryDTO.from(categoryGateway.delete(id));
    }
}
