package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.paymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO implements Serializable {
    private String paymentId;
    private String bookingAmount;
    private String paymentDate;
    private String paymentMethod;
    private paymentStatus status;

}
