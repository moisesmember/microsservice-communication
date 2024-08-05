package br.com.konisberg.product_api.application.form;

import java.util.List;

public record ProductCheckStockForm(
        List<ProductQuantityForm> products
) {
}
