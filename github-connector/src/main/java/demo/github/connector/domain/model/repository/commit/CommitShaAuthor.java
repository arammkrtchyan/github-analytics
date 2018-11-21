package demo.github.connector.domain.model.repository.commit;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.github.connector.domain.model.common.GitHubEntity;

/**
 * @author Aram Mkrtchyan.
 */
public class CommitShaAuthor extends GitHubEntity {

    private String login;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
