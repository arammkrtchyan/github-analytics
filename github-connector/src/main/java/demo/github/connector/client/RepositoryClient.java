package demo.github.connector.client;

import demo.github.connector.client.common.PageRequest;
import demo.github.connector.client.common.SearchResult;
import demo.github.connector.domain.model.repository.commit.CommitSha;
import demo.github.connector.domain.model.repository.contributor.Contributor;
import demo.github.connector.domain.model.repository.Repository;

import java.util.List;

/**
 * @author Aram Mkrtchyan.
 */
public interface RepositoryClient {

    /**
     * Finds and returns repositories via various criteria.
     *
     * @param query the search keywords, as well as any qualifiers.
     * @return search result.
     * @see <a href="https://developer.github.com/v3/search/#search-repositories">
     * Search repositories
     * </a>
     */
    SearchResult<Repository> search(String query);


    /**
     * Finds and returns paginated list of repositories via various criteria.
     * <p>
     * Please see {@link  }
     *
     * @param query the search keywords, as well as any qualifiers.
     * @return paginated list of repositories.
     * @see <a href="https://developer.github.com/v3/search/#search-repositories">
     * Search repositories
     * </a>
     */
    SearchResult<Repository> search(String query, PageRequest pageRequest);

    /**
     * List contributors of the repository.
     *
     * @param owner       the repo owner.
     * @param repo        the name of the repo.
     * @param pageRequest the page and size information.
     * @return the list of contributors of the specified repository.
     * @see <a href="https://developer.github.com/v3/repos/#list-contributors">
     * List contributors
     * </a>
     */
    List<Contributor> repositoryContributors(String owner, String repo, PageRequest pageRequest);

    /**
     * List commits on the repository.
     *
     * @param owner       the repo owner.
     * @param repo        the name of the repo.
     * @param branch      the branch name.
     * @param pageRequest the page and size information.
     * @return the list of commits on the specified repository.
     * @see <a href="https://developer.github.com/v3/repos/commits/#list-commits-on-a-repository">
     * List commits on a repository
     * </a>
     */
    List<CommitSha> commits(String owner, String repo, String branch, PageRequest pageRequest);

    /**
     * List commits on the repository's master branch.
     *
     * @param owner       the repo owner.
     * @param repo        the name of the repo.
     * @param pageRequest the page and size information.
     * @return the list of commits on the specified repository.
     * @see <a href="https://developer.github.com/v3/repos/commits/#list-commits-on-a-repository">
     * List commits on a repository
     * </a>
     */
    List<CommitSha> commits(String owner, String repo, PageRequest pageRequest);

    /**
     * Retrieves repository by full name.
     * Full name is the combination of owner and repo name like lqury/jquery.
     *
     * @param fullName the full name.
     * @return repository with specified name.
     */
    Repository get(String fullName);

}
