package demo.github.connector.domain.model.repository.commit;

import demo.github.connector.domain.model.common.GitHubEntity;

/**
 * Holds commit's sha, commit and author details.
 *
 * @author Aram Mkrtchyan.
 */
public class CommitSha extends GitHubEntity {

    private String sha;

    private Commit commit;

    private CommitShaAuthor author;

    /**
     * The commit details.
     */
    public Commit getCommit() {
        return commit;
    }

    /**
     * The SHA of the commit.
     */
    public String getSha() {
        return sha;
    }

    public CommitShaAuthor getAuthor() {
        return author;
    }
}
