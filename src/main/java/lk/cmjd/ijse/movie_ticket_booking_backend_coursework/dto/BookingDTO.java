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
public class BookingDTO {
    private String bookingId;
    private String userId;
    @NotBlank @JsonAlias("show")
    private String showId;
    @NotEmpty @Size(max = 100)
    private List<@NotBlank @Pattern(regexp = "[a-zA-Z]{1,2}[1-9][0-9]?") String> seatNumbers;
    @Min(1) @Max(100)
    private int numberOfTickets;
    private BigDecimal totalAmount;
    private LocalDateTime bookingDate;
    private bookingStatus status;
}
