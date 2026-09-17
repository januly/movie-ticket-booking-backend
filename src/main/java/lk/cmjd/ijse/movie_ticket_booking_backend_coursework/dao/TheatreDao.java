package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao;

import jakarta.persistence.LockModeType;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.TheatreEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreDao extends JpaRepository<TheatreEntity, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from TheatreEntity t where t.theatreId = :id")
    Optional<TheatreEntity> findForUpdate(@Param("id") String id);
}
