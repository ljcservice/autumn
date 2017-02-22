package com.hitzd.WebPage;

import java.util.List;

/**
 * 
 * @author liujc
 *
 * @param <T>
 */
public class PageView<T> {
    
    /** ��ҳ���� **/
    private List<T> records;
    /** ҳ�뿪ʼ�����ͽ������� **/
    private PageIndex pageindex;
    /** ��ҳ�� **/
    private long totalpage = 1;
    /** ÿҳ��ʾ��¼�� **/
    private int maxresult = 12;
    /** ��ǰҳ **/
    private int currentpage = 1;
    /** �ܼ�¼�� **/
    private long totalrecord;
    /** ҳ������ **/
    private int pagecode = 10;
    
    public PageView() {
    }

    /**
     * @param maxresult
     * ÿҳ��ʾ��¼��
     * @param currentpage
     * ��ǰҳ
     */
    public PageView(int maxresult, int currentpage) {
        this.maxresult = maxresult;
        this.currentpage = currentpage;
    }
    /**
     * @param qr
     * �����Զ���Ľ����
     */
    public void setQueryResult(QueryResult<T> qr){
        setTotalrecord(qr.getTotalrecord());
        setRecords(qr.getResultlist());
    }
    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public PageIndex getPageindex() {
        return pageindex;
    }

    protected void setPageindex(PageIndex pageindex) {
        this.pageindex = pageindex;
    }

    public long getTotalpage() {
        return totalpage;
    }

    protected void setTotalpage(long totalpage) {
        this.totalpage = totalpage;
        this.setPageindex(WebTool.getPageIndex(pagecode, currentpage,this.totalpage));
    }

    public int getMaxresult() {
        return maxresult;
    }

    protected void setMaxresult(int maxresult) {
        this.maxresult = maxresult;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public long getTotalrecord() {
        return totalrecord;
    }

    protected void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
        setTotalpage(this.totalrecord%this.maxresult==0? this.totalrecord/this.maxresult : this.totalrecord/this.maxresult+1);
    }
    
    /** ҳ������ **/
    public int getPagecode() {
        return pagecode;
    }
    /** ҳ������ **/
    public void setPagecode(int pagecode) {
        this.pagecode = pagecode;
    }
    
}
