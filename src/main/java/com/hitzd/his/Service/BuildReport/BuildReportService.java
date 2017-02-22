package com.hitzd.his.Service.BuildReport;


import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.WebPage.PageView;

/**
 * ����������
 * @author Administrator
 *
 */
public interface BuildReportService
{
    /**
     * ���������м�¼
     * @param pram
     * @return
     */
    public PageView<TCommonRecord> getList(TCommonRecord parm);
    
    /**
     *  ��Ӽ�¼ 
     * @param pram
     */
    public void addOper(TCommonRecord parm);
    
    /**
     * ���¼�¼
     * @param parm
     */
    public void updateOper(TCommonRecord parm);
    
    /**
     * ɾ����¼ 
     * @param id
     */
    public void deleteOper(String id);
    
    /**
     *  id��������
     * @param id
     * @return
     */
    public TCommonRecord findData(String id );
    
}
