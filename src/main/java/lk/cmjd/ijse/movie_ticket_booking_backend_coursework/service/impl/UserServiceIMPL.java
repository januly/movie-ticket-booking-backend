package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.UserDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.Role;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService {


    @Override
    public UserDTO getselectedUser(String id) {

        return new UserDTO("U001", Role.ADMIN);
    }

    @Override
    public List<UserDTO> getAllusers() {
        List<UserDTO>UserList =List.of(
                new UserDTO (
                        "U001",
                        Role.ADMIN
                ),
                new UserDTO(
                        "U002",
                        Role.CUSTOMER
                ),
                new UserDTO(
                        "U003",
                        Role.ADMIN
                ),
                new UserDTO(
                        "U004",
                        Role.CUSTOMER
                ));
        return UserList;
    }

    @Override
    public void updateUser(String id, UserDTO user) {
        System.out.println("Updated user id is " + id + " and the user is: " + user.toString());

    }

    @Override
    public void deleteUser(String id) {
        System.out.println("Deleted user id is " + id);

    }
}
