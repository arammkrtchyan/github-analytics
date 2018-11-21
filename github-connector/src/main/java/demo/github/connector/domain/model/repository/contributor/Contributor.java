package demo.github.connector.domain.model.repository.contributor;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.github.connector.domain.model.common.GitHubEntity;

/**
 * Represent GitHub contributor.
 *
 * @author Aram Mkrtchyan.
 */
public class Contributor extends GitHubEntity {

    private String login;

    private String type;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    private long contributions;

    /**
     * Returns the login of the contributor.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns the type of the contributor.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the avatar URL of the contributor.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Returns contributions count.
     */
    public long getContributions() {
        return contributions;
    }
}
