package co.zoltans.memento.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Long id) {
        return userService.getUser(id);
    }

    @GetMapping(path = "username/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUserName(username);
    }

    @GetMapping(path = "memento/{userName}")
    public User getUserMementos(@PathVariable("userName") String userName) {
        return userService.getUserByUserName(userName);
    }

    @PostMapping(path = "login")
    public void login(@RequestBody User user) {
        userService.login(user);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }
}