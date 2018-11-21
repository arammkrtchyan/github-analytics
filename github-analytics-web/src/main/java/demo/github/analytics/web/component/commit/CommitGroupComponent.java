package demo.github.analytics.web.component.commit;

import demo.github.analytics.web.domain.model.commit.Commit;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.List;

/**
 * @author Aram Mkrtchyan.
 */
class CommitGroupComponent extends Panel {


    CommitGroupComponent(String id, String groupDateTitle, List<Commit> commits) {
        super(id);
        add(new Label("group-date", groupDateTitle));
        RepeatingView commitsRepeatingView = new RepeatingView("group-commits");
        commits.forEach(commit -> {
            commitsRepeatingView.add(new CommitComponent(
                    commitsRepeatingView.newChildId(),
                    commit,
                    groupDateTitle
            ));
        });
        add(commitsRepeatingView);
    }


}
