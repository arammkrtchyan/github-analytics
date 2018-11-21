package demo.github.analytics.web.domain.model.commit;

import demo.github.connector.client.common.PageRequest;

import java.util.List;

/**
 * Domain service for pulling commits on repository.
 *
 * @author Aram Mkrtchyan.
 */
public interface CommitService {

    List<Commit> commits(String owner, String repo, String branch, PageRequest pageRequest);

}
