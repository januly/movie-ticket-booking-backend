package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities;

import jakarta.persistence.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    private String bookingId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private ShowEntity show;
    @OneToOne(mappedBy = "booking", fetch = FetchType.LAZY)
    private PaymentEntity payment;
    @Column(nullable = false, length = 1000)
    private String seatNumbers;
    @Column(nullable = false)
    private int numberOfTickets;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private LocalDateTime bookingDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private bookingStatus status;
}
