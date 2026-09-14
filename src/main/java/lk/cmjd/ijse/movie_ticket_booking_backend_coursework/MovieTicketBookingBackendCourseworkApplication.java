package lk.cmjd.ijse.movie_ticket_booking_backend_coursework;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MovieTicketBookingBackendCourseworkApplication {

	public static void main(String[] args) {
		SpringApplication.run
				(MovieTicketBookingBackendCourseworkApplication.class, args);
	}
    @Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
