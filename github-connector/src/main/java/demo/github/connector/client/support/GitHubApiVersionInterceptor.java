package demo.github.connector.client.support;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Intercepts all request to GitHub API and will set api version to request header.
 *
 * @author Aram Mkrtchyan.
 */
public class GitHubApiVersionInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        // Currently we will support only v3 version of GitHub Api. version can be configurable.
        headers.set("Accept", "application/vnd.github.v3+json");
        return execution.execute(request, body);
    }

}
