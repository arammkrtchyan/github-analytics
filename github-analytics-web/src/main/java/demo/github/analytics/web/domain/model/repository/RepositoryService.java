package demo.github.analytics.web.domain.model.repository;

import demo.github.connector.domain.model.repository.Repository;
import demo.github.connector.domain.model.repository.contributor.Contributor;

import java.util.List;

/**
 * @author Aram Mkrtchyan.
 */
public interface RepositoryService {

    /**
     * Search and return first {@code n} repositories.
     */
    RepositorySearchResult search(String query, int n);

    /**
     * Finds and returns repository by specified full name of the repository.
     * Full name is the combination of owner and repo name like lqury/jquery.
     */
    Repository get(String fullName);


    /**
     * Returns first {@code n} contributors of the repository.
     *
     * @param owner the repo owner.
     * @param repo  the name of the repo.
     * @param n     the limit.
     * @return first {@code n} contributors of the specified repository.
     */
    List<Contributor> contributors(String owner, String repo, int n);
}
