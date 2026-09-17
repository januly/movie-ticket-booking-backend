package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String movieId;
    @NotBlank @Size(max = 150)
    private String title;
    @NotBlank @Size(max = 2000)
    private String description;
    @Min(1) @Max(600)
    private int duration;
    @NotBlank @Size(max = 50)
    private String language;
    @NotBlank @Size(max = 80)
    private String genre;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    private movieStatus status;
}
