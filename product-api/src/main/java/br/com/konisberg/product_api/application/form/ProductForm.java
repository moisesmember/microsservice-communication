package br.com.konisberg.product_api.application.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductForm {
    private String name;

    @JsonProperty("category_id")
    private Integer categoryId;

    @JsonProperty("supplier_id")
    private Integer supplierId;

    @JsonProperty("quantity_available")
    private Integer quantityAvailable;
}
