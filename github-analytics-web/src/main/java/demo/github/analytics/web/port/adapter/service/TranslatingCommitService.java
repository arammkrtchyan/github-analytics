package demo.github.analytics.web.port.adapter.service;

import demo.github.analytics.web.domain.model.commit.Commit;
import demo.github.analytics.web.domain.model.commit.CommitService;
import demo.github.analytics.web.port.adapter.CommitTranslator;
import demo.github.connector.client.RepositoryClient;
import demo.github.connector.client.common.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link CommitService}, which will pull data from Github connector nd will translate
 * external domain to our internal.
 *
 * @author Aram Mkrtchyan.
 */
@Service
public class TranslatingCommitService implements CommitService {

    private final RepositoryClient repositoryClient;

    private final CommitTranslator commitTranslator;

    @Autowired
    public TranslatingCommitService(RepositoryClient repositoryClient, CommitTranslator commitTranslator) {
        this.repositoryClient = repositoryClient;
        this.commitTranslator = commitTranslator;
    }

    @Override
    public List<Commit> commits(String owner, String repo, String branch, PageRequest pageRequest) {
        if (StringUtils.hasText(branch)) {
            return repositoryClient.commits(owner, repo, branch, pageRequest).stream().map(commitTranslator::toCommit)
                    .collect(Collectors.toList());
        } else {
            return repositoryClient.commits(owner, repo, pageRequest).stream().map(commitTranslator::toCommit)
                    .collect(Collectors.toList());
        }

    }
}
