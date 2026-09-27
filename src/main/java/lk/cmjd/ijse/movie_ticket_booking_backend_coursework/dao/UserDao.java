package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao;

import jakarta.persistence.LockModeType;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.UserEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByRole(Role role);
}
