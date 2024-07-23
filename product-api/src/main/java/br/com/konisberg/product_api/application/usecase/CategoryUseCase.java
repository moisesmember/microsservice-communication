package br.com.konisberg.product_api.application.usecase;

import br.com.konisberg.product_api.application.dto.CategoryDTO;
import br.com.konisberg.product_api.application.form.CategoryForm;
import br.com.konisberg.product_api.application.interator.CategoryInterator;
import br.com.konisberg.product_api.domain.entity.Category;
import br.com.konisberg.product_api.domain.repository.CategoryGateway;
import br.com.konisberg.product_api.domain.service.Mapper;
import br.com.konisberg.product_api.infra.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class CategoryUseCase implements CategoryInterator {

    private final CategoryGateway categoryGateway;
    private final Mapper<CategoryDTO, CategoryForm, Category> mapper;

    @SneakyThrows
    @Transactional
    @Override
    public CategoryDTO create(CategoryForm categoryForm) {
        ValidationUtils.validateNotEmpty(categoryForm.getDescription(), "category's description");
        return mapper.domainToDto(categoryGateway.create(categoryForm));
    }

    @SneakyThrows
    @Transactional
    @Override
    public CategoryDTO update(Integer id, CategoryForm categoryForm) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "category's id");
        ValidationUtils.validateNotEmpty(categoryForm.getDescription(), "category's description");
        return mapper.domainToDto(categoryGateway.update(id, categoryForm));
    }

    @Override
    public CategoryDTO searchById(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "category's id");
        return mapper.domainToDto(categoryGateway.findById(id));
    }

    @Override
    public List<CategoryDTO> searchAll() {
        return mapper.domainListToDtoList(categoryGateway.findAll());
    }

    @SneakyThrows
    @Transactional
    @Override
    public CategoryDTO delete(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "category's id");
        return mapper.domainToDto(categoryGateway.delete(id));
    }
}
