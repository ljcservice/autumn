package com.hitzd.his.Beans.Middle;

import java.util.HashMap;
import java.util.Map;

/**
 * ��������
 * @author Crystal
 */
public class TTableConfig
{
	/* Ψһ��ʶ */
	private String tableId;
	/* ������ */
	private String tableDesc;
	/* ԭʼ�� */
	private String originalTable;
	/* Ŀ��� */
	private String targetTable;
	/* ��ע */
	private String remark;
	/* HIS��׼��Ĭ��JWYH-304 */
	private String hisName;
	/* ����Դ��ʶ */
	private String dbUrl;
	/* ���ݿ����ͣ�Oracle,MySQL,SQL Server */
	private String dbName;
	/* ��Ӧ�ֶ� */
	private TFieldConfig[] fields;
	private Map<String, TFieldConfig> fieldMap = null;
	/* ��Ӧ��ѯ���� */
	private TQueryConfig[] queries;
	private Map<String, TQueryConfig> queryMap = null;

	public TTableConfig()
	{
		this.tableId = "";
		this.tableDesc = "";
		this.originalTable = "";
		this.targetTable = "";
		this.remark = "";
		this.hisName = "JWYH-304";
		this.dbUrl = "HIS";
		this.dbName = "Oracle";
		this.fields = new TFieldConfig[]{};
		this.fieldMap = new HashMap<String, TFieldConfig>();
		this.queries = new TQueryConfig[]{};
		this.queryMap = new HashMap<String, TQueryConfig>();
	}

	public String getTableId()
	{
		return tableId;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}

	public String getTableDesc()
	{
		return tableDesc;
	}

	public void setTableDesc(String tableDesc)
	{
		this.tableDesc = tableDesc;
	}

	public String getOriginalTable()
	{
		return originalTable;
	}

	public void setOriginalTable(String originalTable)
	{
		this.originalTable = originalTable;
	}

	public String getTargetTable()
	{
		return targetTable;
	}

	public void setTargetTable(String targetTable)
	{
		this.targetTable = targetTable;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getHisName()
	{
		return hisName;
	}

	public void setHisName(String hisName)
	{
		this.hisName = hisName;
	}

	public String getDbUrl()
	{
		return dbUrl;
	}

	public void setDbUrl(String dbUrl)
	{
		this.dbUrl = dbUrl;
	}

	public String getDbName()
	{
		return dbName;
	}

	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}

	public TFieldConfig[] getFields()
	{
		return fields;
	}

	public void setFields(TFieldConfig[] fields)
	{
		this.fields = fields;
	}

	public Map<String, TFieldConfig> getFieldMap()
	{
		return fieldMap;
	}

	public void setFieldMap(Map<String, TFieldConfig> fieldMap)
	{
		this.fieldMap = fieldMap;
	}

	public TQueryConfig[] getQueries()
	{
		return queries;
	}

	public void setQueries(TQueryConfig[] queries)
	{
		this.queries = queries;
	}

	public Map<String, TQueryConfig> getQueryMap()
	{
		return queryMap;
	}

	public void setQueryMap(Map<String, TQueryConfig> queryMap)
	{
		this.queryMap = queryMap;
	}
}