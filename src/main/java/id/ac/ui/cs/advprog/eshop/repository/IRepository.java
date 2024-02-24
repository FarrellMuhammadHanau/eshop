package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Entity;

import java.util.Iterator;

public interface IRepository <T extends Entity> {
    public T create(T entity);
    public Iterator<T> findAll();
    public T findById(String id);
    public T update(String id, T updatedEntity);
    public void delete(String id);
}
