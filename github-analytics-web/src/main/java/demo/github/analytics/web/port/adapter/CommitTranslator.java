package demo.github.analytics.web.port.adapter;

import demo.github.analytics.web.domain.model.commit.Commit;
import demo.github.analytics.web.domain.model.commit.CommitAuthorBuilder;
import demo.github.connector.domain.model.repository.commit.CommitSha;
import demo.github.connector.domain.model.repository.commit.CommitShaAuthor;
import org.springframework.stereotype.Component;

/**
 * Translates external commit domain to our's.
 *
 * @author Aram Mkrtchyan.
 */
@Component
public class CommitTranslator {

    public Commit toCommit(CommitSha sha) {
        CommitShaAuthor shaAuthor = sha.getAuthor();
        CommitAuthorBuilder commitAuthorBuilder = new CommitAuthorBuilder(sha.getCommit().getAuthor().getName())
                .email(sha.getCommit().getAuthor().getEmail());

        if (shaAuthor != null) {
            commitAuthorBuilder.avatarUrl(shaAuthor.getAvatarUrl())
                    .htmlUrl(shaAuthor.getHtmlUrl())
                    .login(shaAuthor.getLogin());
        }

        return new Commit(
                sha.getCommit().getMessage(),
                commitAuthorBuilder.build(),
                sha.getHtmlUrl(),
                sha.getCommit().getAuthor().getDate()
        );
    }

}
