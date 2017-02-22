package com.hitzd.his.casehistory.helper;

import com.hitzd.DBUtils.TCommonRecord;

public class CaseHistoryHelperUtils 
{
	/**
	 * �Ⱥţ�=
	 */
	public static final String EQ = "=";
	
	/**
	 * ģ����ѯ��like
	 */
	public static final String LIKE = "like";
	
	/**
	 * ȡ��ֵ֮������ݣ�between..and..
	 */
	public static final String BETWEENAND = "between..and";
	
	/**
	 * С�ڣ�<
	 */
	public static final String LESS = "<";
	
	/**
	 * ���ڣ�>
	 */
	public static final String MORE = ">";
	
	/**
	 * С�ڵ��ڣ�<=
	 */
	public static final String LESSEQ = "<=";
	
	/**
	 * ���ڵ��ڣ�>=
	 */
	public static final String MOREEQ = ">=";
	
	/**
	 * ��ѯ�����涨���ֵ��in
	 */
	public static final String IN = "in";
	
	/**
	 * ��ѯ is Ϊ���Ӽ���
	 */
	public static final String IS =  "is";
	
	/**
	 * @param lsWheres �����б���һ��TCommonRecord���б�ÿһ��TCommonRecord����FieldName, GroupNo, FieldValue, FieldType, Relation, Condition
	 * ����
	 * FieldName : ��ʾ���������ĸ��ֶ����
	 * FieldValue: ��ʾ���������ֶ�ȡֵ�����FieldValue�ĵ�һ�������һ���ַ�Ϊ%��������like���
	 * FieldType : ��ʾ���ֶε����ͣ������Char����ƴ�õ�sql�а���'�����򲻰���'��Ĭ��ֵΪ��char��������'
	 * Relation  : ��ʾ�ֶ������ֶ�ֵ֮��Ĺ�ϵ��=��>=��<=�ȵȣ�Ĭ��ֵΪ��ֵ����ʾ��ϵΪ=
	 * GroupNo   : ��ʾ����������ţ����2������Ϊһ�飬��Ҫ������ѯ�����磺
	 * ((StartDateTime <= to_date('2011-01-01', 'yyyy-mm-dd')) and (StopDateTime >= to_date('2011-02-01', 'yyyy-mm-dd')))
	 * �����2�������ͱ���һ���ã��������2���ֶ�Ϊһ�飬GroupNo��Ӧ�÷����ظ���Ĭ��Ϊ��ֵ����ʾ��������������һ������
	 * Condition : ��ʾ������ǰ�������and ���� or��Ĭ��ֵΪ��ֵ����ʾǰ��� and 
	 * @param strGroup group by ��������ŵ��ֶ��б�����1�����ݣ�FieldName
	 * @param strOrder order by ������ŵ��ֶ��б�����2�����ݣ�һ����FieldName��һ����By��By��ȡֵΪ�գ�asc��desc
	 */
	public static TCommonRecord genWhereCR(String FieldName, String FieldValue, String FieldType, String Relation, String GroupNo, String Condition)
	{
		TCommonRecord Result = new TCommonRecord();
		Result.set("FieldName", FieldName);
		Result.set("FieldValue", FieldValue);
		Result.set("FieldType", FieldType);
		Result.set("Relation", Relation);
		Result.set("GroupNo", GroupNo);
		Result.set("Condition", Condition);
		return Result;
	}
	
	/**
	 * ���ɴ����ĵĲ�ѯ����
	 * @param FieldName
	 * @param FieldValue
	 * @param FieldType
	 * @param Relation
	 * @param GroupNo
	 * @param Condition
	 * @return
	 */
	public static TCommonRecord genWhereGbkCR(String FieldName, String FieldValue, String FieldType, String Relation, String GroupNo, String Condition)
	{
		TCommonRecord Result = new TCommonRecord();
		Result.set("FieldName", FieldName);
		if (BETWEENAND.equals(Relation))
		{
			String[] values = FieldValue.split("&");
			FieldValue = "?" + values[0] + ":end&?" + values[1] + ":end";
			Result.set("FieldValue", FieldValue);
		}
		else
		{
			Result.set("FieldValue", "?" + FieldValue + ":end");
		}
		Result.set("FieldType", FieldType);
		Result.set("Relation", Relation);
		Result.set("GroupNo", GroupNo);
		Result.set("Condition", Condition);
		return Result;
	}
	
	/**
	 * ���ɷ������
	 * @param FieldName
	 * @return
	 */
	public static TCommonRecord genGroupCR(String FieldName)
	{
		TCommonRecord Result = new TCommonRecord();
		Result.set("FieldName", FieldName);
		return Result;
	}
	
	/**
	 * �����������
	 * @param FieldName
	 * @param By
	 * @return
	 */
	public static TCommonRecord genOrderCR(String FieldName, String By)
	{
		TCommonRecord Result = new TCommonRecord();
		Result.set("FieldName", FieldName);
		Result.set("By", By);
		return Result;
	}
	
	
}