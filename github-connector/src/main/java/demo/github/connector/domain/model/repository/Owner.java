package demo.github.connector.domain.model.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.github.connector.domain.model.common.GitHubEntity;

/**
 * Represention of the repository owner.
 *
 * @author Aram Mkrtchyan.
 */
public class Owner extends GitHubEntity {

    private String login;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    private String type;

    /**
     * Returns owner type: Organisation, User.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns avatar URL of the owner.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Returns login of the owner.
     */
    public String getLogin() {
        return login;
    }
}
