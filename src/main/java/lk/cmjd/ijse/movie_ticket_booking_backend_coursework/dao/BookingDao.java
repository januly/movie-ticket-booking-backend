package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao;

import jakarta.persistence.LockModeType;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.BookingEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDao extends JpaRepository<BookingEntity, String> {
    List<BookingEntity> findByUserIdOrderByBookingDateDesc(String userId);
    List<BookingEntity> findAllByOrderByBookingDateDesc();
    List<BookingEntity> findByShowShowIdAndStatusNot(String showId, bookingStatus status);
    boolean existsByUserId(String userId);
    boolean existsByShowShowId(String showId);
    @Query("select b.show.showId from BookingEntity b where b.bookingId = :id")
    Optional<String> findShowId(@Param("id") String id);
}
