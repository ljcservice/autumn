package com.hitzd.WebPage.Impl;

import java.util.LinkedHashMap;

import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.WebPage.PageView;
import com.hitzd.WebPage.QueryResult;
import com.hitzd.DBUtils.CommonMapper;
import com.hitzd.persistent.Persistent4DB;

/**
 * ��ҳ���� 
 * @author Administrator
 *
 */
public class BasePageBean extends Persistent4DB
{
    
    /**
     *  ��ҳ����
     * @param maxresult    һҳ��ʾ��¼��
     * @param currentpage  ҳ��
     * @param queryCode    ϵͳ����
     * @param wheres       ���� 
     * @param value        ����ֵ
     * @param orders       ����
     * @param tableName    ����
     * @param fields       �ֶ����� 
     * @return
     */
    @SuppressWarnings ("unchecked")
    public PageView<TCommonRecord> getScrollData(int maxresult, int currentpage, String queryCode
            ,String wheres,Object[] value ,LinkedHashMap<String, String> orders,String tableName,String fields)
    {
        setQueryCode(queryCode);
        /* ��ҳ���ݼ���  */
        QueryResult<TCommonRecord> qr = new QueryResult<TCommonRecord>();
        /* ҳ�� */
        currentpage = currentpage < 1 ? 1 : currentpage;
        /* һҳ��ʾ��¼�� */
        maxresult   = maxresult < 1? 12 : maxresult;
        PageView<TCommonRecord>  pageView = new PageView<TCommonRecord>(maxresult,currentpage);
        /* ��������  */
        StringBuffer countSql = new StringBuffer();
        countSql.append("select count(*) total from ").append(tableName).append(" where 1=1 ").append(wheres);
        /* �������� */
        qr.setTotalrecord(((TCommonRecord)query.queryForObject(countSql.toString(),value == null ? new Object[]{} : value,new CommonMapper())).getInt("total"));
        /* ����ҳ��С�ڵ�ǰҳ  ��ǰҳ����Ϊ ��һҳ */
        if(((qr.getTotalrecord() / maxresult) + ((qr.getTotalrecord() % maxresult) > 0 ? 1 : 0 )) < currentpage)
        {
            pageView.setCurrentpage(1);
        }
        Integer firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();
        Integer lastindex  = (pageView.getCurrentpage()) * pageView.getMaxresult();
        /* �Ƿ�ָ���ֶβ�ѯ  */
        fields  = fields == null? "*": fields;
        /* ��ҳ���� */
        StringBuffer pageSql = new StringBuffer("select * from (select t.* ,rownum rn from (select ").append(fields).append(" from ");
        pageSql.append(tableName).append(" where 1=1 ").append(wheres);
        pageSql.append(getOrder(orders));
        pageSql.append(") t where rownum <= ").append(lastindex.toString()).append(") b where b.rn > ").append(firstindex.toString());
        /*  ��ҳ����  */
        qr.setResultlist(query.query(pageSql.toString(),value == null?new Object[]{}:value, new CommonMapper()));
        pageView.setQueryResult(qr);
        return pageView;
    }
    
    public PageView<TCommonRecord> getScrollData(int maxresult, int currentpage, String queryCode
            ,String wheres,Object[] value ,LinkedHashMap<String, String> orders,String tableName)
    {
        return getScrollData( maxresult,  currentpage,  queryCode
                , wheres, value , orders, tableName ,null);
    }

    public PageView<TCommonRecord> getScrollData(int maxresult, int currentpage,String queryCode, String tableName)
    {
        return getScrollData(maxresult, currentpage, queryCode,null,null ,null, tableName,null);
    }
    
    public PageView<TCommonRecord> getScrollData(int maxresult, int currentpage,String queryCode,String wheres, String tableName)
    {
        return getScrollData(maxresult, currentpage, queryCode,wheres,null ,null, tableName,null);
    }
    
    public PageView<TCommonRecord> getScrollData(int maxresult, int currentpage,String queryCode,String wheres,LinkedHashMap<String
            , String> orders, String tableName)
    {
        return getScrollData(maxresult, currentpage, queryCode,wheres,null ,orders, tableName,null);
    }
    
    public PageView<TCommonRecord> getScrollData(int maxresult, int currentpage,String queryCode,String wheres
                ,Object[] value , String tableName)
    {
        return getScrollData(maxresult, currentpage, queryCode,wheres,value ,null, tableName,null);
    }
    
    /**
     * �������򷽷� 
     * @param orders
     * @return
     */
    private  String getOrder(LinkedHashMap<String, String> orders)
    {
        StringBuffer strOrder = new StringBuffer("");
        if(orders!=null&&orders.size()>0)
        {
            strOrder.append(" order by ");
            for(String order :orders.keySet())
            {
                strOrder.append(order).append(" ").append(orders.get(order)).append(",");
            }
            strOrder.deleteCharAt(strOrder.length()-1);
        }
        return strOrder.toString();
    }
    
}
