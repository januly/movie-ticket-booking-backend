package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Conversion {
    private final ModelMapper mapper;

    public MovieDTO toMovieDTO(MovieEntity entity) { return mapper.map(entity, MovieDTO.class); }
    public MovieEntity toMovieEntity(MovieDTO dto) { return mapper.map(dto, MovieEntity.class); }
    public TheatreDTO toTheatreDTO(TheatreEntity entity) { return mapper.map(entity, TheatreDTO.class); }
    public TheatreEntity toTheatreEntity(TheatreDTO dto) { return mapper.map(dto, TheatreEntity.class); }
    public UserDTO toUserDTO(UserEntity entity) {
        return new UserDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getRole());
    }

    public ShowDTO toShowDTO(ShowEntity entity) {
        return new ShowDTO(entity.getShowId(), entity.getMovie().getMovieId(), entity.getTheatre().getTheatreId(),
                entity.getShowDate(), entity.getShowTime(), entity.getTicketPrice(), entity.getStatus());
    }

    public BookingDTO toBookingDTO(BookingEntity entity) {
        return new BookingDTO(entity.getBookingId(), entity.getUser().getId(), entity.getShow().getShowId(),
                Arrays.asList(entity.getSeatNumbers().split(",")), entity.getNumberOfTickets(),
                entity.getTotalAmount(), entity.getBookingDate(), entity.getStatus(),
                entity.getPayment() == null ? null : entity.getPayment().getPaymentId());
    }

    public PaymentDTO toPaymentDTO(PaymentEntity entity) {
        return new PaymentDTO(entity.getPaymentId(), entity.getBooking().getBookingId(), entity.getAmount(),
                entity.getPaymentDate(), entity.getPaymentMethod(), entity.getStatus());
    }
}
