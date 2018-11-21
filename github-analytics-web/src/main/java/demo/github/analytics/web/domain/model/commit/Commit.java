package demo.github.analytics.web.domain.model.commit;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Aram Mkrtchyan.
 */
public class Commit implements Serializable {

    private String message;

    private CommitAuthor author;

    private String htmlUrl;

    private ZonedDateTime date;

    public Commit(
            String message,
            CommitAuthor author,
            String htmlUrl,
            ZonedDateTime date) {
        this.message = message;
        this.author = author;
        this.htmlUrl = htmlUrl;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public CommitAuthor getAuthor() {
        return author;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public ZonedDateTime getDate() {
        return date;
    }
}
