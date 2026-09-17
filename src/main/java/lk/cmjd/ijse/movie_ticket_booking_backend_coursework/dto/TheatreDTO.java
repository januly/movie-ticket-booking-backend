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
public class TheatreDTO {
    private String theatreId;
    @NotBlank @Size(max = 150)
    private String theatreName;
    @NotBlank @Size(max = 200)
    private String location;
    @Min(1) @Max(1000)
    private int capacity;
    @NotNull
    private theatreStatus status;
}
