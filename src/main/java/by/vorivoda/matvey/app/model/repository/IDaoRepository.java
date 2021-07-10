package by.vorivoda.matvey.app.model.repository;

import by.vorivoda.matvey.app.model.entity.IDaoEntity;

import java.util.List;

public interface IDaoRepository<ID, T extends IDaoEntity<ID>> {
    void create(T entity);

    T getById(ID id);

    List<T> getAll();

    void update(ID id, T entity);

    void delete(ID id);
}
