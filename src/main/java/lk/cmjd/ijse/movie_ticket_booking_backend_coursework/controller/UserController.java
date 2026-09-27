package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import jakarta.validation.Valid;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.UserDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.UserService;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.security.CurrentUser;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util.Conversion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService userService;
    private final CurrentUser currentUser;
    private final Conversion conversion;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> me() { return ResponseEntity.ok(conversion.toUserDTO(currentUser.get())); }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() { return ResponseEntity.ok(userService.getUsers()); }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable String id) { return ResponseEntity.ok(userService.getSelectedUser(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
