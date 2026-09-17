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
public class SignInDTO {
    @NotBlank @Email @Size(max = 150)
    private String email;
    @NotBlank @Size(max = 72)
    @ToString.Exclude
    private String password;
}
