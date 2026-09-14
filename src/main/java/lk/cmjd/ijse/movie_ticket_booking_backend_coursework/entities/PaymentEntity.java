package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities;

import jakarta.persistence.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.paymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Payments")
@Builder
public class PaymentEntity implements Serializable {
    @Id
    private String paymentId;
    private String bookingAmount;
    private String paymentDate;
    @Column(nullable = false)
    private String paymentMethod;
    @Enumerated(EnumType.STRING)
    private paymentStatus status;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private BookingEntity booking;

}
