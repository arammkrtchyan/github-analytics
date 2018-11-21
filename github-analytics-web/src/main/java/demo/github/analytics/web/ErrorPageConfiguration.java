package demo.github.analytics.web;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import demo.github.analytics.web.pages.error.GitHubErrorPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Aram Mkrtchyan.
 */
@ApplicationInitExtension
public class ErrorPageConfiguration implements WicketApplicationInitConfiguration {
    @Override
    public void init(WebApplication webApplication) {
        webApplication.getApplicationSettings()
                .setInternalErrorPage(GitHubErrorPage.class);
    }
}
