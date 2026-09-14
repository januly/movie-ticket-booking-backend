package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String id;
    private Role role;
}
