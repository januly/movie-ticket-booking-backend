package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.showStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowDTO implements Serializable {
    private String showId;
    private String movie;
    private String theatre;
    private String showDate;
    private String showTime;
    private String ticketPrice;
    private showStatus status;
}
