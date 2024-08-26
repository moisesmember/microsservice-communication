package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.domain.entity.ProductSalesResponse;
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
    public static ProductSalesResponseDTO from(ProductSalesResponse productSale) {
        return new ProductSalesResponseDTO(
                productSale.getId(),
                productSale.getName(),
                productSale.getQuantityAvailable(),
                DateConversion.convertDateToLocalDateTime(productSale.getCreationDate()),
                SupplierDTO.from(productSale.getSupplier()),
                CategoryDTO.from(productSale.getCategory()),
                productSale.getSales()
        );
    }
}
