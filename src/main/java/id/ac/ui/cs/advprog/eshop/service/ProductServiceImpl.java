package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.IRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final IRepository<Product> productRepository;

    public ProductServiceImpl(IRepository<Product> productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product){
        productRepository.create(product);
        return product;
    }

    @Override
    public Product findById(String id){
        Product product = productRepository.findById(id);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public void update(String id, Product product){
        // TODO Auto Generated method stub
        productRepository.update(id, product);
    }

    @Override
    public void deleteById(String id){
        productRepository.delete(id);
    }
}
