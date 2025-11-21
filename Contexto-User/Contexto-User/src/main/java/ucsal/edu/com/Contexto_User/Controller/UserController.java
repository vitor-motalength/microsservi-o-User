package ucsal.edu.com.Contexto_User.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucsal.edu.com.Contexto_User.DTO.UserDTO;
import ucsal.edu.com.Contexto_User.DTO.UserRegistrationDTO;
import ucsal.edu.com.Contexto_User.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.  Accessible without authentication as
     * configured in SecurityConfig.  Clients must provide a username,
     * password and at least one role.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Validated @RequestBody UserRegistrationDTO dto) {
        UserDTO created = userService.registerUser(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
