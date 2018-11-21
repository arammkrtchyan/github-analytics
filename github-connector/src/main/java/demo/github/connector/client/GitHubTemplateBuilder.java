package demo.github.connector.client;


import demo.github.connector.config.BasicAuthCredentials;
import demo.github.connector.config.Configuration;
import demo.github.connector.config.OAuth2Credentials;

import java.util.TimeZone;

/**
 * Class for building GitHubTemplate with authentication, timezone,
 * and user agent properties.
 *
 * @author Aram Mkrtchyan.
 */
public class GitHubTemplateBuilder {

    private Configuration configuration = new Configuration();

    public GitHubTemplate build() {
        return new GitHubTemplate(configuration);
    }

    public GitHubTemplateBuilder withBasicAuthentication(BasicAuthCredentials credentials) {
        configuration.setBasicAuthentication(credentials);
        return this;
    }

    public GitHubTemplateBuilder withOauth2Authentication(OAuth2Credentials credentials) {
        configuration.setOAuth2(credentials);
        return this;
    }

    public GitHubTemplateBuilder withTimeZone(TimeZone timeZone) {
        configuration.setTimeZone(timeZone);
        return this;
    }

    public GitHubTemplateBuilder withUserAgent(String userAgent) {
        configuration.setUserAgent(userAgent);
        return this;
    }

}
