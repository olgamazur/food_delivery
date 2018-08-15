package food_delivering.exсeptions;

public class TargetAlreadyExistsException extends RuntimeException {


    public TargetAlreadyExistsException(String message) {
        super(message);
    }
}
