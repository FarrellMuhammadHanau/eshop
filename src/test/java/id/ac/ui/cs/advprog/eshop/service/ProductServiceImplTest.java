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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void testCreate(){
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        when(productRepository.create(product)).thenReturn(product);

        assertEquals(productService.create(product), product);
    }

    @Test
    void testFind() {
        Product productToFind = new Product();
        productToFind.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productToFind.setName("Sampo Cap Bambang");
        productToFind.setQuantity(100);

        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(productToFind);
        assertEquals(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"), productToFind);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);

        Product product2 = new Product();
        product2.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setName("Farrell Muhammad Hanau");
        product2.setQuantity(100);

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
        editedProduct.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setName("Sampo Cap Bambang");
        editedProduct.setQuantity(100);

        when(productRepository.update(editedProduct.getId(), editedProduct)).thenReturn(editedProduct);

        productService.update(editedProduct.getId(), editedProduct);

        verify(productRepository).update(editedProduct.getId(), editedProduct);
    }

    @Test
    void testDelete(){
        Product deletedProduct = new Product();
        deletedProduct.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        deletedProduct.setName("Sampo Cap Bambang");
        deletedProduct.setQuantity(100);

        doNothing().when(productRepository).delete(any());
        productService.deleteById(deletedProduct.getId());
        verify(productRepository).delete(deletedProduct.getId());
    }
}
