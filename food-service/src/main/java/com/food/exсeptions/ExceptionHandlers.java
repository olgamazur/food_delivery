package com.food.exсeptions;

import com.food.controllers.ClientController;
import com.food.controllers.ManagerController;
import com.food.controllers.MealController;
import com.food.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

    @ControllerAdvice(assignableTypes ={ClientController.class,ManagerController.class,MealController.class})

    public class ExceptionHandlers {
        @ExceptionHandler(TargetNotFoundException.class)
        public ResponseEntity<ErrorDetails> handleUserNotFoundException(TargetNotFoundException ex) {
            return new ResponseEntity(JsonUtils.asJsonString(new ErrorDetails(ex.getMessage(), 404)), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(TargetAlreadyExistsException.class)
        public ResponseEntity<ErrorDetails> handleUserBadRequestException(TargetAlreadyExistsException ex) {
            return new ResponseEntity(JsonUtils.asJsonString(new ErrorDetails(ex.getMessage(), 400)),HttpStatus.BAD_REQUEST);
        }

    }
