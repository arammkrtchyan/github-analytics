package demo.github.analytics.web.component.commit.stats;

import demo.github.analytics.web.domain.model.commit.UserCommitStats;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.List;

/**
 * @author Aram Mkrtchyan.
 */
public class CommitStatsComponent extends Panel {

    public CommitStatsComponent(String id, List<UserCommitStats> impacts) {
        super(id);

        RepeatingView impactsView = new RepeatingView("userCommitStats");
        impacts.forEach(impact -> {
            impactsView.add(new UserCommitStatsComponent(impactsView.newChildId(), impact));
        });
        add(impactsView);
    }

}
