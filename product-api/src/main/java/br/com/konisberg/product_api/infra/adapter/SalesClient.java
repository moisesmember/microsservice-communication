package br.com.konisberg.product_api.infra.adapter;

import br.com.konisberg.product_api.domain.entity.SalesProduct;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Optional;

@HttpExchange("/api/orders")
public interface SalesClient {

    @GetExchange("/product/{productId}")
    Optional<SalesProduct> findSalesByProductId(@PathVariable Integer productId,
                                                @RequestHeader(name = "Authorization") String authorization,
                                                @RequestHeader(name = "transactionId") String transactionId);
}
