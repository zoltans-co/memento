package co.zoltans.memento.memento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MementoRepository extends JpaRepository<Memento, Long> {

    Optional<Memento>  findByReminder(String reminder);

}