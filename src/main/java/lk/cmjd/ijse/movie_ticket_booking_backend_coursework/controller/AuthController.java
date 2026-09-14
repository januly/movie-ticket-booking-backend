package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;


import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.AuthDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@RequestBody AuthDTO authDTO) {
        authService.signUp(authDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signIn(@RequestBody AuthDTO authDTO) {
        return new ResponseEntity<>(authService.signIn(authDTO), HttpStatus.OK);
    }

}
