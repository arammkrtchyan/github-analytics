package demo.github.connector.domain.model.repository.commit;

import java.io.Serializable;

/**
 * GitHub commit representation.
 *
 * @author Aram Mkrtchyan.
 */
public class Commit implements Serializable {

    private String message;

    private CommitAuthor author;

    /**
     * Returns commit message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the author of commit.
     *
     * @see CommitAuthor
     */
    public CommitAuthor getAuthor() {
        return author;
    }
}
