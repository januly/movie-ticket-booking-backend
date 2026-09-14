package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getselectedUser(String id);
    List<UserDTO> getAllusers();
    void updateUser(String id,UserDTO user);
    void deleteUser(String id);
}
