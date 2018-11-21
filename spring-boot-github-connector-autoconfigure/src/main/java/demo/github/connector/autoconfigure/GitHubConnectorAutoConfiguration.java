package demo.github.connector.autoconfigure;

import demo.github.connector.client.GitHubTemplate;
import demo.github.connector.client.RepositoryClient;
import demo.github.connector.client.RepositoryClientIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * {@link EnableAutoConfiguration Auto-configuration} for {@link GitHubTemplate}.
 *
 * @author Aram Mkrtchyan.
 */
@Configuration
@EnableConfigurationProperties(GitHubConnectorProperties.class)
@ConditionalOnClass(GitHubTemplate.class)
public class GitHubConnectorAutoConfiguration {

    private final GitHubConnectorProperties connectorProperties;

    @Autowired
    public GitHubConnectorAutoConfiguration(GitHubConnectorProperties connectorProperties) {
        this.connectorProperties = connectorProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public GitHubTemplate gitHubTemplate() {
        return connectorProperties.initializeGitHubTemplateBuilder()
                .build();
    }

    @Bean
    RepositoryClient repositoryClient() {
        return new RepositoryClientIml(gitHubTemplate());
    }

}
