package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao;

import jakarta.persistence.LockModeType;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.PaymentEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDao extends JpaRepository<PaymentEntity, String> {
    boolean existsByBookingBookingId(String bookingId);
    List<PaymentEntity> findAllByOrderByPaymentDateDesc();
    @Query("select p.booking.show.showId from PaymentEntity p where p.paymentId = :id")
    Optional<String> findShowId(@Param("id") String id);
}
