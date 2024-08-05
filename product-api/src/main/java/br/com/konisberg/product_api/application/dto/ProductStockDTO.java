package br.com.konisberg.product_api.application.dto;

import java.util.List;

public record ProductStockDTO(
        String salesId,
        List<ProductQuantityDTO> products,
        String transactionid
) {
}
