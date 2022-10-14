package co.zoltans.memento;

import co.zoltans.memento.memento.Memento;
import co.zoltans.memento.memento.MementoRepository;
import co.zoltans.memento.memento.MementoService;
import co.zoltans.memento.memento.Priority;
import co.zoltans.memento.user.User;
import co.zoltans.memento.user.UserRepository;
import co.zoltans.memento.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    private UserService userService;
    private MementoService mementoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MementoRepository mementoRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        mementoService = new MementoService(userRepository, mementoRepository);

        // start with a clean slate
        mementoRepository.deleteAll();
        mementoRepository.flush();
        userRepository.deleteAll();
        userRepository.flush();
    }

    @AfterEach
    void tearDown() {
        // clean up after each test
        mementoRepository.deleteAll();
        mementoRepository.flush();
        userRepository.deleteAll();
        userRepository.flush();
    }

    @Test
    void createUser() {
        // given
        final var user = new User("John", "Doe", "JohnDoe", "j.doe@gmail.com", "secret");
        user.addMemento(new Memento(user, "Buy milk", Priority.HIGH));
        user.addMemento(new Memento(user, "Buy eggs", Priority.LOW));
        user.addMemento(new Memento(user, "Buy cheese", Priority.MEDIUM));


        // when
        userService.createUser(user);

        // then
        assertEquals(1, userRepository.count());
        assertEquals(3, mementoRepository.count());
    }

    @Test
    void deleteUser() {
        // given
        final var user = new User("John", "Doe", "JohnDoe", "j.doe@gmail.com", "secret");
        user.addMemento(new Memento(user, "Buy milk", Priority.HIGH));
        user.addMemento(new Memento(user, "Buy eggs", Priority.LOW));
        user.addMemento(new Memento(user, "Buy cheese", Priority.MEDIUM));
        userService.createUser(user);

        // when
        userService.deleteUser(user.getId());

        // then
        assertEquals(0, userRepository.count());
        assertEquals(0, mementoRepository.count());
    }

}