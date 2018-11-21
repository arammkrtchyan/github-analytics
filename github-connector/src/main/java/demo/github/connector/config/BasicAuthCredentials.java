package demo.github.connector.config;

import demo.github.connector.config.exception.InvalidBasicAuthCredentialsException;

import java.util.Optional;

/**
 * Configuration class for basic authentication credentials.
 *
 * @author Aram Mkrtchyan.
 */
public class BasicAuthCredentials {

    private String username;

    private String password;

    public BasicAuthCredentials(String username, String password) {
        this.username = Optional.ofNullable(username)
                .orElseThrow(() -> new InvalidBasicAuthCredentialsException("GitHub username can not be null."));
        this.password = Optional.ofNullable(password)
                .orElseThrow(() -> new InvalidBasicAuthCredentialsException("GitHub password can not be null."));
    }

    /**
     * User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * User's name.
     */
    public String getUsername() {
        return username;
    }
}
