package br.com.konisberg.product_api.application.form;

import br.com.konisberg.product_api.domain.entity.enums.SalesStatus;

public record SalesConfirmationForm(
        String salesId,
        SalesStatus status,
        String transactionId
) {
}
