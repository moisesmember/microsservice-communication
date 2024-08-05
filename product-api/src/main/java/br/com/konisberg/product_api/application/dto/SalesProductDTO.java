package br.com.konisberg.product_api.application.dto;

import java.util.List;

public record SalesProductDTO(
        List<String> salesIds
) {
}
