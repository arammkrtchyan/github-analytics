package demo.github.connector.domain.model.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Used to hold summary information of repository, ike total
 * number of stargazers, open issues, watchers, ...
 *
 * @author Aram Mkrtchyan.
 */
public class RepositorySummary implements Serializable {

    @JsonProperty("stargazers_count")
    private long stargazers;

    @JsonProperty("forks")
    private long forks;

    @JsonProperty("open_issues")
    private long openIssues;

    @JsonProperty("watchers")
    private long watchers;

    /**
     * Returns total number of stargazers.
     */
    public long getStargazers() {
        return stargazers;
    }

    /**
     * Returns total number of forks.
     */
    public long getForks() {
        return forks;
    }

    /**
     * Returns total number of open issues.
     */
    public long getOpenIssues() {
        return openIssues;
    }

    /**
     * Returns total number of watchers.
     */
    public long getWatchers() {
        return watchers;
    }
}
