package demo.github.analytics.web.component.search;

import demo.github.analytics.web.domain.model.repository.RepositoryService;
import demo.github.connector.domain.model.repository.Repository;
import org.apache.wicket.ajax.AjaxPreventSubmitBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteTextRenderer;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Aram Mkrtchyan.
 */
public class RepositorySearchTextField extends AutoCompleteTextField<Repository> {

    @SpringBean
    private RepositoryService repositoryService;

    /**
     * Set of repository names appeared in autocomplete panel.
     */
    private Set<String> repositoryNames = new HashSet<>();

    RepositorySearchTextField(String id, AutoCompleteSettings autoCompleteSettings) {
        super(id, Model.of(),null, renderer(), autoCompleteSettings);
        add(new AjaxPreventSubmitBehavior());

        add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                String repo = String.valueOf(RepositorySearchTextField.this.getModel().getObject());
                // if selected on of suggested items, then navigate to the repo's page.
                // otherwise do nothing.
                if (repositoryNames.contains(repo)) {
                    target.appendJavaScript(generateRedirectScript(repo));
                }
            }
        });
    }

    private String generateRedirectScript(String repo) {
        String contextPath = ((WebApplication) getApplication()).getServletContext().getContextPath();
        String url = String.format("%s/repository?repo=%s", contextPath, repo);
        return "setTimeout(\"window.location.href='" + url + "'\", 100);";
    }

    @Override
    protected Iterator<Repository> getChoices(String query) {
        return doSearch(query)
                .iterator();
    }

    private List<Repository> doSearch(String query) {
        List<Repository> repositories = repositoryService.search(query, 5)
                .getRepositories();
        setRepositoryNames(repositories);

        return repositories;
    }

    private void setRepositoryNames(List<Repository> repositories) {
        repositoryNames = repositories.stream().map(repo ->
                Optional.ofNullable(repo.getFullName())
                        .orElseGet(() -> repo.getOwner().getLogin() + "/" + repo.getName())
        ).collect(Collectors.toSet());
    }

    private static IAutoCompleteRenderer<Repository> renderer() {
        return new AbstractAutoCompleteTextRenderer<Repository>() {

            @Override
            protected String getTextValue(Repository repository) {
                return repository.getFullName();
            }
        };
    }
}
