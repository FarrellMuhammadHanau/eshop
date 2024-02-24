package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Entity;

import java.util.List;

public interface SearchService <T extends Entity> {
    public List<T> findAll();
    T findById(String id);
}
