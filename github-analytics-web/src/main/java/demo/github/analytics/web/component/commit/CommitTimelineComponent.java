package demo.github.analytics.web.component.commit;

import demo.github.analytics.web.domain.model.commit.Commit;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * @author Aram Mkrtchyan.
 */
public class CommitTimelineComponent extends Panel {

    private static DateTimeFormatter groupTitleFormat;

    static {
        groupTitleFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        groupTitleFormat.withZone(TimeZone.getTimeZone("UTC").toZoneId());
    }

    public CommitTimelineComponent(String id, List<Commit> commits) {
        super(id);
        addCommitsListing(commits);
    }

    private void addCommitsListing(List<Commit> commits) {
        RepeatingView repeatingView = new RepeatingView("commitsListing");
        groupCommitsByDate(commits).forEach((group, groupCommits) -> {
            repeatingView.add(new CommitGroupComponent(repeatingView.newChildId(), group, groupCommits));
        });
        add(repeatingView);
    }

    private Map<String, List<Commit>> groupCommitsByDate(List<Commit> commits) {
        return commits.stream().collect(Collectors.groupingBy(
                (c) -> groupTitleFormat.format(c.getDate()),
                LinkedHashMap::new,
                Collectors.toList()
        ));
    }

}
