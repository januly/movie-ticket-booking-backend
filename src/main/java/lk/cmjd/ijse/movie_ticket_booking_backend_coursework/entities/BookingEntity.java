package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.bookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name="Booking")
public class BookingEntity implements Serializable {
    @Id
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private ShowEntity show;

    @OneToOne(mappedBy = "booking")
    @JsonIgnore
    private PaymentEntity payment;

    private String seatNumbers;
    @Column(nullable = false)
    private String numberOfTickets;
    private double totalAmount;
    private String bookingDate;
    @Enumerated(EnumType.STRING)
    private bookingStatus status;


}

