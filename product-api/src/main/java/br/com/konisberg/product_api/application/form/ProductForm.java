package br.com.konisberg.product_api.application.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductForm {
    private String name;
    private Integer categoryId;
    private Integer supplierId;
    private Integer quantityAvailable;
}
