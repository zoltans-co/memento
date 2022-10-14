package co.zoltans.memento.memento;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/mementos")
public class MementoController {

    private final MementoService mementoService;

    @GetMapping
    public List<Memento> getMementos() {
        return mementoService.getMementos();
    }

    @GetMapping(path = "{mementoId}")
    public Memento getMemento(@PathVariable("mementoId") Long id) {
        return mementoService.getMemento(id);
    }

    @PostMapping(path = "username/{username}")
    public void createNewMemento(@PathVariable("username") String username, @RequestBody Memento memento) {
        mementoService.createMemento(username, memento);
    }

    @PutMapping(path = "{mementoId}")
    public void updateMemento(@PathVariable("mementoId") Long id, @RequestBody Memento memento) {
        mementoService.updateMemento(id, memento);
    }

    @DeleteMapping(path = "{mementoId}")
    public void deleteMemento(@PathVariable("mementoId") Long id) {
        mementoService.deleteMemento(id);
    }
}
