package demo.github.connector.config;

import java.util.TimeZone;

/**
 * Class for defining GitHubConnector configuration.
 *
 * @author Aram Mkrtchyan.
 */
public class Configuration {

    private BasicAuthCredentials basicAuthCredentials;

    private OAuth2Credentials oauth2;

    private TimeZone timeZone;

    private String userAgent;

    public BasicAuthCredentials getBasicAuthCredentials() {
        return basicAuthCredentials;
    }

    public void setBasicAuthentication(BasicAuthCredentials basicAuthCredentials) {
        this.basicAuthCredentials = basicAuthCredentials;
    }

    public OAuth2Credentials getOAuth2() {
        return oauth2;
    }

    public void setOAuth2(OAuth2Credentials oauth2) {
        this.oauth2 = oauth2;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
