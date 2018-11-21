package demo.github.analytics.web.domain.model.commit;

/**
 * @author Aram Mkrtchyan.
 */
public class CommitAuthorBuilder {

    private String login;

    private String avatarUrl;

    private String name;

    private String email;

    private String htmlUrl;

    public CommitAuthorBuilder(String name) {
        this.name = name;
    }

    public CommitAuthorBuilder login(String login) {
        this.login = login;
        return this;
    }

    public CommitAuthorBuilder avatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public CommitAuthorBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CommitAuthorBuilder htmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    public CommitAuthor build() {
        return new CommitAuthor(
                login,
                avatarUrl,
                name,
                email,
                htmlUrl
        );
    }

}
