package br.com.konisberg.product_api.infra.util;

public class PathRest {

    private PathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String API = "/api";
    public static final String VERSION = "/v1";

    public static final String AUTHENTICATION = "/authentication";
    public static final String LOGIN = "/login";

    public static final String PRODUCT = "/product";

    public static final String CATEGORY = "/category";

    public static final String SUPPLIER = "/supplier";

    public static final String ORDER = "/orders";

}
