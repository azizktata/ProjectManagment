package com.example.project.PDS.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException newException =  new ApiException (
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(newException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(ObjectNotFoundException e){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        ApiException newException =  new ApiException (
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(newException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ListNotRemovedException.class)
    public ResponseEntity<Object> handleListNotRemovedException(ListNotRemovedException e){
        HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;
        ApiException newException =  new ApiException (
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(newException, HttpStatus.NOT_ACCEPTABLE);
    }

}
