package br.com.konisberg.product_api.application.form;

public record ProductQuantityForm(
        Integer productId,
        Integer quantity
) {}
