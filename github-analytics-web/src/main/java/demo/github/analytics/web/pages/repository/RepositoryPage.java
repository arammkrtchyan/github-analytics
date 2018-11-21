package demo.github.analytics.web.pages.repository;

import demo.github.analytics.web.domain.model.repository.RepositoryService;
import demo.github.analytics.web.pages.common.BasePage;
import demo.github.connector.domain.model.repository.Repository;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.annotation.mount.MountPath;


/**
 * @author Aram Mkrtchyan.
 */
@MountPath("repository")
public class RepositoryPage extends BasePage {

    @SpringBean
    private RepositoryService repositoryService;

    public RepositoryPage(PageParameters params) {
        super(params);
        String repoName = getRepositoryName(params);
        Repository repository = repositoryService.get(repoName);
        add(new RepositoryPageContent("content", repository));
    }

    private String getRepositoryName(PageParameters params) {
        StringValue repo = params.get("repo");
        return repo.toString();
    }


}
