package com.hitzd.his.Beans.Middle;

/**
 * ��ѯ������������
 * @author Crystal
 */
public class TQueryConfig
{
	/* Ψһ��ʶ */
	private String queryId;
	/* ������ID */
	private String tableId;
	/* ��ѯ���� */
	private String queryCondition;
	/* ��ע */
	private String remark;
	/* HIS��׼��Ĭ��JWYH-304 */
	private String hisName;
	
	public TQueryConfig()
	{
		this.queryId = "";
		this.tableId = "";
		this.queryCondition = "";
		this.remark = "";
		this.hisName = "JWYH-304";
	}

	public String getQueryId()
	{
		return queryId;
	}

	public void setQueryId(String queryId)
	{
		this.queryId = queryId;
	}

	public String getTableId()
	{
		return tableId;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}

	public String getQueryCondition()
	{
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition)
	{
		this.queryCondition = queryCondition;
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
}