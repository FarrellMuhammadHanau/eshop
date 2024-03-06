package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.UUID;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService{
    private final HashSet<String> generatedId = new HashSet<>();

    @Autowired
    private PaymentRepository paymentRepository;
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String id = null;
        do {
            id = UUID.randomUUID().toString();
        } while (generatedId.contains(id));
        generatedId.add(id);
        Payment payment = new Payment(id, method, paymentData);
        return paymentRepository.save(payment, order);
    }
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        Order order = paymentRepository.findOrderByPaymentId(payment.getId());

        if (status == "SUCCESS") {
            order.setStatus("SUCCESS");
        }
        else {
            order.setStatus("FAILED");
        }

        return payment;
    }
    public Payment getPayment(String id) {
        Payment payment = paymentRepository.findById(id);

        if (payment == null) {
            throw new IllegalArgumentException();
        }

        return payment;
    }
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
