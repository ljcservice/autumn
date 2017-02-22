package com.hitzd.his.casehistory.helper;

import com.hitzd.DBUtils.CommonMapper;
import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;

/**
 * �м�㹤����
 * @author jingcong
 *
 */
public  class CaseHistoryUtils
{
    private CaseHistoryUtils()
    {
    }
    
    /**
     * ��ȡ��table_config������Ϣ
     * @param tableid
     * @return
     */
    public static TCommonRecord  getTableConfig(String tableid)
    {
        JDBCQueryImpl query = DBQueryFactory.getQuery("PEAAS");
        TCommonRecord  tcom = null;
        try
        {
            tableid = tableid.trim().toUpperCase();
            String sql = "select TABLE_ID ,TABLE_DESC ,ORIGINAL_TABLE ,TARGET_TABLE ,REMARK ,HIS_NAME ,DB_URL ,DB_NAME from table_config  where table_Id = '" + tableid + "'";
            tcom = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
        }
        return tcom;
    }
    
}
