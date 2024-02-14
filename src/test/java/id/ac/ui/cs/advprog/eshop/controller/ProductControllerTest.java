package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void createProductPageTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/product/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("CreateProduct"));
    }

    @Test
    void createProductPostTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                        .param("productName", "Apple")
                        .param("productQuantity", "10"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list"));
    }

    @Test
    void productListPageTest() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("products", productList))
                .andExpect(MockMvcResultMatchers.view().name("ProductList"));
    }

    @Test
    void editProductPageTest() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productService.find(product.getProductId())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/edit/{id}", product.getProductId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("product", product))
                .andExpect((MockMvcResultMatchers.view().name("EditProduct")));
    }

    @Test
    void editProductPostTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/edit")
                        .param("productName", "Apple")
                        .param("productQuantity", "10")
                        .param("productId", "eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list"));
    }

    @Test
    void deleteProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/delete")
                .param("id", "eb558e9f-1c39-460e-8860-71af6af63bd6"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("list"));
    }
}
