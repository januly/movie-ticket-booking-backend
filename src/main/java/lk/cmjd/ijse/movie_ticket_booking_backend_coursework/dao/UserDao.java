package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
}
