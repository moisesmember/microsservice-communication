package br.com.konisberg.product_api.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class SalesProduct {
    private List<String> salesIds;
}
