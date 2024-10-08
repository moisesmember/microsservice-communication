package br.com.konisberg.product_api.application.interator;

import br.com.konisberg.product_api.application.dto.ProductDTO;
import br.com.konisberg.product_api.application.dto.SuccessResponseDTO;
import br.com.konisberg.product_api.application.form.ProductForm;

import java.util.List;

public interface ProductInterator {
    ProductDTO create(ProductForm productForm);
    ProductDTO update(Integer id, ProductForm productForm);
    ProductDTO searchById(Integer id);
    List<ProductDTO> searchAll();
    SuccessResponseDTO delete(Integer id);
    List<ProductDTO> findByName(String name);
    List<ProductDTO> findByCategoryId(Integer categoryId);
    List<ProductDTO> findBySupplierId(Integer supplierId);
}
