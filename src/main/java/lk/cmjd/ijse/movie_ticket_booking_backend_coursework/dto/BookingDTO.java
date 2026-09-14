package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.Status;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.bookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO implements Serializable {
    private String bookingId;
    private String user;
    private String show;
    private String seatNumbers;
    private String numberOfTickets;
    private double totalAmount;
    private String bookingDate;
    private bookingStatus status;



}
