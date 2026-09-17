package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao;

import jakarta.persistence.LockModeType;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.ShowEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowDao extends JpaRepository<ShowEntity, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from ShowEntity s where s.showId = :id")
    Optional<ShowEntity> findForUpdate(@Param("id") String id);
    List<ShowEntity> findByMovieMovieId(String movieId);
    List<ShowEntity> findByTheatreTheatreIdAndStatus(String theatreId, showStatus status);
    boolean existsByMovieMovieId(String movieId);
    boolean existsByTheatreTheatreId(String theatreId);
}
