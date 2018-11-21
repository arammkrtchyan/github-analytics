package demo.github.analytics.web.component.contributor;

import demo.github.connector.domain.model.repository.contributor.Contributor;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Aram Mkrtchyan.
 */
class ContributorDetailComponent extends Panel {

    ContributorDetailComponent(String id, Contributor contributor, int order) {
        super(id);

        add(new ExternalImage("avatar", contributor.getAvatarUrl()));
        add(new ExternalLink("loginLink", contributor.getHtmlUrl(), contributor.getLogin()));
        add(new Label("commits", formatCommitNumberLabel(contributor.getContributions())));
        add(new Label("orderNumber", formatOrder(order)));
    }


    private String formatCommitNumberLabel(long commits) {
        return String.format("%d commits", commits);
    }

    private String formatOrder(int order) {
        return String.format("#%d", order);
    }
}
