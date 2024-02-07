package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution. * Set automatically during each test run by Spring Framework's test context. */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void userCanCreateProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        //Go to ProductListPage
        driver.findElement(By.tagName("a")).click();

        //Go to create product form
        driver.findElement(By.id("create product")).click();

        //Fill input text
        String name = "Apple";
        String quantity = "100";
        driver.findElement(By.id("nameInput")).sendKeys(name);
        driver.findElement(By.id("quantityInput")).sendKeys(quantity);

        //Submit form
        driver.findElement(By.tagName("button")).click();

        //Check there is a product in product list
        driver.findElement(By.tagName("form"));
    }

    @Test
    void userNotYetCreatedProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        //Go to ProductListPage
        driver.findElement(By.tagName("a")).click();

        //Check there is no product in product list
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.tagName("form"));
        });
    }
}
