package br.com.konisberg.product_api.application.dto;

public record ProductDTO(
        Integer id,
        String name,
        CategoryDTO category,
        SupplierDTO supplier,
        Integer quantityAvailable
) {
}
