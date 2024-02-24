package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.HashSet;

@Repository
public class ProductRepository implements IRepository<Product>{
    private List<Product> productData = new ArrayList<>();
    private HashSet<String> generatedId = new HashSet<>();

    public Product create(Product product){
        //Generate id unik untuk produk
        String id = UUID.randomUUID().toString();
        while (generatedId.contains(id)){
            id = UUID.randomUUID().toString();
        }
        product.setId(id);

        productData.add(product);
        return product;
    }

    public Product findById(String id){
        for(Product product: productData){
            if(product.getId().equals(id)){
                return product;
            }
        }

        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product update(String id, Product updatedProduct){
        for(int i = 0; i < productData.size(); i++){
            Product product = productData.get(i);
            if(product.getId().equals(id)){
                product.setName(updatedProduct.getName());
                product.setQuantity(updatedProduct.getQuantity());
                return product;
            }
        }

        return null;
    }

    public void delete(String id){
        Product product = findById(id);
        productData.remove(product);
    }
}
