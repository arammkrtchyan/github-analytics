package demo.github.analytics.web.component.search;

import demo.github.analytics.web.component.common.AjaxEventHandler;
import demo.github.analytics.web.domain.model.repository.RepositoryService;
import demo.github.connector.domain.model.repository.Repository;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;


/**
 * @author Aram Mkrtchyan.
 */
public class SearchForm extends Form<String> {

    @SpringBean
    private RepositoryService repositoryService;

    private AjaxEventHandler submitHandler;

    public SearchForm(String id) {
        super(id, new Model<>());
    }

    public void setOnSubmit(AjaxEventHandler submitHandler) {
        this.submitHandler = submitHandler;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(createAutoCompleteSearch());
    }

    private AutoCompleteTextField createAutoCompleteSearch() {
        AutoCompleteSettings autoCompleteSettings = new AutoCompleteSettings();
        autoCompleteSettings.setShowListOnFocusGain(true);

        AutoCompleteTextField<Repository> autoCompleteTextField = new RepositorySearchTextField(
                "searchInput", autoCompleteSettings);


        add(new IndicatingAjaxButton("searchButton") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                String query = String.valueOf(autoCompleteTextField.getModelObject());
                submitHandler.handle(target, repositoryService.search(query, 100));
            }

        });


        return autoCompleteTextField;
    }

}
