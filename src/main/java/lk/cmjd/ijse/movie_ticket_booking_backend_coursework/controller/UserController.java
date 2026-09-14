package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.UserDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.Role;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.getAllusers(),HttpStatus.OK);
    }
    @GetMapping (value ="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO>getselectedUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.getselectedUser(id), HttpStatus.OK);
    }
    @PutMapping(value ="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO>updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id,userDTO);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
