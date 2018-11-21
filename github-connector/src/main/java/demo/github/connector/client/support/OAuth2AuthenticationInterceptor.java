package demo.github.connector.client.support;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Intercepts all requests to GitHub API and sets {@code Authorization} token, in case if client
 * decided to use Oauth2 authentication.
 *
 * @author Aram Mkrtchyan.
 */
public class OAuth2AuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private String token;

    public OAuth2AuthenticationInterceptor(String token) {
        this.token = token;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        if (!headers.containsKey("Authorization")) {
            if (!StringUtils.isEmpty(token)) {
                headers.set("Authorization", "token " + token);
            }
        }

        return execution.execute(request, body);
    }

}
