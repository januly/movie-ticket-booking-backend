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
public class ShowDTO {
    private String showId;
    @NotBlank @JsonAlias("movie")
    private String movieId;
    @NotBlank @JsonAlias("theatre")
    private String theatreId;
    @NotNull
    private LocalDate showDate;
    @NotNull
    private LocalTime showTime;
    @NotNull @DecimalMin("0.01") @DecimalMax("999999.99") @Digits(integer = 6, fraction = 2)
    private BigDecimal ticketPrice;
    @NotNull
    private showStatus status;
}
