package play.cine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AvaliacaoAlreadyExistsException extends ResponseStatusException {
    public AvaliacaoAlreadyExistsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
