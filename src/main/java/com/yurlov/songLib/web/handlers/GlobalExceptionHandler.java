package com.yurlov.songLib.web.handlers;

import com.yurlov.songLib.data.model.dto.JsonError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.yurlov.songLib.data.exception.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<JsonError> handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new ResponseEntity<>(new JsonError(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiValidationException.class)
    public ResponseEntity<JsonError> handleApiValidationException(ApiValidationException ex) {
        return new ResponseEntity<>(new JsonError(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
