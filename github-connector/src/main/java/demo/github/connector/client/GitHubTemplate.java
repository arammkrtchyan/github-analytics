package demo.github.connector.client;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import demo.github.connector.client.support.GitHubApiUserAgentInterceptor;
import demo.github.connector.client.support.GitHubApiVersionInterceptor;
import demo.github.connector.client.support.OAuth2AuthenticationInterceptor;
import demo.github.connector.config.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Synchronous client to perform HTTP requests to GitHub API. Template
 * method API over underlying {@link RestTemplate}.
 *
 * @author Aram Mkrtchyan.
 */
public class GitHubTemplate {

    private Configuration configuration;

    private RestTemplate restTemplate;

    /**
     * Create a new instance of the {@link GitHubTemplate} using provided configurations and {@link RestTemplate}.
     */
    public GitHubTemplate(Configuration configuration, RestTemplate restTemplate) {
        this.configuration = configuration;
        this.restTemplate = restTemplate;
    }

    /**
     * Create a new instance of the {@link GitHubTemplate} using provided configurations
     * and with default {@link RestTemplate}.
     */
    public GitHubTemplate(Configuration configuration) {
        this.configuration = configuration;
        this.restTemplate = defaultRestTemplate();
    }


    /**
     * Retrieve GitHub resource  by doing a GET request with specified path.
     * The response is converted and stored in an {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param path the resource path
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the entity
     */
    public <T> ResponseEntity<T> getForEntity(String path, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplate.getForEntity(apiUrl(path), responseType, uriVariables);
    }


    /**
     * Retrieve GitHub resource  by doing a GET request with specified path .
     * The response is converted and stored in an {@link ResponseEntity}.
     * @param path the resource path
     * @param responseType the type of the return value
     * @return the converted object
     */
    public <T> ResponseEntity<T> getForEntity(String path, Class<T> responseType) throws RestClientException {
        return restTemplate.getForEntity(apiUrl(path), responseType);
    }

    /**
     * Execute the HTTP method to the given GitHub endpoint, writing the given
     * request entity to the request, and returns the response as {@link ResponseEntity}.
     * The given {@link ParameterizedTypeReference} is used to pass generic type information:
     * <pre class="code">
     * ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt; myBean =
     *     new ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt;() {};
     *
     * ResponseEntity&lt;List&lt;MyBean&gt;&gt; response =
     *     template.exchange(&quot;http://example.com&quot;,HttpMethod.GET, null, myBean);
     * </pre>
     * @param path the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestEntity the entity (headers and/or body) to write to the
     * request (may be {@code null})
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand in the template
     * @return the response as entity
     * @since 3.2
     */
    public <T> ResponseEntity<T> exchange(String path, HttpMethod method, @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplate.exchange(apiUrl(path), method, requestEntity, responseType, uriVariables);
    }

    private String apiUrl(String path) {
        return "https://api.github.com" + path;
    }

    private RestTemplate defaultRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(new GitHubApiVersionInterceptor());
        interceptors.add(new GitHubApiUserAgentInterceptor(configuration));

        Optional.ofNullable(configuration.getBasicAuthCredentials()).ifPresent(credentials -> {
            interceptors.add(
                    new BasicAuthorizationInterceptor(credentials.getUsername(), credentials.getPassword())
            );
        });

        Optional.ofNullable(configuration.getOAuth2()).ifPresent(credentials -> {
            interceptors.add(
                    new OAuth2AuthenticationInterceptor(credentials.getToken())
            );
        });

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(
                new MappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder.json()
                        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .modules(
                                new JavaTimeModule().addDeserializer(LocalDateTime.class,
                                        new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME)
                                )
                        ).build())
        );
        messageConverters.addAll(restTemplate.getMessageConverters());
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }
}
