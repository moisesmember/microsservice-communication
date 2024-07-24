package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.domain.entity.Supplier;

import java.util.List;
import java.util.stream.Collectors;

public record SupplierDTO(
        Integer id,
        String name
) {
    public static SupplierDTO from(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId(), supplier.getName()
        );
    }

    public static List<SupplierDTO> fromList(List<Supplier> list) {
        return list.stream().map(SupplierDTO::from).collect(Collectors.toList());
    }
}
