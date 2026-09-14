package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.PaymentDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.paymentStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceIMPL implements PaymentService {

    @Override
    public void createPayment(PaymentDTO payment) {

        System.out.println("Create PaymentServiceIMPL: " + payment);

    }

    @Override

    public PaymentDTO getselectedPayment(String paymentId) {

        return new PaymentDTO(
                "P001",
                "2500.00",
                "2026-10-09",
                "CARD",
                paymentStatus.COMPLETED

        );
    }

    @Override
    public void processPayment(String paymentId) {
        System.out.println("Processing Payment: " + paymentId);

    }

    @Override
    public paymentStatus getPaymentStatus(String paymentId) {
        System.out.println("Checking Payment Status: " + paymentId);
        return paymentStatus.PENDING;
    }}
