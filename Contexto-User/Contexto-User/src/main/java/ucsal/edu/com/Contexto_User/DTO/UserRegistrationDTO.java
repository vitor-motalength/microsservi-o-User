package ucsal.edu.com.Contexto_User.DTO;

import ucsal.edu.com.Contexto_User.Enums.Role;

import java.util.Set;

public record UserRegistrationDTO(
        String username,
        String password,
        Set<Role> roles
) {}

