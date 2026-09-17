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
@Table(name = "shows")
public class ShowEntity {
    @Id
    private String showId;
    @Column(nullable = false)
    private LocalDate showDate;
    @Column(nullable = false)
    private LocalTime showTime;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private showStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private TheatreEntity theatre;
    @OneToMany(mappedBy = "show")
    private List<BookingEntity> bookings;
}
