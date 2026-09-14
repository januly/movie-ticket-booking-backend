package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.MovieDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.UserDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.MovieEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Conversion {
    private final ModelMapper mapper;
    //Movie-Conversion
    public MovieDTO toMovieDTO(MovieEntity movieEntity){
        return mapper.map(movieEntity,MovieDTO.class);
    }
    public  MovieEntity toMovieEntity(MovieDTO movieDTO){
        return mapper.map(movieDTO,MovieEntity.class);
    }
}
