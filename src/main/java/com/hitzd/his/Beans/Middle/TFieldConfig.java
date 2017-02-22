package com.hitzd.his.Beans.Middle;

/**
 * �ֶζ�������
 * @author Crystal
 */
public class TFieldConfig
{
	/* Ψһ��ʶ */
	private String fieldId;
	/* ������ID */
	private String tableId;
	/* �ֶ����� */
	private String fieldDesc;
	/* ԭʼ�ֶ� */
	private String originalField;
	/* Ŀ���ֶ� */
	private String targetField;
	/* ��ע */
	private String remark;
	/* HIS��׼��Ĭ��JWYH-304 */
	private String hisName;
	
	public TFieldConfig()
	{
		this.fieldId = "";
		this.tableId = "";
		this.fieldDesc = "";
		this.originalField = "";
		this.targetField = "";
		this.remark = "";
		this.hisName = "JWYH-304";
	}

	public String getFieldId()
	{
		return fieldId;
	}

	public void setFieldId(String fieldId)
	{
		this.fieldId = fieldId;
	}

	public String getTableId()
	{
		return tableId;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}

	public String getFieldDesc()
	{
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc)
	{
		this.fieldDesc = fieldDesc;
	}

	public String getOriginalField()
	{
		return originalField;
	}

	public void setOriginalField(String originalField)
	{
		this.originalField = originalField;
	}

	public String getTargetField()
	{
		return targetField;
	}

	public void setTargetField(String targetField)
	{
		this.targetField = targetField;
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