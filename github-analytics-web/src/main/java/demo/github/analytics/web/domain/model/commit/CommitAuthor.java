package demo.github.analytics.web.domain.model.commit;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Aram Mkrtchyan.
 */
public class CommitAuthor implements Serializable {

    private String login;

    private String avatarUrl;

    private String name;

    private String email;

    private String htmlUrl;

    private String generatedIdentifier;

    CommitAuthor(
            String login,
            String avatarUrl,
            String name,
            String email,
            String htmlUrl) {
        validate(name, login);
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.email = Objects.requireNonNull(email, "Email can not be null.");
        this.htmlUrl = htmlUrl;
        generatedIdentifier = Optional.ofNullable(login).orElse(email);
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getEmail() {
        return email;
    }

    private void validate(String name, String login) {
        if (name == null && login == null) {
            throw new IllegalArgumentException("Either name or login should be specified");
        }
    }

    public String getDisplayName() {
        return Optional.ofNullable(getLogin()).orElseGet(this::getName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitAuthor that = (CommitAuthor) o;
        return Objects.equals(generatedIdentifier, that.generatedIdentifier);
    }

    @Override
    public int hashCode() {

        return Objects.hash(generatedIdentifier);
    }
}
