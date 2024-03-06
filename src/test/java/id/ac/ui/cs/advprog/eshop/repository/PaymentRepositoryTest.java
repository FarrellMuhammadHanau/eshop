package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    Payment payment1, payment2;
    Order order1, order2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Map<String, String> voucherPaymentData1 = new HashMap<>();
        voucherPaymentData1.put("voucherCode", "ESHOP1234ABC5678");
        payment1 = new Payment("136522556-012a-4c07-b546-54eb1396d79b", "VOUCHER", voucherPaymentData1);

        List<Product> products1 = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products1.add(product1);

        order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products1, 1708560000L, "Safira Sudrajat");


        Map<String, String> voucherPaymentData2 = new HashMap<>();
        voucherPaymentData2.put("voucherCode", "ESHOP1234ABC5678");
        payment2 = new Payment("136522556-012a-4c07-b546-54eb1396d79c", "VOUCHER", voucherPaymentData2);


        List<Product> products2 = new ArrayList<>();
        products2.add(product1);

        order2 = new Order("136522556-012a-4c07-b546-54eb1396d79c",
                products2, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testFindAllButEmpty() {
        List<Payment> payments = paymentRepository.findAll();
        assertEquals(payments.size(), 0);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment1, order1);
        paymentRepository.save(payment2, order2);
        List<Payment> payments = paymentRepository.findAll();

        assertEquals(payments.size(), 2);
        assertEquals(payments.get(0), payment1);
        assertEquals(payments.get(1), payment2);
    }

    @Test
    void testFindById() {
        paymentRepository.save(payment1, order1);
        paymentRepository.save(payment2, order2);

        Payment tempPayment = paymentRepository.findById(payment1.getId());
        assertEquals(tempPayment, payment1);
    }

    @Test
    void testFindByIdButNotExist() {
        paymentRepository.save(payment1, order1);
        paymentRepository.save(payment2, order2);

        Payment tempPayment = paymentRepository.findById("ByeBye");
        assertNull(tempPayment);
    }

    @Test
    void testFindOrderByPaymentId() {
        paymentRepository.save(payment1, order1);
        paymentRepository.save(payment2, order2);

        Order tempOrder = paymentRepository.findOrderByPaymentId(payment1.getId());
        assertEquals(tempOrder, order1);
    }

    @Test
    void testFindOrderByPaymentIdButNotExist() {
        paymentRepository.save(payment1, order1);
        paymentRepository.save(payment2, order2);

        Order tempOrder = paymentRepository.findOrderByPaymentId("ByeBye");
        assertNull(tempOrder);
    }

    @Test
    void testSavePayment() {
        paymentRepository.save(payment1, order1);
        paymentRepository.save(payment2, order2);

        Payment tempPayment = paymentRepository.findById(payment1.getId());
        assertEquals(tempPayment, payment1);

        Order tempOrder = paymentRepository.findOrderByPaymentId(tempPayment.getId());
        assertEquals(tempOrder, order1);
    }

    @Test
    void testSavePaymentButDuplicate() {
        paymentRepository.save(payment1, order1);
        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.save(payment1, order1);
        });
    }

    @Test
    void testSaveTwoPaymentSameOrder() {
        paymentRepository.save(payment1, order1);
        assertThrows(IllegalArgumentException.class, () -> {
           paymentRepository.save(payment1, order2);
        });
    }
}