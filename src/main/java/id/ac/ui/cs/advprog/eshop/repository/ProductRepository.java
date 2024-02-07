package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.HashSet;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private HashSet<String> generatedId = new HashSet<>();

    public Product create(Product product){
        //Generate id unik untuk produk
        String id = UUID.randomUUID().toString();
        while (generatedId.contains(id)){
            id = UUID.randomUUID().toString();
        }
        product.setProductId(id);

        productData.add(product);
        return product;
    }

    public Product find(String id) throws IllegalArgumentException{
      for (Product product : productData){
          if (product.getProductId().equals(id)){
              return product;
          }
      }

      throw new IllegalArgumentException("Product Doesn't Exist");
    };

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit(String id, String newName, int newQuantity) throws IllegalArgumentException{
        Product product = find(id);

        product.setProductName(newName);
        product.setProductQuantity(newQuantity);

        return product;
    }
}
