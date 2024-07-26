package br.com.konisberg.product_api.infra.service;

import br.com.konisberg.product_api.application.form.SupplierForm;
import br.com.konisberg.product_api.domain.entity.Supplier;
import br.com.konisberg.product_api.domain.repository.SupplierGateway;
import br.com.konisberg.product_api.infra.config.exception.ValidationException;
import br.com.konisberg.product_api.infra.model.SupplierModel;
import br.com.konisberg.product_api.infra.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplierService implements SupplierGateway {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return Supplier.ofList(supplierRepository.findAll());
    }

    @Override
    public Supplier findById(Integer id) {
        return Supplier.of(supplierRepository.findById(id).orElseThrow(() -> new ValidationException("There's no Supplier for the given ID.")));
    }

    @Override
    public Supplier create(SupplierForm param) {
        SupplierModel supplierModel = new SupplierModel();
        supplierModel.setName(param.getName());
        supplierModel.setCreationDate(new Date());
        return Supplier.of(supplierRepository.save(supplierModel));
    }

    @Override
    public Supplier update(Integer id, SupplierForm param) {
        SupplierModel supplierFound = supplierRepository.findById(id).orElseThrow(() -> new ValidationException("There's no Supplier for the given ID."));
        supplierFound.setName(param.getName());
        supplierFound.setDeletedAt(new Date());
        return Supplier.of(supplierRepository.save(supplierFound));
    }

    @Override
    public Supplier delete(Integer id) {
        SupplierModel supplierFound = supplierRepository.findById(id).orElseThrow(() -> new ValidationException("There's no Supplier for the given ID."));
        supplierRepository.delete(supplierFound);
        return Supplier.of(supplierFound);
    }

    @Override
    public long count(String search) {
        return supplierRepository.count();
    }

    @Override
    public boolean existsById(Integer id) {
        return supplierRepository.existsById(id);
    }
}
