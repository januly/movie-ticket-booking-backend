package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDTO implements Serializable {
    private String name;
    private String email;
    private String password;
}
