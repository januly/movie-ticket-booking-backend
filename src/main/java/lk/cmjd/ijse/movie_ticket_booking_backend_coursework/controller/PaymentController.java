package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import jakarta.validation.Valid;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.paymentStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<PaymentDTO> create(@Valid @RequestBody PaymentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PaymentDTO>> getAll() { return ResponseEntity.ok(paymentService.getPayments()); }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> get(@PathVariable String id) { return ResponseEntity.ok(paymentService.getSelectedPayment(id)); }

    @PutMapping("/{id}/process")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<PaymentDTO> process(@PathVariable String id, @Valid @RequestBody ProcessPaymentDTO dto) {
        return ResponseEntity.ok(paymentService.processPayment(id, dto.getStatus()));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<paymentStatus> status(@PathVariable String id) { return ResponseEntity.ok(paymentService.getPaymentStatus(id)); }
}
