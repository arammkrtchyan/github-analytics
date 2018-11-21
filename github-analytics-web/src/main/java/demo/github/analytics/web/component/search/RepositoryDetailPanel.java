package demo.github.analytics.web.component.search;

import demo.github.analytics.web.pages.repository.RepositoryPage;
import demo.github.connector.domain.model.repository.License;
import demo.github.connector.domain.model.repository.Repository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Aram Mkrtchyan.
 */
public class RepositoryDetailPanel extends Panel {

    private Repository repository;

    RepositoryDetailPanel(String id, Repository repository) {
        super(id);
        this.repository = repository;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        addComponents();
    }

    private void addComponents() {
        addNameLink();
        addTextField("description", repository.getDescription());
        addTextField("license", Optional.ofNullable(repository.getLicense()).map(License::getName).orElse("No License"));
        addTextField("language", repository.getLanguage());
        addTextField("stargazers", repository.getSummary().getStargazers());
        addTextField("issues", repository.getSummary().getOpenIssues());
    }

    private <T extends Serializable> void addTextField(String id, T value) {
        addOrReplace(new Label(id, new Model<T>(value)));
    }

    private void addNameLink() {
        PageParameters pageParameters = new PageParameters();
        pageParameters.set("repo", repository.getFullName());
        BookmarkablePageLink<Repository> nameLink = new BookmarkablePageLink<>("nameLink",
                RepositoryPage.class, pageParameters);
        nameLink.addOrReplace(new Label("name", Model.of(repository.getFullName())));
        addOrReplace(nameLink);
    }
}
