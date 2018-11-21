package demo.github.connector.client.common;

/**
 * Class for pagination information.
 *
 * @author Aram Mkrtchyan.
 */
public class PageRequest {

    private int page;

    private int pageSize;

    public PageRequest(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    /**
     * Returns the page to be returned.
     *
     * @return the page to be returned.
     */
    public int getPage() {
        return page;
    }

    /**
     * Returns the number of items to be returned.
     *
     * @return the number of items of that page
     */
    public int getPageSize() {
        return pageSize;
    }
}
