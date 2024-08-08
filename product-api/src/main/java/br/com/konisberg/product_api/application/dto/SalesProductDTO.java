package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.domain.entity.SalesProduct;

import java.util.List;
import java.util.stream.Collectors;

public record SalesProductDTO(
        List<String> salesIds
) {
    public static SalesProductDTO from(SalesProduct salesProduct) {
        return new SalesProductDTO(
                salesProduct.getSalesIds()
        );
    }

    public static List<SalesProductDTO> fromList(List<SalesProduct> list) {
        return list.stream().map(SalesProductDTO::from).collect(Collectors.toList());
    }
}
