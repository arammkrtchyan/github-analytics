package demo.github.connector.domain.model.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import demo.github.connector.domain.model.common.GitHubEntity;

/**
 * Represents GitHub Repository.
 *
 * @author Aram Mkrtchyan.
 */
public class Repository extends GitHubEntity {

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private boolean fork;

    @JsonProperty("private")
    private boolean isPrivate;

    @JsonProperty
    private String description;

    private Owner owner;

    private String language;

    @JsonProperty("git_url")
    private String gitUrl;

    @JsonProperty("ssh_url")
    private String sshUrl;

    @JsonProperty("clone_url")
    private String cloneUrl;

    private boolean archived;

    @JsonProperty("has_issues")
    private boolean hasIssues;

    @JsonProperty("has_projects")
    private boolean hasProjects;

    @JsonProperty("has_downloads")
    private boolean hasDownloads;

    @JsonProperty("has_wiki")
    private boolean hasWiki;

    @JsonProperty("has_pages")
    private boolean hasPages;

    @JsonProperty("default_branch")
    private String defaultBranch;

    @JsonProperty("license")
    private License license;

    @JsonUnwrapped
    private RepositorySummary summary;

    /**
     * Returns repository name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the full name of repository.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Specifies whether repository is private or not.
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Returns the description of repository.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the owner of repository.
     * <p>
     *
     * @see Owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Specifies whether repository is forked or not.
     */
    public boolean isFork() {
        return fork;
    }

    /**
     * Specifies whether repository is archived or not.
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * Returns the clone URL of repository
     */
    public String getCloneUrl() {
        return cloneUrl;
    }

    /**
     * Returns repository's default branch.
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }

    /**
     * Returns repository's git URL.
     */
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     * Returns repository's SSH URL.
     */
    public String getSshUrl() {
        return sshUrl;
    }

    /**
     * Returns repo summary.
     *
     * @see RepositorySummary
     */
    public RepositorySummary getSummary() {
        return summary;
    }

    /**
     * Specifies whether repository has downloads or not.
     */
    public boolean hasDownloads() {
        return hasDownloads;
    }

    /**
     * Specifies whether repository has issues or not.
     */
    public boolean hasIssues() {
        return hasIssues;
    }

    /**
     * Specifies whether repository has pages or not.
     */
    public boolean hasPages() {
        return hasPages;
    }

    /**
     * Specifies whether repository has projects or not.
     */
    public boolean hasProjects() {
        return hasProjects;
    }

    /**
     * Specifies whether repository has wiki or not.
     */
    public boolean hasWiki() {
        return hasWiki;
    }

    /**
     * Returns specified programming language of the repository.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the license of repository.
     *
     * @see License
     */
    public License getLicense() {
        return license;
    }
}
