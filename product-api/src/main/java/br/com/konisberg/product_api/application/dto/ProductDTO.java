package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.domain.entity.Product;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record ProductDTO(
        Integer id,
        String name,
        CategoryDTO category,
        SupplierDTO supplier,
        Integer quantityAvailable,
        Date creationDate
) {
    public static ProductDTO from(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                CategoryDTO.from(product.getCategory()),
                SupplierDTO.from(product.getSupplier()),
                product.getQuantityAvailable(),
                product.getCreationDate()
        );
    }

    public static List<ProductDTO> fromList(List<Product> list) {
        return list.stream().map(ProductDTO::from).collect(Collectors.toList());
    }
}
