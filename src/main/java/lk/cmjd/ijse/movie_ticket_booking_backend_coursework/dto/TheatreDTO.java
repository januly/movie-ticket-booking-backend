package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.theatreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatreDTO implements Serializable {

    private String theatreId;
    private String theatreName;
    private String location;
    private String Capacity;
    private theatreStatus status;
}
