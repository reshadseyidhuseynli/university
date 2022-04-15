package com.company.database.service.inter;

import java.util.List;

public interface GenericService<E,I> {

    E getById(I id);

    List<E> getAll();

    E addOrUpdate(E entity);

    void delete(E entity);
}
