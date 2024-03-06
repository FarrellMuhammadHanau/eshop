package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class PaymentTest {
    Map<String, String> voucherPaymentData;
    Map<String, String> bankTransferPaymentData;

    @BeforeEach
    void setUp(){
        voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");

        bankTransferPaymentData = new HashMap<>();
        bankTransferPaymentData.put("bankName", "Hanau's Bank");
        bankTransferPaymentData.put("referenceCode", "a1b2c3");
    }

    @Test
    void testCreateValidVoucher() {
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(),
                voucherPaymentData);
        assertEquals(payment.getStatus(), PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testCreateVoucherInvalidVoucherCode() {
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC567");
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(),
                voucherPaymentData);
        assertEquals(payment.getStatus(), PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testCreateVoucherInvalidPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(),
                    bankTransferPaymentData);
        });
    }

    @Test
    void testCreateValidBankTransfer() {
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BANKTRANSFER.getValue(),
                bankTransferPaymentData);
        assertEquals(payment.getStatus(), PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testCreateBankTransferBlankBankName() {
        bankTransferPaymentData.put("bankName", "");
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BANKTRANSFER.getValue(),
                bankTransferPaymentData);
        assertEquals(payment.getStatus(), PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testCreateBankTransferBlankReferenceCode() {
        bankTransferPaymentData.put("referenceCode", "");
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BANKTRANSFER.getValue(),
                bankTransferPaymentData);
        assertEquals(payment.getStatus(), PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testCreateBankTransferInvalidPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BANKTRANSFER.getValue(),
                    voucherPaymentData);
        });
    }

    @Test
    void testCreateMethodNotValid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", "something",
                    voucherPaymentData);
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(),
                voucherPaymentData);
        payment.setStatus("REJECTED");
        assertEquals(payment.getStatus(), PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testSetStatusToInvalid() {
        Payment payment = new Payment("136522556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(),
                voucherPaymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("byebye"));
    }
}