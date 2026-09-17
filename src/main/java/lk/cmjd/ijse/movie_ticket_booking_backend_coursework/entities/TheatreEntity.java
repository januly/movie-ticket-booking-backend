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
@Table(name = "theatre")
public class TheatreEntity {
    @Id
    private String theatreId;
    @Column(nullable = false, length = 150)
    private String theatreName;
    @Column(nullable = false, length = 200)
    private String location;
    @Column(nullable = false)
    private int capacity;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private theatreStatus status;
    @OneToMany(mappedBy = "theatre")
    private List<ShowEntity> shows;
}
