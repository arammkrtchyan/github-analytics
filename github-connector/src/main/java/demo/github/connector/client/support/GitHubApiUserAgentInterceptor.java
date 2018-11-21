package demo.github.connector.client.support;

import demo.github.connector.config.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Per GitHub API documentation
 * <p>
 * <cite>
 * All API requests MUST include a valid User-Agent header.
 * Requests with no User-Agent header will be rejected. We request that you use your GitHub username,
 * or the name of your application
 * </cite>
 * <p>
 * <p>
 * This interceptor will set intercept all requests to Github API and will set {@code User-Agent} header tp provided value.
 *
 * @author Aram Mkrtchyan.
 */
public class GitHubApiUserAgentInterceptor implements ClientHttpRequestInterceptor {

    private Configuration configuration;

    public GitHubApiUserAgentInterceptor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.set("User-Agent", configuration.getUserAgent());
        return execution.execute(request, body);
    }

}
