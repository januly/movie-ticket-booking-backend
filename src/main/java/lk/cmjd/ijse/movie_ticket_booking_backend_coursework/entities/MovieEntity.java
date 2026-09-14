package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.movieStatus;
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
@Table(name="Movies")
@Builder

public class MovieEntity implements Serializable {
    @Id
    private String movieId;
    private String title;
    private String description;
    private String duration;
    private String language;
    private String genre;
    private String releaseDate;
    @Enumerated(EnumType.STRING)
    private movieStatus status;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<ShowEntity> shows;
}
