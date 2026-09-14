package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.showStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Shows")
@Builder
    public class ShowEntity implements Serializable {
        @Id
        private String showId;
        private String showDate;
        private String showTime;
        private String ticketPrice;
        @Enumerated(EnumType.STRING)
        private showStatus status;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private TheatreEntity theatre;

    @OneToMany(mappedBy = "show")
    @JsonIgnore
    private List<BookingEntity> bookings;

}
