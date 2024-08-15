package br.com.konisberg.product_api.infra.adapters;

import br.com.konisberg.product_api.domain.entity.SalesProduct;
import br.com.konisberg.product_api.infra.util.PathRest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Optional;

@HttpExchange(PathRest.API + PathRest.ORDER)
public interface SalesClient {

    @GetExchange("/product/{productId}")
    Optional<SalesProduct> findSalesByProductId(@PathVariable Integer productId,
                                                @RequestHeader(name = "Authorization") String authorization,
                                                @RequestHeader(name = "transactionId") String transactionId);
}
