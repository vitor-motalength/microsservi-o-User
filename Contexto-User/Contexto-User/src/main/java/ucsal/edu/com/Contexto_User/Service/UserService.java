package ucsal.edu.com.Contexto_User.Service;
import ucsal.edu.com.Contexto_User.Exception.BadRequestException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ucsal.edu.com.Contexto_User.DTO.UserDTO;
import ucsal.edu.com.Contexto_User.DTO.UserRegistrationDTO;
import ucsal.edu.com.Contexto_User.Exception.ResourceNotFoundException;
import ucsal.edu.com.Contexto_User.Repository.UserRepository;
import ucsal.edu.com.Contexto_User.entity.User;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO registerUser(UserRegistrationDTO dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new BadRequestException("Usuário já existe: " + dto.username());
        }
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(dto.roles());
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id " + id));
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}
