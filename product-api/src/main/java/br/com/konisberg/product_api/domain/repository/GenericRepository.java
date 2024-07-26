package br.com.konisberg.product_api.domain.repository;

import java.util.List;

public interface GenericRepository<D, F, S> {
    List<D> findAll();

    D findById(Integer id);

    D create(F param);

    D update(Integer id, F param);

    S delete(Integer id);

    long count(String search);

    boolean existsById(Integer id);
}
