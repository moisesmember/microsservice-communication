package br.com.konisberg.product_api.domain.repository;

import java.util.List;
import java.util.UUID;

public interface GenericRepository<D, F> {
    List<D> findAll();

    D findById(Integer id);

    D create(F param);

    D update(Integer id, F param);

    D delete(Integer id);

    long count(String search);
}
