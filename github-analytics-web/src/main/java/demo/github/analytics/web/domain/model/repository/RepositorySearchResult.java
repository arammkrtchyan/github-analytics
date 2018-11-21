package demo.github.analytics.web.domain.model.repository;

import demo.github.connector.domain.model.repository.Repository;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Aram Mkrtchyan.
 */
public class RepositorySearchResult implements Serializable {

    private long totalCount;

    private List<Repository> repositories;

    public RepositorySearchResult(long totalCount, List<Repository> repositories) {
        this.totalCount = totalCount;
        this.repositories = repositories;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public long getTotalCount() {
        return totalCount;
    }

    /**
     * Get all programming languages in founded repositories.
     */
    public Set<String> getLanguages() {
        return getRepositories().stream()
                .map(Repository::getLanguage)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public static RepositorySearchResult empty() {
        return new RepositorySearchResult(0, Collections.emptyList());
    }
}
