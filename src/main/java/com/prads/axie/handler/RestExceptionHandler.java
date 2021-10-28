// package com.prads.axie.handler;
//
// import java.util.Date;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import com.prads.axie.exception.ExceptionDetails;
// import com.prads.axie.exception.ResourceNotFoundException;
//
// @ControllerAdvice
// public class RestExceptionHandler {
//
// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Usuário não encontrado.")
// @ExceptionHandler(ResourceNotFoundException.class)
// public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(
// ResourceNotFoundException exception) {
// ExceptionDetails details = new ExceptionDetails.builder().status(HttpStatus.NOT_FOUND.value())
// .timestamp(new Date().getTime()).message(exception.getMessage())
// .title("Usuário não encontrado.").build();
//
// return new ResponseEntity<ExceptionDetails>(details, HttpStatus.NOT_FOUND);
// }
// }
