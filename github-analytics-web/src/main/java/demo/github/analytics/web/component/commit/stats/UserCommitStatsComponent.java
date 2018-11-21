package demo.github.analytics.web.component.commit.stats;

import demo.github.analytics.web.domain.model.commit.CommitAuthor;
import demo.github.analytics.web.domain.model.commit.UserCommitStats;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.springframework.util.StringUtils;

/**
 * @author Aram Mkrtchyan.
 */
class UserCommitStatsComponent extends Panel {

    UserCommitStatsComponent(String id, UserCommitStats stats) {
        super(id);
        CommitAuthor author = stats.getAuthor();
        addAvatar(author);
        addLoginLink(author);
        addCommitStatsLabel(stats);
    }

    private void addAvatar(CommitAuthor author) {
        String url = author.getAvatarUrl();
        if (StringUtils.hasText(url)) {
            add(new ExternalImage("avatar", author.getAvatarUrl()));
        } else {
            add(new ContextImage("avatar", "images/missing-avatar.png"));
        }
    }

    private void addLoginLink(CommitAuthor author) {
        add(new ExternalLink("login", author.getHtmlUrl(), author.getDisplayName()));
    }

    private void addCommitStatsLabel(UserCommitStats stats) {
        add(new Label("commits", stats.getCommits()));
    }

}
