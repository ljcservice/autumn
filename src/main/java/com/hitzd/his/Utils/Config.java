package com.hitzd.his.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;
import com.hitzd.his.Beans.Middle.TDBUrlConfig;
import com.hitzd.his.Beans.Middle.TFieldConfig;
import com.hitzd.his.Beans.Middle.TQueryConfig;
import com.hitzd.his.Beans.Middle.TTableConfig;
import com.hitzd.his.RowMapperBeans.Middle.DBUrlConfigMapper;
import com.hitzd.his.RowMapperBeans.Middle.FieldConfigMapper;
import com.hitzd.his.RowMapperBeans.Middle.QueryConfigMapper;
import com.hitzd.his.RowMapperBeans.Middle.TableConfigMapper;
import com.hitzd.DBUtils.CommonMapper;

/**
 *  ���ò��� 
 * @author Administrator
 *
 */
public class Config 
{
    /* ���� */
    private static HashMap<String, String>            paramMap = null;
    /* ���ձ����� */
    private static HashMap<String, TTableConfig> tableMap = null;
    private static HashMap<String,TDBUrlConfig>  dbUrlMap = null; 
    public static boolean paramLoaded = false;
    public static boolean tableConfigLoaded = false;
    
    /**
     * ������ʼ��
     */
    public static void initParam()
    {
    	System.out.println("Config������ʼ����ʼ��");
    	JDBCQueryImpl peaasQuery = DBQueryFactory.getQuery("PEAAS");
    	// ��ʼ��������Ϣ
    	paramMap = new HashMap<String, String>(); 
        @SuppressWarnings("unchecked")
		List<TCommonRecord> list = peaasQuery.query("select rulecode,rulevalue from ruleparameter", new CommonMapper());
        for (TCommonRecord cr: list)
        	paramMap.put(cr.get("rulecode").toUpperCase(), cr.get("rulevalue"));
        peaasQuery = null;
        paramLoaded = true;
    	System.out.println("Config������ʼ��������");
    }
    
    /**
     * ����ֶ����ó�ʼ��
     */
    @SuppressWarnings ("unchecked")
    public static void initTableConfig()
    {
    	System.out.println("Config����ֶ����ó�ʼ����ʼ��");
    	JDBCQueryImpl peaasQuery = DBQueryFactory.getQuery("PEAAS");
        // ��ʼ�����ձ�������Ϣ
        tableMap = new HashMap<String, TTableConfig>();
        @SuppressWarnings("unchecked")
		List<TTableConfig> tableList = peaasQuery.query("select * from table_config", new TableConfigMapper());
        if (tableList != null && tableList.size() > 0)
        {
	        for (TTableConfig table : tableList)
	        {
	        	@SuppressWarnings("unchecked")
				List<TFieldConfig> fieldList = peaasQuery.query("select * from field_config where table_id='" + table.getTableId() + "'", new FieldConfigMapper());
	        	if (fieldList != null && fieldList.size() > 0)
	        	{
		        	TFieldConfig[] fields = new TFieldConfig[fieldList.size()];
		        	Map<String, TFieldConfig> fieldMap = new HashMap<String, TFieldConfig>();
		        	for (int i = 0; i < fieldList.size(); i++)
		        	{
		        		fields[i] = fieldList.get(i);
		        		fieldMap.put(fieldList.get(i).getOriginalField(), fieldList.get(i));
		        	}
		        	table.setFields(fields);
		        	table.setFieldMap(fieldMap);
	        	}
	        	@SuppressWarnings("unchecked")
				List<TQueryConfig> queryList = peaasQuery.query("select * from query_config where table_id='" + table.getTableId() + "'", new QueryConfigMapper());
	        	if (queryList != null && queryList.size() > 0)
	        	{
	        		TQueryConfig[] queries = new TQueryConfig[queryList.size()];
		        	Map<String, TQueryConfig> queryMap = new HashMap<String, TQueryConfig>();
	        		for (int i = 0; i < queryList.size(); i++)
	        		{
	        			queries[i] = queryList.get(i);
	        			queryMap.put(queryList.get(i).getQueryId(), queryList.get(i));
	        		}
	        		table.setQueries(queries);
	        		table.setQueryMap(queryMap);
	        	}
	        	tableMap.put(table.getTableId(), table);
	        }
        }
        dbUrlMap = new HashMap<String, TDBUrlConfig>();
        List<TDBUrlConfig>  list = peaasQuery.query("select * from db_url_config ", new DBUrlConfigMapper());
        for(TDBUrlConfig t : list)
        {
            dbUrlMap.put(t.getDb_url(), t);
        }
        peaasQuery = null;
        tableConfigLoaded = true;
    	System.out.println("Config����ֶ����ó�ʼ��������");
    }
    
    /**
     *  ���ò��� 
     */
    public static void ReSetParam()
    {
        initParam();
    }
    
    /**
     * ���ñ���ֶ�����
     */
    public static void ReSetTableConfig()
    {
    	initTableConfig();
    }
    
    /**
     * ��������Config
     */
    public static void ReSetConfig()
    {
        initParam();
        initTableConfig();
    }
    
    /**
     * ��������ϵͳ����
     * @param key
     * @param value
     */
    public static void setParamValue(String key ,String value)
    {
        
         // 2014-09-02 liujc �޸ģ�   
        if (!paramLoaded) initParam();
        if (paramMap.containsKey(key.toUpperCase()))
        {
            paramMap.put(key.toUpperCase(), value); 
        }
        else
        {
            System.out.println("������û�иò�����" + key);
        }
    }
    
    /**
     * 
     * @param paraCode
     * @return
     */
    private static String getSelectParamValue(String paraCode)
    {
//        System.out.println("Config ��ȡ���� " + paraCode ); 
        JDBCQueryImpl query =  DBQueryFactory.getQuery("PEAAS");
        CommonMapper  cmr   = new CommonMapper();
        TCommonRecord  rs   = new TCommonRecord();
        try
        {
            rs = (TCommonRecord) query.queryForObject("select RULEVALUE from ruleparameter t where upper(t.RULECODE) = ?��", new Object[]{paraCode}, cmr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //query = null;
            cmr   = null;
        }
        if(rs == null) new RuntimeException("�ò���" + paraCode + " Ϊ�������в����� ");
//        System.out.println("Config ��ȡ�����ɹ� " + paraCode + ":" + rs.get("RULEVALUE")); 
        return rs.get("RULEVALUE");
    }
    
    /**
     * ���ϵͳ����
     * @param name
     * @return
     */
    public static String getParamValue(String name)
    {
    	
        if (!paramLoaded) initParam();
    	if (paramMap.containsKey(name.toUpperCase()))
    	{
    		return paramMap.get(name.toUpperCase());
    	}
    	return getSelectParamValue(name.toUpperCase());
    }
    
    public static int getIntParamValue(String name)
    {
   		try
   		{
   			return Integer.parseInt(getParamValue(name));
   		}
   		catch (Exception ex)
   		{
   			System.out.println("��ȡϵͳ��������ֵ����" + name);
   			return 0;
   		}
    }

    /**
     * �����м����ձ�
     * @return
     */
	public static HashMap<String, TTableConfig> getTableMap()
	{
    	if (!tableConfigLoaded) initTableConfig();
		return tableMap;
	}
	
	/**
	 * ����м����ձ�
	 * @param key
	 * @return
	 */
	public static TTableConfig getTableCofig(String key)
	{
	    if (!tableConfigLoaded) initTableConfig();
	    if(!tableMap.containsKey(key.toUpperCase()))
	    {
	        new RuntimeException("�м�� table_Config ��û���ҵ�Ŀ�ꡰ" + key + "����");
	    }
	    return tableMap.get(key.toUpperCase());
	}
	
	/**
	 * ����Ԫ��
	 * @return
	 */
	public static HashMap<String, TDBUrlConfig> getDBUrlMap()
	{
	    if (!tableConfigLoaded) initTableConfig();
        return dbUrlMap;
	}
	
	/**
     * �������Ԫ��
     * @param key
     * @return
     */
    public static TDBUrlConfig getDBUrlConfig(String key)
    {
        if (!tableConfigLoaded) initTableConfig();
        if(!dbUrlMap.containsKey(key))
        {
            new RuntimeException("�м�� db_url_config ��û���ҵ�Ŀ�ꡰ" + key + "����");
        }
        return dbUrlMap.get(key);
    }
}
