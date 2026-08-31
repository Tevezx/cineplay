package play.cine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandlerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultErrorMessage> handleNotFoundException(NotFoundException e) {
        var error = new DefaultErrorMessage(HttpStatus.NOT_FOUND.value(), e.getReason());

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<DefaultErrorMessage> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getReason());

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(AvaliacaoAlreadyExistsException.class)
    public ResponseEntity<DefaultErrorMessage> handleAvaliacaoAlreadyExistsException(AvaliacaoAlreadyExistsException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getReason());

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DefaultErrorMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        var error = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());

        return ResponseEntity.status(400).body(error);
    }
}
