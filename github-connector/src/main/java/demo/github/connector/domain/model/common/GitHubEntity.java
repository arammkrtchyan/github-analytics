package demo.github.connector.domain.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents common GitHub entity properties.
 * THe purpose of this to use inheritance and avoid code duplications.
 *
 * @author Aram Mkrtchyan.
 */
public class GitHubEntity implements Serializable {

    private Long id;

    @JsonProperty("html_url")
    private String htmlUrl;

    /**
     * Html url of thw current resource which will onk to GitHub.
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Id of the entity.
     */
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitHubEntity that = (GitHubEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
