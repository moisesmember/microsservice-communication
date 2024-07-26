package br.com.konisberg.product_api.infra.util;

public class RouteTag {
    private RouteTag() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PRODUCT = "Produto";

    public static final String CATEGORY = "Categoria";

    public static final String SUPPLIER = "Fornecedor";
}
