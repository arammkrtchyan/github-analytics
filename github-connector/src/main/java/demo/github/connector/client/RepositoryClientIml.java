package demo.github.connector.client;

import demo.github.connector.client.common.PageRequest;
import demo.github.connector.client.common.SearchResult;
import demo.github.connector.domain.model.repository.Repository;
import demo.github.connector.domain.model.repository.commit.CommitSha;
import demo.github.connector.domain.model.repository.contributor.Contributor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Basic implementation of {@link RepositoryClient}.
 *
 * @author Aram Mkrtchyan.
 */
public class RepositoryClientIml implements RepositoryClient {

    private GitHubTemplate gitHubTemplate;

    public RepositoryClientIml(GitHubTemplate gitHubTemplate) {
        this.gitHubTemplate = gitHubTemplate;
    }

    @Override
    public SearchResult<Repository> search(String query) {
        return gitHubTemplate.exchange(
                "/search/repositories?query={query}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<SearchResult<Repository>>() {
                },
                query
        ).getBody();
    }

    @Override
    public SearchResult<Repository> search(String query, PageRequest pageRequest) {
        return gitHubTemplate.exchange(
                "/search/repositories?q={query}&page={page}&per_page={pageSize}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<SearchResult<Repository>>() {
                },
                query,
                pageRequest.getPage(),
                pageRequest.getPageSize()
        ).getBody();
    }

    @Override
    public List<Contributor> repositoryContributors(String owner, String repo, PageRequest pageRequest) {
        return gitHubTemplate.exchange(
                "/repos/{owner}/{repo}/contributors?per_page={pageSize}&page={page}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Contributor>>() {
                },
                owner, repo, pageRequest.getPageSize(), pageRequest.getPage()
        ).getBody();
    }

    @Override
    public List<CommitSha> commits(String owner, String repo, String branch, PageRequest pageRequest) {
        return gitHubTemplate.exchange(
                "/repos/{owner}/{repo}/commits?per_page={pageSize}&page={page}&sha={branch}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CommitSha>>() {
                },
                owner, repo, pageRequest.getPageSize(), pageRequest.getPage(), branch
        ).getBody();
    }

    @Override
    public List<CommitSha> commits(String owner, String repo, PageRequest pageRequest) {
        return commits(owner, repo, "master", pageRequest);
    }

    @Override
    public Repository get(String repo) {
        return gitHubTemplate.getForEntity("/repos/" + repo,
                Repository.class).getBody();
    }
}
