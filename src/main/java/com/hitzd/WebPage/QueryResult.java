package com.hitzd.WebPage;

import java.util.List;

/**
 * 
 * @author liujc
 *
 * @param <T>
 */
public class QueryResult<T> {
    /** �����¼ **/
    private List<T> resultlist;
    /** �ܼ�¼�� **/
    private long totalrecord;

    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
    }

}
