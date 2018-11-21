package demo.github.analytics.web.domain.model.commit;

import java.io.Serializable;

/**
 * Represents commit statistics of the user.
 *
 * @author Aram Mkrtchyan.
 */
public class UserCommitStats implements Serializable {

    private CommitAuthor author;

    private long commits;

    public UserCommitStats(
            CommitAuthor author,
            long commits) {
        this.author = author;
        this.commits = commits;
    }

    /**
     * Returns the author of commits.
     */
    public CommitAuthor getAuthor() {
        return author;
    }

    /**
     * Returns commits count.
     */
    public long getCommits() {
        return commits;
    }

}
