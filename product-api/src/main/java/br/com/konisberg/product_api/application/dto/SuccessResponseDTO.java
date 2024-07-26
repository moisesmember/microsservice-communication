package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.infra.config.exception.SuccessResponse;

public record SuccessResponseDTO(
        Integer status, String message
) {
    public static SuccessResponseDTO from (SuccessResponse successResponse) {
        return new SuccessResponseDTO(
                successResponse.getStatus(),
                successResponse.getMessage()
        );
    }
}
