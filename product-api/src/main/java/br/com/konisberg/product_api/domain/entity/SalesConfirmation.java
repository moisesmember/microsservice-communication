package br.com.konisberg.product_api.domain.entity;

import br.com.konisberg.product_api.domain.entity.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesConfirmation {
    private String salesId;
    private SalesStatus status;
}
