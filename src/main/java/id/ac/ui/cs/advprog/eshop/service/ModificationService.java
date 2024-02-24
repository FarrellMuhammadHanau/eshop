package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Entity;

public interface ModificationService <T extends Entity>{
    public T create(T entity);
    public void update(String id, T entity);
    public void deleteById(String id);
}
