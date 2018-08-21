package com.authorization.exceptions;

import com.authorization.controllers.UserOperationsController;
import com.authorization.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes ={UserOperationsController.class})

public class ExceptionHandlers {
    @ExceptionHandler(TargetNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(TargetNotFoundException ex) {
        return new ResponseEntity(JsonUtils.asJsonString(new ErrorDetails(ex.getMessage(), 404)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TargetAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUserBadRequestException(TargetAlreadyExistsException ex) {
        return new ResponseEntity(JsonUtils.asJsonString(new ErrorDetails(ex.getMessage(), 400)),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorDetails> handleUserBadRequestException(InternalErrorException ex) {
        return new ResponseEntity(JsonUtils.asJsonString(new ErrorDetails(ex.getMessage(), 500)),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
