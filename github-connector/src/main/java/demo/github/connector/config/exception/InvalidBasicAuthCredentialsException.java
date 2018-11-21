package demo.github.connector.config.exception;

/**
 * @author Aram Mkrtchyan.
 */
public class InvalidBasicAuthCredentialsException extends IllegalArgumentException {

    public InvalidBasicAuthCredentialsException(String s) {
        super(s);
    }
}
