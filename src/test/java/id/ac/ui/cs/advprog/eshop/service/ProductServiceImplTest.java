package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        assertEquals(productService.create(product), product);
    }

    @Test
    void testFind() {
        Product productToFind = new Product();
        productToFind.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productToFind.setProductName("Sampo Cap Bambang");
        productToFind.setProductQuantity(100);

        when(productRepository.find("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(productToFind);
        assertEquals(productService.find("eb558e9f-1c39-460e-8860-71af6af63bd6"), productToFind);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Farrell Muhammad Hanau");
        product2.setProductQuantity(100);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);

        when(productRepository.findAll()).thenReturn(productList1.iterator());
        List<Product> productList2 = productService.findAll();

        //Check if the list is perfectly matched
        assertEquals(productList1.size(), productList2.size());
        for (int i = 0; i < productList1.size(); i++){
            assertEquals(productList1.get(i), productList2.get(i));
        }
    }

    @Test
    void testEdit(){
        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductName("Sampo Cap Bambang");
        editedProduct.setProductQuantity(100);

        when(productRepository.edit(editedProduct.getProductId(), editedProduct.getProductName(), editedProduct.getProductQuantity())).thenReturn(editedProduct);
        Product retValProduct = productService.edit(editedProduct);

        assertEquals(retValProduct.getProductId(), editedProduct.getProductId());
        assertEquals(retValProduct.getProductName(), editedProduct.getProductName());
        assertEquals(retValProduct.getProductQuantity(), editedProduct.getProductQuantity());
    }

    @Test
    void testDelete(){
        Product deletedProduct = new Product();
        deletedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        deletedProduct.setProductName("Sampo Cap Bambang");
        deletedProduct.setProductQuantity(100);

        when(productRepository.delete(deletedProduct.getProductId())).thenReturn(deletedProduct);

        assertEquals(productService.delete("eb558e9f-1c39-460e-8860-71af6af63bd6"), deletedProduct);
    }
}
