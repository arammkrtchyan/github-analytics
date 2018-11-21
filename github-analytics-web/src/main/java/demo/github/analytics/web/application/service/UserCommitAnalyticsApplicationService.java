package demo.github.analytics.web.application.service;

import demo.github.analytics.web.domain.model.commit.Commit;
import demo.github.analytics.web.domain.model.commit.CommitService;
import demo.github.analytics.web.domain.model.commit.UserCommitStats;
import demo.github.connector.client.common.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingByConcurrent;

/**
 * @author Aram Mkrtchyan.
 */
@Component
public class UserCommitAnalyticsApplicationService {

    @Autowired
    private CommitService commitService;

    /**
     * Returns the sorted list of user commit stats based on latest 100 commits.
     */
    public List<UserCommitStats> userCommitStats(String owner, String repo, String branch) {
        PageRequest pageRequest = new PageRequest(0, 100);

        return commitService.commits(owner, repo, branch, pageRequest).stream()
                .collect(
                        groupingByConcurrent(Commit::getAuthor, Collectors.counting())
                ).entrySet().stream().map((e) ->
                        new UserCommitStats(e.getKey(), e.getValue())
                ).sorted(Comparator.comparingLong(UserCommitStats::getCommits).reversed())
                .collect(Collectors.toList());
    }

}
