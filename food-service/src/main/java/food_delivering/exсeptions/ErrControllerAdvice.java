package food_delivering.exсeptions;

import food_delivering.contollers.ClientController;
import food_delivering.contollers.ManagerController;
import food_delivering.contollers.MealController;
import food_delivering.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

    @ControllerAdvice(assignableTypes ={ClientController.class,ManagerController.class,MealController.class})

    public class ErrControllerAdvice {
        @ExceptionHandler(TargetNotFoundException.class)
        public ResponseEntity<ErrorDetails> handleUserNotFoundException(TargetNotFoundException ex) {
            return new ResponseEntity(JsonUtils.asJsonString(new ErrorDetails(ex.getMessage(), 404)), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(TargetAlreadyExistsException.class)
        public ResponseEntity<ErrorDetails> handleUserBadRequestException(TargetAlreadyExistsException ex) {
            return new ResponseEntity(JsonUtils.asJsonString(new ErrorDetails(ex.getMessage(), 400)),HttpStatus.BAD_REQUEST);
        }

    }
