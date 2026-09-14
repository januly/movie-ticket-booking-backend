package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.PaymentDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.ShowDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.paymentStatus;

public interface PaymentService {
    void createPayment(PaymentDTO payment);
    PaymentDTO getselectedPayment(String paymentId);
    void processPayment(String paymentId);
    paymentStatus getPaymentStatus(String paymentId);

}
