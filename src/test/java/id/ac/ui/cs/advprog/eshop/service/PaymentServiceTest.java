package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    Payment payment;
    Order order;
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        Map<String, String> voucherPaymentData1 = new HashMap<>();
        voucherPaymentData1.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", "VOUCHER", voucherPaymentData1);

        List<Product> products1 = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products1.add(product1);

        order = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products1, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testAddPayment() {
        doReturn(payment).when(paymentRepository).save(any(Payment.class), any(Order.class));
        Payment tempPayment = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        assertEquals(tempPayment, payment);
    }

    @Test
    void testSetStatusSuccess() {
        doReturn(order).when(paymentRepository).findOrderByPaymentId(payment.getId());
        Payment tempPayment = paymentService.setStatus(payment, "SUCCESS");
        assertEquals(tempPayment, payment);
        assertEquals(payment.getStatus(), "SUCCESS");
        assertEquals(order.getStatus(), "SUCCESS");
    }

    @Test
    void testSetStatusRejected() {
        doReturn(order).when(paymentRepository).findOrderByPaymentId(payment.getId());
        Payment tempPayment = paymentService.setStatus(payment, "REJECTED");
        assertEquals(tempPayment, payment);
        assertEquals(payment.getStatus(), "REJECTED");
        assertEquals(order.getStatus(), "FAILED");
    }

    @Test
    void testSetStatusInvalid() {
        doReturn(order).when(paymentRepository).findOrderByPaymentId(payment.getId());
        assertThrows(IllegalArgumentException.class, () -> {
           paymentService.setStatus(payment, "ByeBye");
        });
    }

    @Test
    void testGetPayment() {
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment tempPayment = paymentService.getPayment(payment.getId());
        assertEquals(tempPayment, payment);
    }

    @Test
    void testGetPaymentNontExist() {
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(null).when(paymentRepository).findById("ByeBye");
        assertThrows(IllegalArgumentException.class, () -> {
           paymentService.getPayment("ByeBye");
        });
    }

    @Test
    void testGetAllPayment() {
        List<Payment> payments = new ArrayList<>();
        payments.add(payment);
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> paymentsTemp = paymentService.getAllPayments();
        assertEquals(paymentsTemp, payments);
        assertEquals(paymentsTemp.get(0), payment);
    }
}
