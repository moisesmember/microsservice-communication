package br.com.konisberg.product_api.domain.entity;

import br.com.konisberg.product_api.infra.model.CategoryModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Category {
    private Integer id;
    private String description;

    public static Category of (CategoryModel category) {
        var response = new Category();
        BeanUtils.copyProperties(category, response);
        return response;
    }

    public static List<Category> ofList(List<CategoryModel> list) {
        return list.stream().map(Category::of).collect(Collectors.toList());
    }
}
