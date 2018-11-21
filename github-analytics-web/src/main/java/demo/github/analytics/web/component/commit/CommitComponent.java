package demo.github.analytics.web.component.commit;

import demo.github.analytics.web.domain.model.commit.Commit;
import demo.github.analytics.web.domain.model.commit.CommitAuthor;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.springframework.util.StringUtils;

/**
 * @author Aram Mkrtchyan.
 */
class CommitComponent extends Panel {

    CommitComponent(String id, Commit commit, String groupDateTitle) {
        super(id);

        add(new ExternalLink("commit-message-link", commit.getHtmlUrl(), commit.getMessage()));

        CommitAuthor author = commit.getAuthor();
        addAvatar(author);
        add(new ExternalLink("committer", author.getHtmlUrl(), author.getDisplayName()));

        add(new Label("commit-date", groupDateTitle));
    }

    private void addAvatar(CommitAuthor author) {
        String url = author.getAvatarUrl();
        if (StringUtils.hasText(url)) {
            add(new ExternalImage("committer-avatar", author.getAvatarUrl()));
        } else {
            add(new ContextImage("committer-avatar", "images/missing-avatar.png"));
        }
    }

}
