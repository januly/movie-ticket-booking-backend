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
public class SeatAvailabilityDTO {
    private String showId;
    private int capacity;
    private List<String> bookedSeats;
    private List<String> availableSeats;
}
