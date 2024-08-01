package br.com.konisberg.product_api.infra.config.interceptor;

import br.com.konisberg.product_api.infra.util.PathRest;

import java.util.List;

public class Urls {
    public static final List<String> PROTECTED_URLS = List.of(
            PathRest.API + PathRest.VERSION + PathRest.CATEGORY,
            PathRest.API + PathRest.VERSION + PathRest.SUPPLIER,
            PathRest.API + PathRest.VERSION + PathRest.PRODUCT
    );
}
