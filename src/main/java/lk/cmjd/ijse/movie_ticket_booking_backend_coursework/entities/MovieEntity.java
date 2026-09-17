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
@Table(name = "movies")
public class MovieEntity {
    @Id
    private String movieId;
    @Column(nullable = false, length = 150)
    private String title;
    @Column(nullable = false, length = 2000)
    private String description;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false, length = 50)
    private String language;
    @Column(nullable = false, length = 80)
    private String genre;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private movieStatus status;
    @OneToMany(mappedBy = "movie")
    private List<ShowEntity> shows;
}
