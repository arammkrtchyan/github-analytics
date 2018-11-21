package demo.github.connector.autoconfigure;

import demo.github.connector.client.GitHubTemplateBuilder;
import demo.github.connector.config.BasicAuthCredentials;
import demo.github.connector.config.OAuth2Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;

/**
 * Base class for configuration of a GiHub Connector.
 *
 * @author Aram Mkrtchyan.
 */
@ConfigurationProperties(prefix = "github.connector")
public class GitHubConnectorProperties {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private String timeZone;

    /**
     * The name of your application or the user name.
     */
    private String userAgent;

    /**
     * Client's authentication.
     */
    private Authentication authentication = new Authentication();

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    private enum AuthenticationType {
        BASIC, OAUTH2, NONE
    }

    private AuthenticationType determineAuthenticationType() {
        return AuthenticationType.BASIC;
    }

    public GitHubTemplateBuilder initializeGitHubTemplateBuilder() {
        GitHubTemplateBuilder builder = new GitHubTemplateBuilder()
                .withUserAgent(Objects.requireNonNull(this.userAgent,
                        "User agent must be specified, it should be github user or application name."));
        if (StringUtils.hasText(this.timeZone)) {
            builder.withTimeZone(TimeZone.getTimeZone(this.timeZone.trim()));
        }
        switch (determineAuthenticationType()) {
            case BASIC:
                Authentication.Basic basic = authentication.getBasic();
                return builder.withBasicAuthentication(
                        new BasicAuthCredentials(basic.getUser(), basic.getPassword())
                );
            case OAUTH2:
                Authentication.OAuth2 oAuth2 = authentication.getOAuth2();
                return builder.withOauth2Authentication(
                        Optional.ofNullable(oAuth2.getToken())
                                .map(token -> new OAuth2Credentials(oAuth2.getToken()))
                                .orElseGet(() -> new OAuth2Credentials(oAuth2.getKey(), oAuth2.getSecret()))

                );
            case NONE:
            default:
                logger.warn("No Authentication credentials provided. For unauthenticated requests, the rate limit allows for up to 60 requests per hour.");
                return builder;
        }

    }

    public static class Authentication {

        /**
         * GitHub Basic authentication congigurations.
         */
        private Basic basic;

        /**
         * GitHub Oauth2 authentication configurations.
         */
        private OAuth2 oAuth2;

        public void setBasic(Basic basic) {
            this.basic = basic;
        }

        public Basic getBasic() {
            return basic;
        }

        public void setOAuth2(OAuth2 oAuth2) {
            this.oAuth2 = oAuth2;
        }

        public OAuth2 getOAuth2() {
            return oAuth2;
        }

        @SuppressWarnings("unused")
        public static class Basic {

            /**
             * GitHub username.
             */
            private String user;

            /**
             * GitHub user's password.
             */
            private String password;

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
        }

        @SuppressWarnings("unused")
        public static class OAuth2 {

            /**
             * GitHub OAuth2 token.
             */
            private String token;

            /**
             * GitHub OAuth2 key.
             */
            private String key;

            /**
             * GitHub OAuth2 secret.
             */
            private String secret;

            public void setKey(String key) {
                this.key = key;
            }

            public String getKey() {
                return key;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }
        }

    }
}
