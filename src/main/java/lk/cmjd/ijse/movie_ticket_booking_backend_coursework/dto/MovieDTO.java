package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.movieStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO implements Serializable {
    private String movieId;
    private String title;
    private String description;
    private String duration;
    private String language;
    private String genre;
    private String releaseDate;
    private movieStatus status;
}
