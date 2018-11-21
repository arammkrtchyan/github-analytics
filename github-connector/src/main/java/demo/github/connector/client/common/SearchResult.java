package demo.github.connector.client.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The result of a <code>search</code> request. Contains the items that match the specified search criteria and
 * total numbers of matched items.
 * </p>
 *
 * @author Aram Mkrtchyan.
 */
public class SearchResult<T> implements Serializable {

    @JsonProperty("total_count")
    private long totalCount;

    private List<T> items = new ArrayList<>();

    /**
     * Total number of items that that match the search criteria
     *
     * @return the total number of items that that match the search criteria.
     */
    public long getTotalCount() {
        return totalCount;
    }


    /**
     * The items that match the search criteria. Result can be paginated.
     *
     * @return the items that match the search criteria.
     */
    public List<T> getItems() {
        return items;
    }

}
