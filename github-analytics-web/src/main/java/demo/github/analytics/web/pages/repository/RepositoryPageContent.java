package demo.github.analytics.web.pages.repository;

import demo.github.analytics.web.component.tab.AjaxTwitterBootstrapTabbedPanel;
import demo.github.analytics.web.domain.model.commit.Commit;
import demo.github.analytics.web.domain.model.commit.CommitService;
import demo.github.analytics.web.domain.model.commit.UserCommitStats;
import demo.github.analytics.web.component.commit.CommitTimelineComponent;
import demo.github.analytics.web.component.commit.stats.CommitStatsComponent;
import demo.github.analytics.web.component.contributor.ContributorsPanel;
import demo.github.analytics.web.application.service.UserCommitAnalyticsApplicationService;
import demo.github.analytics.web.domain.model.repository.RepositoryService;
import demo.github.connector.client.common.PageRequest;
import demo.github.connector.domain.model.repository.Repository;
import demo.github.connector.domain.model.repository.contributor.Contributor;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aram Mkrtchyan.
 */
class RepositoryPageContent extends Panel {

    private Repository repository;

    @SpringBean
    private RepositoryService repositoryService;

    @SpringBean
    private CommitService commitService;

    @SpringBean
    private UserCommitAnalyticsApplicationService userCommitAnalyticsApplicationService;

    RepositoryPageContent(String id, Repository repository) {
        super(id, Model.of(repository));
        this.repository = repository;
        addHeader();
        addTabbedPanel();
    }

    private void addHeader() {
        add(new ExternalLink("repoHtmlLink", repository.getHtmlUrl(), repository.getFullName()));
    }

    private void addTabbedPanel() {
        add(new AjaxTwitterBootstrapTabbedPanel<>(
                "tabs",
                Arrays.asList(commitsTab(), contributorsTab(), commitStatisticsTab())
        ));
    }

    private AbstractTab commitsTab() {
        List<Commit> commits = commitService.commits(repository.getOwner().getLogin(),
                repository.getName(), repository.getDefaultBranch(), new PageRequest(0, 100));
        return new AbstractTab(Model.of("Last 100 Commits")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new CommitTimelineComponent(panelId, commits);
            }
        };
    }

    private AbstractTab contributorsTab() {
        List<Contributor> contributors = repositoryService.contributors(
                repository.getOwner().getLogin(),
                repository.getName(),
                100
        );

        return new AbstractTab(Model.of("Contributors")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new ContributorsPanel(panelId, contributors);
            }
        };
    }

    private AbstractTab commitStatisticsTab() {
        List<UserCommitStats> commits = userCommitAnalyticsApplicationService.userCommitStats(
                repository.getOwner().getLogin(),
                repository.getName(),
                repository.getDefaultBranch());
        return new AbstractTab(Model.of("Last 100 Commit Statistics")) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new CommitStatsComponent(panelId, commits);
            }
        };
    }
}
