package br.com.konisberg.product_api.application.interator;

import br.com.konisberg.product_api.application.dto.CategoryDTO;
import br.com.konisberg.product_api.application.form.CategoryForm;

import java.util.List;

public interface CategoryInterator {
    CategoryDTO create(CategoryForm categoryForm);
    CategoryDTO update(Integer id, CategoryForm categoryForm);
    CategoryDTO searchById(Integer id);
    List<CategoryDTO> searchAll();
    CategoryDTO delete(Integer id);
}
