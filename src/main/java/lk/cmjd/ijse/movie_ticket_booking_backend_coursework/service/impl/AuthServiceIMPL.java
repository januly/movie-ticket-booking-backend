package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.AuthDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.AuthService;
import org.springframework.stereotype.Service;

@Service

public class AuthServiceIMPL implements AuthService {

    @Override
    public void signUp(AuthDTO authDTO) {
        System.out.println("Create User: " + authDTO);
    }

    @Override
    public String signIn(AuthDTO authDTO) {
        System.out.println("Signin User: " + authDTO.getEmail());

        return "Signin request received successfully";
    }
}
