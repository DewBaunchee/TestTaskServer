package by.vorivoda.matvey.app.model.service;

import by.vorivoda.matvey.app.model.entity.IDaoEntity;
import by.vorivoda.matvey.app.model.repository.IDaoRepository;

import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractService<ID, T extends IDaoEntity<ID>> {

    protected IDaoRepository<ID, T> repository;

    @Transactional
    public boolean add(T dao) {
        try {
            repository.create(dao);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public T get(ID id) {
        return repository.getById(id);
    }

    @Transactional
    public List<T> getAll() {
        return repository.getAll();
    }

    @Transactional
    public boolean update(T dao) {
        try {
            repository.update(dao.getId(), dao);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean delete(ID id) {
        try {
            repository.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
