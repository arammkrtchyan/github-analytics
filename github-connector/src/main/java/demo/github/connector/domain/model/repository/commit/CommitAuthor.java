package demo.github.connector.domain.model.repository.commit;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * The author of commit.
 *
 * @author Aram Mkrtchyan.
 */
public class CommitAuthor implements Serializable {

    private String name;

    private String email;

    private ZonedDateTime date;

    /**
     * The name of the author.
     */
    public String getName() {
        return name;
    }

    /**
     * The email of the author.
     */
    public String getEmail() {
        return email;
    }

    /**
     * The commit date.
     */
    public ZonedDateTime getDate() {
        return date;
    }
}
