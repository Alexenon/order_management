package com.example.xenon.user;

import com.example.xenon.utils.BeanValidator;
import com.example.xenon.utils.exceptions.InternalCriticalException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BeanValidator validator;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, BeanValidator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        if (username == null || username.isEmpty())
            return Optional.empty();

        return userRepository.findByUsername(username);
    }

    public long getUsersCount() {
        return userRepository.count();
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername().trim());
        user.setEmail(request.getEmail().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return save(user);
    }

    private User save(User user) {
        validator.validate(user);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            log.info("Cannot save {}", user, e);
            throw new InternalCriticalException(e);
        }
    }

    public void deleteUser(Long userId) {
        User user = findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Invalid user id: #" + userId));

        try {
            userRepository.delete(user);
        } catch (Exception e) {
            log.error("Cannot delete user #{}", userId, e);
            throw new InternalCriticalException(e);
        }
    }

}
