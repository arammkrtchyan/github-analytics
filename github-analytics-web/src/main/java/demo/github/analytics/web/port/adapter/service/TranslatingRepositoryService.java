package demo.github.analytics.web.port.adapter.service;

import demo.github.analytics.web.domain.model.repository.RepositorySearchResult;
import demo.github.analytics.web.domain.model.repository.RepositoryService;
import demo.github.connector.client.RepositoryClient;
import demo.github.connector.client.common.PageRequest;
import demo.github.connector.client.common.SearchResult;
import demo.github.connector.domain.model.repository.Repository;
import demo.github.connector.domain.model.repository.contributor.Contributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Aram Mkrtchyan.
 */
@Service
public class TranslatingRepositoryService implements RepositoryService {

    @Autowired
    private RepositoryClient repositoryClient;

    @Override
    public RepositorySearchResult search(String query, int limit) {
        if (StringUtils.hasText(query)) {
            SearchResult<Repository> searchResult = repositoryClient
                    .search(query, new PageRequest(0, limit));
            return new RepositorySearchResult(searchResult.getTotalCount(), searchResult.getItems());
        } else {
            return RepositorySearchResult.empty();
        }
    }

    @Override
    public Repository get(String fullName) {
        return repositoryClient.get(fullName);
    }

    @Override
    public List<Contributor> contributors(String owner, String repo, int limit) {
        return repositoryClient.repositoryContributors(
                owner,
                repo,
                new PageRequest(0, limit)
        );
    }
}
