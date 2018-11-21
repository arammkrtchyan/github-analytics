package demo.github.analytics.web.component.common;

import demo.github.analytics.web.domain.model.repository.RepositorySearchResult;
import org.apache.wicket.ajax.AjaxRequestTarget;

import java.io.Serializable;

/**
 * @author Aram Mkrtchyan.
 */
public interface AjaxEventHandler extends Serializable {

    void handle(AjaxRequestTarget target, RepositorySearchResult searchResult);

}
