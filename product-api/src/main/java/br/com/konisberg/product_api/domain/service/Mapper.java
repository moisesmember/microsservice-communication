package br.com.konisberg.product_api.domain.service;

import java.util.List;

public interface Mapper<T, F, D> {
    T domainToDto(D domain);

    List<T> domainListToDtoList(List<D> domainList);

    D formToDomain(F form);

    List<D> formListToDomainList(List<F> formList);
}
