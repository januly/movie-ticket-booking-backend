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
public class PaymentDTO {
    private String paymentId;
    @NotBlank
    private String bookingId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    @NotBlank @Pattern(regexp = "CARD|CASH|BANK_TRANSFER", message = "must be CARD, CASH or BANK_TRANSFER")
    private String paymentMethod;
    private paymentStatus status;
}
