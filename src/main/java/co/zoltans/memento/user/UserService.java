package co.zoltans.memento.user;

import co.zoltans.memento.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long id) {
        log.info("getUser was called with id: {}", id);
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public List<User> getUsers() {
        log.info("getUsers was called");
        return userRepository.findAll();
    }

    public User getUserByUserName(String username) {
        var user = userRepository.findByUserName(username);
        return user
                .orElseThrow(() -> new NotFoundException("User not found with username: " + username));
    }

    public void createUser(User user) {
        log.info("createUser was called");
        userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        log.info("updateUser was called");
        // find the user and update it
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        userToUpdate.setUserName(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id) {
        log.info("deleteUser was called");
        userRepository.deleteById(id);
    }

    public void login(User user) {
        log.info("login was called");
        // find the user and update it
        User userToLogin = userRepository.findByUserName(user.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found with username: " + user.getUsername()));
        if (userToLogin.getPassword().equals(user.getPassword())) {
            log.info("login successful");
        } else {
            log.info("login failed");
        }
    }
}
