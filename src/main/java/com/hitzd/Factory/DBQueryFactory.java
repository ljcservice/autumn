package com.hitzd.Factory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.JdbcTemplateHander;
import com.hitzd.springBeanManager.SpringBeanUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * ����Դ����
 * @author Administrator
 *
 */
@Service
public final class DBQueryFactory
{
    private DBQueryFactory()
    {
    }

    /**
     * �������Դ
     * @param resourceID
     * @return
     */
    public static DataSource getDataSource(String resourceID)
    {
        DataSource ds = null;
        try
        {
            //2014-10-21 liujc �޸� ʹ��tomcat  �������ӳ�
            Context  initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup(resourceID);
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return ds ;
    }
    
    /**
     * spring ԭʼ����sql JdbcTemplate  
     * @param resourceID
     * @return
     */
    public  static JdbcTemplate getDBQuery(String resourceID)
    {
        synchronized(DBQueryFactory.class)
        {
            DataSource ds = null;
            try
            {
                //2014-10-21 liujc �޸� ʹ��tomcat  �������ӳ�
                ds = getDataSource(resourceID);
                
                // 2014-10-21 liujc �޸�   ���������ӳز��й���spring������
                //ds = (DataSource) SpringBeanUtil.getBean(resourceID);    
            }
            catch(NullPointerException nullPoiE)
            {
                new RuntimeException("NullPointerException ��ָ���쳣");
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            return new JdbcTemplate(ds);
        }
    }
    
    /**
     * ֱ�ӻ�����ݿ���ʶ���
     * @param resourceID
     * @return
     */
    @SuppressWarnings ("unused")
    public static JDBCQueryImpl getQuery(String resourceID)
	{
    	JDBCQueryImpl query = new JDBCQueryImpl((new JdbcTemplateHander()).bind(getDBQuery(resourceID),resourceID),resourceID) ;
	    if(query == null)
    	    new RuntimeException("The code name is not true , can't load datasource : " + resourceID);
	    return query;
	}
}
