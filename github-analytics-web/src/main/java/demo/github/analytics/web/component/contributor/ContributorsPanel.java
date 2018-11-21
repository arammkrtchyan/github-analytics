package demo.github.analytics.web.component.contributor;

import demo.github.connector.domain.model.repository.contributor.Contributor;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.List;

/**
 * @author Aram Mkrtchyan.
 */
public class ContributorsPanel extends Panel {

    private List<Contributor> contributors;

    public ContributorsPanel(String id, List<Contributor> contributors) {
        super(id);
        this.contributors = contributors;
        addContributors();
    }

    private void addContributors() {
        RepeatingView contributorRepeatingView = new RepeatingView("contributors");
        for (int index = 0; index < contributors.size(); index++) {
            Contributor contributor = contributors.get(index);
            ContributorDetailComponent contributorDetail = new ContributorDetailComponent(contributorRepeatingView.newChildId(),
                    contributor, index + 1);
            contributorRepeatingView.add(contributorDetail);
        }
        add(contributorRepeatingView);

    }
}
