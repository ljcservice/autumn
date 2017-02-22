package com.hitzd.his.casehistory.helper;

import java.util.HashMap;
import java.util.Map;

import com.hitzd.his.Beans.Middle.TTableConfig;

/**
 * �м�㺯����
 * @author Crystal
 */
public class CaseHistoryFunction
{
	/**
	 * ��:+
	 */
	public static final String PLUS = "+";
	/**
	 * ��:-
	 */
	public static final String MINUS = "-";
	/**
	 * ��:*
	 */
	public static final String MULTIPLY = "*";
	/**
	 * ��:/
	 */
	public static final String DIVIDED = "/";
	
	/**
	 * ����+ʱ���ʽ��2013-10-29 16:20:45
	 */
	public static final String DATETIME = "yyyy-mm-dd hh24:mi:ss";
	
	/**
	 * ���ڸ�ʽ��2013-10-29
	 */
	public static final String DATE = "yyyy-mm-dd";
	
	/**
	 * ʱ���ʽ��16:20:45
	 */
	public static final String TIME = "hh24:mi:ss";
	
	/**
	 * SQLServer���ڸ�ʽ
	 */
	private static Map<String, String> SQLServerDateFmt = null;
	
	/**
	 * MySQL���ڸ�ʽ
	 */
	private static Map<String, String> MySQLDateFmt = null;
	
	private static Map<String, TTableConfig> tables = CaseHistoryFactory.getTableMap();

	static
	{
	    /* ��ʼ������ */
		SQLServerDateFmt = new HashMap<String, String>();
		SQLServerDateFmt.put(DATETIME, "20");
		SQLServerDateFmt.put(DATE, "20");
		SQLServerDateFmt.put(TIME, "108");
		MySQLDateFmt = new HashMap<String, String>();
		MySQLDateFmt.put(DATETIME, "%Y-%m-%d %H:%i:%s");
		MySQLDateFmt.put(DATE, "%Y-%m-%d");
		MySQLDateFmt.put(TIME, "%H:%i:%s");
	}
	
	/**
	 * ��ȡĿ���ֶ�
	 * @param tableName ����
	 * @param fieldName ԭʼ�ֶ�
	 * @return Ŀ���ֶ�
	 */
	private static String getTargetField(String tableName, String fieldName)
	{
		/* ��ʼ������ */
		tableName = tableName == null ? "" : tableName.trim();
		fieldName = fieldName == null ? "" : fieldName.trim();
		// ������ڱ��������ʾ��ת��Ϊ��׼ת�������򣬱�ת�����ֶ�Ϊ��ʱ�ֶ�
		if (tableName != null && !"".equals(tableName))
		{
		    String[] f = depenTargetField(fieldName);
			if (tables.containsKey(tableName.toUpperCase()) && tables.get(tableName.toUpperCase()).getFieldMap().containsKey(f[1].toUpperCase()))
			{
				String targetField = tables.get(tableName.toUpperCase()).getFieldMap().get(f[1].toUpperCase()).getTargetField();
				if (!"".equals(targetField))
				{
				    if(!"".equals(f[0])) targetField = f[0] + "." + targetField;
				    return targetField;
				}
			}
			return fieldName;
		}
		return fieldName;
	}
	
	private static String[] depenTargetField(String fieldName)
	{
	    String[] rs = {"",fieldName};
	    if(fieldName.indexOf(".") != -1)
	    {
	        rs[0] = fieldName.substring(0,fieldName.indexOf("."));
            rs[1] = fieldName.substring(fieldName.indexOf(".") + 1,fieldName.length());
	    }
	    return rs;
	}
	
	/**
	 * ��ȡ���Ӧ�����ݿ⣺Oracle/SQLServer/MySQL
	 * @param tableName �������������Ϊ��
	 * @return
	 */
	private static String getDBName(String tableName)
	{
		if (tables.containsKey(tableName.toUpperCase()))
			return tables.get(tableName.toUpperCase()).getDbName();
		return "";
	}
	
	/**
	 * ���ʽ���ɷ��ؼ򵥼Ӽ��˳����ʽ����
	 * @param tableName1  ��1
	 * @param fieldName1  ��1���ֶ�
	 * @param t1                        ��1�ı���
	 * @param tableName2  ��2
	 * @param fieldName2  ��2���ֶ�
	 * @param t2                       ��2�ı���
	 * @param operators  ����������Ե��ó����ӡ������ˡ�����ע���������ǰ����fieldName1�������������fieldName2
	 * @param as         ���������Ϊ�������������ӱ���
	 * @param isFunction �Ƿ�����ʽ
	 * @return           �������isFunctionΪtrue��������fn:��ͷ��:end��β�ĺ�����ʽ�����򣬷��ر��ʽ
	 */
	public static String genExpression(String tableName1, String fieldName1, String t1, String tableName2, String fieldName2, String t2, String operators, String as, boolean isFunction)
	{
		/* ��ʼ������ */
		t1 = t1 == null ? "" : t1.trim();
		t2 = t2 == null ? "" : t2.trim();
		as = as == null ? "" : as.trim();
		String field1 = getTargetField(tableName1, fieldName1);
		if (t1 != null && !"".equals(t1) && t1.endsWith("."))
			field1 = t1 + "." + field1;
		String field2 = getTargetField(tableName2, fieldName2);
		if (t2 != null && !"".equals(t2) && t2.endsWith("."))
			field2 = t2 + "." + field2;
		if (isFunction)
			return "fn:(" + field1 + operators + field2 + ")" + (as != null && !"".equals(as) && !as.endsWith(".") ? " as " + as + ":end" : ":end");
		return field1 + operators + field2;
	}
	
	/**
	 * ������to_char
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param fmt       ��ʽ����ʽ����yyyy-mm-dd
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genToChar(String tableName, String fieldName, String fmt, String as)
	{
		
		fmt = fmt == null ? DATE : fmt.trim().toLowerCase();
		as = as == null ? "" : as.trim();
		String db_name = getDBName(tableName);
		if ("Oracle".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:to_char(" + getTargetField(tableName, fieldName) + ", '" + fmt + "') as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:to_char(" + as + getTargetField(tableName, fieldName) + ", '" + fmt + "'):end";
					// ����.�ֶα���
					else if (as.indexOf(".") < as.length())
						return "fn:to_char(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", '" + fmt + "') as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:to_char(" + getTargetField(tableName, fieldName) + ", '" + fmt + "'):end";
		}
		else if ("SQLServer".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:convert(char(" + fmt.length() + "), " + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(fmt) + ") as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:convert(char(" + fmt.length() + "), " + as + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(fmt) + "):end";
					// ����.�ֶα���
					else if (as.indexOf(".") < as.length())
						return "fn:convert(char(" + fmt.length() + "), " + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", " + SQLServerDateFmt.get(fmt) + ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:convert(char(" + fmt.length() + "), " + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(fmt) + "):end";
		}
		else if ("MySQL".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:date_format(" + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(fmt) + "') as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:date_format(" + as + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(fmt) + "'):end";
					// ����.�ֶα���
					else if (as.indexOf(".") < as.length())
						return "fn:date_format(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", '" + MySQLDateFmt.get(fmt) + "') as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:date_format(" + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(fmt) + "'):end";
		}
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:" + getTargetField(tableName, fieldName) + " as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:" + as + getTargetField(tableName, fieldName) + ":end";
				// ����.�ֶα���
				else
					return "fn:" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   " as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:" + getTargetField(tableName, fieldName) + ":end";
	}
	
	/**
	 * ������to_date
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param fmt       ��ʽ����ʽ����yyyy-mm-dd
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genToDate(String tableName, String fieldName, String fmt, String as)
	{
		fmt = fmt == null ? DATE : fmt.trim().toLowerCase();
		as = as == null ? "" : as.trim();
		String db_name = getDBName(tableName);
		if ("Oracle".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:to_date(" + getTargetField(tableName, fieldName) + ", '" + fmt + "') as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:to_date(" + as + getTargetField(tableName, fieldName) + ", '" + fmt + "'):end";
					// ����.�ֶα���
					else
						return "fn:to_date(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", '" + fmt + "') as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:to_date(" + getTargetField(tableName, fieldName) + ", '" + fmt + "'):end";
		}
		else if ("SQLServer".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:convert(char(" + fmt.length() + "), " + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(fmt) + ") as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:convert(char(" + fmt.length() + "), " + as + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(fmt) + "):end";
					// ����.�ֶα���
					else if (as.indexOf(".") < as.length())
						return "fn:convert(char(" + fmt.length() + "), " + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", " + SQLServerDateFmt.get(fmt) + ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:convert(char(" + fmt.length() + "), " + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(fmt) + "):end";
		}
		else if ("MySQL".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:str_to_date(" + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(fmt) + "') as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:str_to_date(" + as + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(fmt) + "'):end";
					// ����.�ֶα���
					else
						return "fn:str_to_date(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", '" + MySQLDateFmt.get(fmt) + "') as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:str_to_date(" + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(fmt) + "'):end";
		}
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:" + getTargetField(tableName, fieldName) + " as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:" + as + getTargetField(tableName, fieldName) + ":end";
				// ����.�ֶα���
				else
					return "fn:" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   " as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:" + getTargetField(tableName, fieldName) + ":end";
	}
	
	/**
	 * ������to_number
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genToNumber(String tableName, String fieldName, String as)
	{
		/* ��ʼ������ */
		as = as == null ? "" : as.trim();
		String db_name = getDBName(tableName);
		if ("Oracle".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:to_number(" + getTargetField(tableName, fieldName) + ") as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:to_number(" + as + getTargetField(tableName, fieldName) + "):end";
					// ����.�ֶα���
					else
						return "fn:to_number(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:to_number(" + getTargetField(tableName, fieldName) + "):end";
		}
		else if ("SQLServer".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:cast(" + getTargetField(tableName, fieldName) + " as float) as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:cast(" + as + getTargetField(tableName, fieldName) + " as float):end";
					// ����.�ֶα���
					else
						return "fn:cast(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   " as float) as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:cast(" + getTargetField(tableName, fieldName) + " as float):end";
		}
		else if ("MySQL".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:cast(" + getTargetField(tableName, fieldName) + " as DECIMAL(20,2)) as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:cast(" + as + getTargetField(tableName, fieldName) + " as DECIMAL(20,2)):end";
					// ����.�ֶα���
					else
						return "fn:cast(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   " as DECIMAL(20,2)) as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:cast(" + getTargetField(tableName, fieldName) + " as DECIMAL(20,2)):end";
		}
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:" + getTargetField(tableName, fieldName) + " as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:" + as + getTargetField(tableName, fieldName) + ":end";
				// ����.�ֶα���
				else
					return "fn:" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   " as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:" + getTargetField(tableName, fieldName) + ":end";
	}
	
	/**
	 * ������sum
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genSum(String tableName, String fieldName, String as)
	{
		/* ��ʼ������ */
		as = as == null ? "" : as.trim();
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:sum(" + getTargetField(tableName, fieldName) + ") as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:sum(" + as + getTargetField(tableName, fieldName) + "):end";
				// ����.�ֶα���
				else
					return "fn:sum(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:sum(" + getTargetField(tableName, fieldName) + "):end";
	}
	
	/**
	 * ������count
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genCount(String tableName, String fieldName, String as)
	{
		/* ��ʼ������ */
		as = as == null ? "" : as.trim();
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:count(" + getTargetField(tableName, fieldName) + ") as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:count(" + as + getTargetField(tableName, fieldName) + "):end";
				// ����.�ֶα���
				else
					return "fn:count(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:count(" + getTargetField(tableName, fieldName) + "):end";
	}
	
	/**
	 * ������floor
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genFloor(String tableName, String fieldName, String as)
	{
		/* ��ʼ������ */
		as = as == null ? "" : as.trim();
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:floor(" + getTargetField(tableName, fieldName) + ") as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:floor(" + as + getTargetField(tableName, fieldName) + "):end";
				// ����.�ֶα���
				else
					return "fn:floor(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:floor(" + getTargetField(tableName, fieldName) + "):end";
	}
	
	/**
	 * ������trunc
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genTrunc(String tableName, String fieldName, String as)
	{
		as = as == null ? "" : as.trim();
		String db_name = getDBName(tableName);
		if ("Oracle".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:trunc(" + getTargetField(tableName, fieldName) + ") as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:trunc(" + as + getTargetField(tableName, fieldName) + "):end";
					// ����.�ֶα���
					else
						return "fn:trunc(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:trunc(" + getTargetField(tableName, fieldName) + "):end";
		}
		else if ("SQLServer".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:convert(char(" + DATE.length() + "), " + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(DATE) + ") as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:convert(char(" + DATE.length() + "), " + as + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(DATE) + "):end";
					// ����.�ֶα���
					else if (as.indexOf(".") < as.length())
						return "fn:convert(char(" + DATE.length() + "), " + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", " + SQLServerDateFmt.get(DATE) + ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:convert(char(" + DATE.length() + "), " + getTargetField(tableName, fieldName) + ", " + SQLServerDateFmt.get(DATE) + "):end";
		}
		else if ("MySQL".equalsIgnoreCase(db_name))
		{
			if (!"".equals(as))
			{
				// �ֶα���
				if (as.indexOf(".") < 0)
				{
					return "fn:str_to_date(" + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(DATE) + "') as " + as + ":end";
				}
				else if (as.indexOf(".") > 0)
				{
					// ����.
					if (as.endsWith("."))
						return "fn:str_to_date(" + as + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(DATE) + "'):end";
					// ����.�ֶα���
					else
						return "fn:str_to_date(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
							   ", '" + MySQLDateFmt.get(DATE) + "') as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
				}
			}
			return "fn:str_to_date(" + getTargetField(tableName, fieldName) + ", '" + MySQLDateFmt.get(DATE) + "'):end";
		}
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:" + getTargetField(tableName, fieldName) + " as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:" + as + getTargetField(tableName, fieldName) + ":end";
				// ����.�ֶα���
				else
					return "fn:" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   " as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:" + getTargetField(tableName, fieldName) + ":end";
	}
	
	/**
	 * Oracle������max
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genMax(String tableName, String fieldName, String as)
	{
		/* ��ʼ������ */
		as = as == null ? "" : as.trim();
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:max(" + getTargetField(tableName, fieldName) + ") as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:max(" + as + getTargetField(tableName, fieldName) + "):end";
				// ����.�ֶα���
				else
					return "fn:max(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:max(" + getTargetField(tableName, fieldName) + "):end";
	}
	
	/**
	 * Oracle������min
	 * @param tableName �ֶ���������
	 * @param fieldName �ֶ���
	 * @param as        �������������������ָ�ʽ��
	 *                  1. ����������.  ��fieldName���Ƕ�����ϲ�ѯ��ĳ������ֶΣ���Ҫ�Ա�������ʱ�����ӡ�.�������ã����Է���as�����
	 *                                   ��to_char(a.start_date_time, 'yyyy-mm-dd')������a.������Ϊas�������룻
	 *                  2. �ֶα���                 ������Ϊת��Ϊ������ʽ������ֶ�������������to_char(start_date_time, 'yyyy-mm-dd') sdt������sdt������Ϊas�������룻
	 *                  3. ����.�ֶα���    ������ͬʱ����������ֶα�������ʾ�ⷽʽ���룬��to_char(a.start_date_time, 'yyyy-mm-dd') sdt������a.sdt������Ϊas�������룻
	 * @return          ���򣺷�����fn:��ͷ��:end��β�ĺ�����ʽ
	 */
	public static String genMin(String tableName, String fieldName, String as)
	{
		/* ��ʼ������ */
		as = as == null ? "" : as.trim();
		if (!"".equals(as))
		{
			// �ֶα���
			if (as.indexOf(".") < 0)
			{
				return "fn:min(" + getTargetField(tableName, fieldName) + ") as " + as + ":end";
			}
			else if (as.indexOf(".") > 0)
			{
				// ����.
				if (as.endsWith("."))
					return "fn:min(" + as + getTargetField(tableName, fieldName) + "):end";
				// ����.�ֶα���
				else
					return "fn:min(" + as.substring(0, as.indexOf(".") + 1) + getTargetField(tableName, fieldName) + 
						   ") as " + as.substring(as.indexOf(".") + 1, as.length()) + ":end";
			}
		}
		return "fn:min(" + getTargetField(tableName, fieldName) + "):end";
	}
	
	/**
	 * ���� Sql�����
	 * @param tableName
	 * @param fieldName
	 * @return
	 */
	public static String genRToDate(String tableName,String fieldName,String value ,String fmt)
	{
	    fmt = fmt == null ? DATE : fmt.trim().toLowerCase();
        String db_name = getDBName(tableName);
        if ("Oracle".equalsIgnoreCase(db_name))
        {
            return " to_date(" + value + ", '" + fmt + "') ";
        }
        else if ("SQLServer".equalsIgnoreCase(db_name))
        {
            return " convert(char(" + fmt.length() + "), " + value + ", " + SQLServerDateFmt.get(fmt) + ")";
        }
        else if ("MySQL".equalsIgnoreCase(db_name))
        {
            return "str_to_date(" + value + ", '" + MySQLDateFmt.get(fmt) + "') ";
        }
        return value;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static String genRToNumber(String tableName , String fieldName , String value )
	{
        String db_name = getDBName(tableName);
        if ("Oracle".equalsIgnoreCase(db_name))
        {
            return "to_number(" + value + ")";
        }
        else if ("SQLServer".equalsIgnoreCase(db_name))
        {
            return "cast(" + value + " as float)";
        }
        else if ("MySQL".equalsIgnoreCase(db_name))
        {
            return "cast(" + value + " as DECIMAL(20,2))";
        }
        return value;
	}

}