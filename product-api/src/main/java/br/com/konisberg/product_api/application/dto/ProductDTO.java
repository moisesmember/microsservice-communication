package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.infra.util.DateConversion;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ProductDTO(
        Integer id,
        String name,
        CategoryDTO category,
        SupplierDTO supplier,
        @JsonProperty("quantity_available")
        Integer quantityAvailable,
        @JsonProperty("created_at")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime creationDate
) {
    public static ProductDTO from(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                CategoryDTO.from(product.getCategory()),
                SupplierDTO.from(product.getSupplier()),
                product.getQuantityAvailable(),
                DateConversion.convertDateToLocalDateTime(product.getCreationDate())
        );
    }

    public static List<ProductDTO> fromList(List<Product> list) {
        return list.stream().map(ProductDTO::from).collect(Collectors.toList());
    }
}
