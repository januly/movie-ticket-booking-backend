package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao extends JpaRepository<MovieEntity,String> {
}
