package co.zoltans.memento.memento;

import co.zoltans.memento.exceptions.NotFoundException;
import co.zoltans.memento.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MementoService {

    private final UserRepository userRepository;
    private final MementoRepository mementoRepository;

    public List<Memento> getMementos() {
        log.info("getMementos was called");
        return mementoRepository.findAll();
    }

    public Memento getMemento(Long id) {
        log.info("getMemento was called with id: {}", id);
        var mementoResult = mementoRepository.findById(id);
        log.info("getMemento result: {}", mementoResult);
        return mementoResult.orElseThrow(() -> new NotFoundException("Memento not found with id: " + id));
    }

    public Memento getMementoByReminder(String reminder) {
        return mementoRepository
            .findByReminder(reminder)
            .orElseThrow(() -> new NotFoundException("Memento not found with reminder: " + reminder));
    }

    public void createMemento(final String userName, final Memento memento) {
        log.info("createNewMemento was called");
        userRepository.findByUserName(userName).ifPresent(user -> {
            memento.setUser(user);
            mementoRepository.save(memento);
        });
    }

    public void updateMemento(Long id, Memento memento) {
        log.info("updateMemento was called");
        // find the memento and update it
        Memento mementoToUpdate = mementoRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Memento not found with id: " + id));
        mementoToUpdate.setReminder(memento.getReminder());
        mementoRepository.saveAndFlush(memento);
    }

    public void deleteMemento(Long id) {
        log.info("deleteMemento was called");
        mementoRepository.deleteById(id);
        mementoRepository.flush();
    }
}
