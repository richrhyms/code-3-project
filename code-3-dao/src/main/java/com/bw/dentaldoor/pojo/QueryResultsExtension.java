package com.bw.dentaldoor.pojo;

import com.querydsl.core.QueryResults;

import java.util.List;
import java.util.Map;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class QueryResultsExtension<T> {

    private final long limit, offset, total;
    private final List<T> results;

    private Map<String, Object> metadata;

    public QueryResultsExtension(QueryResults<T> tQueryResults) {
        this.limit = tQueryResults.getLimit();
        this.offset = tQueryResults.getOffset();
        this.total = tQueryResults.getTotal();
        this.results = tQueryResults.getResults();
    }

    public long getLimit() {
        return limit;
    }

    public long getOffset() {
        return offset;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getResults() {
        return results;
    }

    public boolean isEmpty() {
        return results.isEmpty();
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
