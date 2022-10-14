package co.zoltans.memento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, message, new RuntimeException(message)
        );
    }
}
