package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.infra.util.DateConversion;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ProductSalesResponseDTO(
        Integer id,
        String name,
        @JsonProperty("quantity_available") Integer quantityAvailable,
        @JsonProperty("created_at")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime createdAt,
        SupplierDTO supplier,
        CategoryDTO category,
        List<String> sales
) {
    public static ProductSalesResponseDTO from(Product product, List<String> sales) {
        return new ProductSalesResponseDTO(
                product.getId(),
                product.getName(),
                product.getQuantityAvailable(),
                DateConversion.convertDateToLocalDateTime(product.getCreationDate()),
                SupplierDTO.from(product.getSupplier()),
                CategoryDTO.from(product.getCategory()),
                sales
        );
    }
}
