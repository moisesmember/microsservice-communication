package br.com.konisberg.product_api.infra.messaging.listener;

import br.com.konisberg.product_api.application.form.ProductStockForm;
import br.com.konisberg.product_api.application.usecase.ProductUseCase;
import br.com.konisberg.product_api.infra.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductStockListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void receiveProductStockMessage(ProductStockForm productStockForm) throws JsonProcessingException {
        try {
            log.info("Recebendo mensagem: {}", new ObjectMapper().writeValueAsString(productStockForm));
            ProductUseCase productUseCase = new ProductUseCase(productService);
            productUseCase.updateProductStock(productStockForm);
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar a mensagem JSON: {}", e.getMessage());
            // Dependendo da lógica, você pode querer propagar ou tratar a exceção aqui
        } catch (Exception e) {
            log.error("Erro inesperado: {}", e.getMessage());
            // Tratar outras exceções potenciais
        }
    }
}
