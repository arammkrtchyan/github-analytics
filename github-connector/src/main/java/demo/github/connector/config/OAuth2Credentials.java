package demo.github.connector.config;

import java.util.Objects;

/**
 * Oath2 Authentication configuration.
 *
 * @author Aram Mkrtchyan.
 */
public class OAuth2Credentials {

    private String token;

    private String key;

    private String secret;

    public OAuth2Credentials(String token) {
        this.token = Objects.requireNonNull(token, "Oauth2 token can not be null.");
    }

    public OAuth2Credentials(String key, String secret) {
        this.key = Objects.requireNonNull(key, "Oath2 key can not be null.");
        this.secret = Objects.requireNonNull(secret, "Oath2 secret can not be null.");
    }

    /**
     * OAuth2 client secret.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * OAuth2 client key.
     */
    public String getKey() {
        return key;
    }

    /**
     * OAuth2 secret token.
     */
    public String getToken() {
        return token;
    }
}
