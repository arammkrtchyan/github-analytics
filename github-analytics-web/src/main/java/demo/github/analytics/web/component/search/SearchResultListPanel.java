package demo.github.analytics.web.component.search;

import demo.github.analytics.web.domain.model.repository.RepositorySearchResult;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.Collections;
import java.util.Set;

/**
 * @author Aram Mkrtchyan.
 */
public class SearchResultListPanel extends Panel {

    private RepositorySearchResult searchResult;

    public SearchResultListPanel(String id) {
        super(id);
        this.searchResult = RepositorySearchResult.empty();
    }

    public void setSearchResult(RepositorySearchResult searchResult) {
        this.searchResult = searchResult;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        addComponents();
    }

    private void addComponents() {
        addOrReplace(new Label("resultMessage", resultMessage()));

        RepeatingView repositories = new RepeatingView("repositories");
        searchResult.getRepositories().forEach(repository ->
                repositories.addOrReplace(new RepositoryDetailPanel(repositories.newChildId(), repository))
        );
        addOrReplace(repositories);

        Set<String> languageNames = searchResult.getLanguages();

        addOrReplace(new LanguagesContainer("languagesContainer", languageNames)
                .setVisible(!languageNames.isEmpty())
        );
    }

    private String resultMessage() {
        long totalCount = searchResult.getTotalCount();
        return totalCount > 0 ? String.format("%d repository results", totalCount)
                : "No repositories to show, please type or search";
    }

}
