package demo.github.analytics.web.pages.index;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import demo.github.analytics.web.component.common.AjaxEventHandler;
import demo.github.analytics.web.domain.model.repository.RepositorySearchResult;
import demo.github.analytics.web.pages.common.BasePage;

import demo.github.analytics.web.component.search.SearchForm;
import demo.github.analytics.web.component.search.SearchResultListPanel;
import demo.github.connector.client.common.SearchResult;
import demo.github.connector.domain.model.repository.Repository;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author Aram Mkrtchyan.
 */
@WicketHomePage
@MountPath("index")
public class IndexPage extends BasePage {

    private SearchResultListPanel searchResultPanel;

    public IndexPage(PageParameters pageParameters) {
        super(pageParameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        searchResultPanel = new SearchResultListPanel("searchResult");
        add(searchResultPanel.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true));
        addSearchForm();
        add(new BookmarkablePageLink<String>("homePageLink", IndexPage.class));
    }

    private void addSearchForm() {
        SearchForm searchForm = new SearchForm("searchForm");
        searchForm.setOnSubmit(onSearchFromSubmit());
        add(searchForm);
    }

    private AjaxEventHandler onSearchFromSubmit() {
        return new AjaxEventHandler() {
            @Override
            public void handle(AjaxRequestTarget target, RepositorySearchResult searchResult) {
                searchResultPanel.setSearchResult(searchResult);
                target.add(searchResultPanel);
            }
        };
    }

}
