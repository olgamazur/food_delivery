package food_delivering.exсeptions;

public class TargetNotFoundException extends RuntimeException {


    public TargetNotFoundException(String message) {
        super(message);
    }
}
