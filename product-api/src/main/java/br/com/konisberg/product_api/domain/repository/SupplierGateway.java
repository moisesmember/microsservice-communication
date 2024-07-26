package br.com.konisberg.product_api.domain.repository;

import br.com.konisberg.product_api.application.form.SupplierForm;
import br.com.konisberg.product_api.domain.entity.Supplier;
import br.com.konisberg.product_api.infra.config.exception.SuccessResponse;

public interface SupplierGateway extends GenericRepository<Supplier, SupplierForm, SuccessResponse>{
}
