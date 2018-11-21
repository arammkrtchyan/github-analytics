package demo.github.analytics.web.component.search;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.Set;

/**
 * @author Aram Mkrtchyan.
 */
class LanguagesContainer extends Panel {

    LanguagesContainer(String id, Set<String> languageNames) {
        super(id);
        addLanguages(languageNames);
    }

    private void addLanguages(Set<String> languages) {
        RepeatingView languagesView = new RepeatingView("languages");

        languages.forEach(language -> {
            languagesView.add(new Label(languagesView.newChildId(), language));
        });
        add(languagesView);

    }


}
