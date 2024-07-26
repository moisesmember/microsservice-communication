package br.com.konisberg.product_api.application.interator;

import br.com.konisberg.product_api.application.dto.SuccessResponseDTO;
import br.com.konisberg.product_api.application.dto.SupplierDTO;
import br.com.konisberg.product_api.application.form.SupplierForm;

import java.util.List;

public interface SupplierInterator {
    SupplierDTO create(SupplierForm supplierForm);
    SupplierDTO update(Integer id, SupplierForm supplierForm);
    SupplierDTO searchById(Integer id);
    List<SupplierDTO> searchAll();
    SuccessResponseDTO delete(Integer id);
}
