package br.com.konisberg.product_api.domain.entity;

import br.com.konisberg.product_api.infra.model.SupplierModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Supplier {
    private Integer id;
    private String name;

    public static Supplier of (SupplierModel supplier) {
        var response = new Supplier();
        BeanUtils.copyProperties(supplier, response);
        return response;
    }

    public static List<Supplier> ofList(List<SupplierModel> list) {
        return list.stream().map(Supplier::of).collect(Collectors.toList());
    }
}
