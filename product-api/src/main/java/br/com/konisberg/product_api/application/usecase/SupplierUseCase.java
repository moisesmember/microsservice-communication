package br.com.konisberg.product_api.application.usecase;

import br.com.konisberg.product_api.application.dto.SupplierDTO;
import br.com.konisberg.product_api.application.form.SupplierForm;
import br.com.konisberg.product_api.application.interator.SupplierInterator;
import br.com.konisberg.product_api.domain.repository.SupplierGateway;
import br.com.konisberg.product_api.infra.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class SupplierUseCase implements SupplierInterator {

    private final SupplierGateway supplierGateway;

    @SneakyThrows
    @Transactional
    @Override
    public SupplierDTO create(SupplierForm supplierForm) {
        ValidationUtils.validateNotEmpty(supplierForm.getName(), "supplier's name");
        return SupplierDTO.from(supplierGateway.create(supplierForm));
    }

    @SneakyThrows
    @Transactional
    @Override
    public SupplierDTO update(Integer id, SupplierForm supplierForm) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "supplier's id");
        ValidationUtils.validateNotEmpty(supplierForm.getName(), "supplier's name");
        return SupplierDTO.from(supplierGateway.update(id, supplierForm));
    }

    @Override
    public SupplierDTO searchById(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "supplier's id");
        return SupplierDTO.from(supplierGateway.findById(id));
    }

    @Override
    public List<SupplierDTO> searchAll() {
        return SupplierDTO.fromList(supplierGateway.findAll());
    }

    @SneakyThrows
    @Transactional
    @Override
    public SupplierDTO delete(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "supplier's id");
        return SupplierDTO.from(supplierGateway.delete(id));
    }
}
