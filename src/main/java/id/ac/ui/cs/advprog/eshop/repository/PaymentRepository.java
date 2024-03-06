package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();
    private Map<String, Order> ordermap = new HashMap<>();

    public Payment save(Payment payment, Order order) {
        if (ordermap.containsKey(payment.getId())){
            throw new IllegalArgumentException();
        }

        for (String key : ordermap.keySet()) {
            if (order == ordermap.get(key)) {
                throw new IllegalArgumentException();
            }
        }

        paymentData.add(payment);
        ordermap.put(payment.getId(), order);

        return payment;
    }

    public Payment findById(String id) {
        for (Payment tempPayment : paymentData) {
            if (tempPayment.getId().equals(id)) {
                return tempPayment;
            }
        }
        return null;
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentData);
    }

    public Order findOrderByPaymentId(String id) {
        if (!ordermap.containsKey(id)) {
            return null;
        }
        return ordermap.get(id);
    }
}
