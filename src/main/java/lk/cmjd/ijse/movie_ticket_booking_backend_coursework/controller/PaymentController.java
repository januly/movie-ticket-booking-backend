package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.PaymentDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.paymentStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/payments")
@RestController
@RequiredArgsConstructor

public class PaymentController {
    public final PaymentService paymentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{paymentid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDTO> getSelectedPayment(@PathVariable("paymentid") String paymentId) {
        return new ResponseEntity<>(paymentService.getselectedPayment(paymentId), HttpStatus.OK);
    }

    @PutMapping("/{paymentId}/process")
    public ResponseEntity<Void> processPayment(@PathVariable String paymentId) {
        paymentService.processPayment(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{paymentId}/status")
    public ResponseEntity<paymentStatus> getPaymentStatus(@PathVariable String paymentId) {

        paymentStatus status = paymentService.getPaymentStatus(paymentId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}

