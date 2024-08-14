package br.com.konisberg.product_api.application.form;

import java.util.List;

public record ProductStockForm(
        String salesId,
        List<ProductQuantityForm> products,
        String transactionId
) {
}
