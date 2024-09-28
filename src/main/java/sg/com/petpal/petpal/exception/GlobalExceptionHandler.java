package sg.com.petpal.petpal.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(OwnerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(),
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<Object> handlePasswordsDoNotMatchException(PasswordsDoNotMatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PetDataNotFoundException.class)
    public ResponseEntity<Object> handlePetDataNotFoundException(PetDataNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(),
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // The other exceptions catchall
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

class ErrorResponse {
    private final Map<String, Object> map = new HashMap<>();

    // Constructor to set required fields
    public ErrorResponse(LocalDateTime timestamp, String message, int status) {
        map.put("timestamp", timestamp);
        map.put("message", message);
        map.put("status", status);
    }

    // Getter for timestamp
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) map.get("timestamp");
    }

    // Getter for message
    public String getMessage() {
        return (String) map.get("message");
    }

    // Getter for status
    public int getStatus() {
        return (int) map.get("status");
    }
}