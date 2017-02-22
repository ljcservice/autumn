package com.hitzd.DBUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.*;

/**
 * ͨ�����ݼ�   
 * @author Administrator
 *
 */
public class TCommonRecord implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6805580253508412294L;
	private Map<String, String> attributes = new HashMap<String, String>();
	private Map<String, Object> objects = new HashMap<String, Object>();
	
	public void setObj(String key, Object obj)
	{
		objects.put(key.toUpperCase(), obj);
	}
	
	public Object getObj(String key)
	{
		return objects.get(key.toUpperCase());
	}
	
	public void set(String key, String value)
	{
		attributes.put(key.toUpperCase(), value);
	}
	
	
	public List<String> getKeys()
	{
		List<String> res = new ArrayList<String>();
		for (String key: attributes.keySet())
			res.add(key);
		return res;
	}
	
	public String get(String key  )
	{
	    String ret = attributes.get(key.toUpperCase());
		return ret == null ? "" : ret; //.replaceAll("'", "''");
	}
	
	/**
	 * Ҫ�������ŵ����
	 * @param key
	 * @param singleq true : Ҫ�������ţ�false ��������
	 * @return
	 */
	public String get(String key , boolean singleq)
	{
	    if(!singleq) return get(key);
	    String ret = attributes.get(key.toUpperCase());
	    return ret == null ? "" : ret.replaceAll("'", "''");
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(String key)
	{
	    String value = get(key);
        if ((value == null) || (value.length() == 0)) value = "0";
        try
        {
            return Long.parseLong(value);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return 0;
        }
	}
	
	public int getInt(String key)
	{
		String value = get(key);
		if ((value == null) || (value.length() == 0)) value = "0";
		try
		{
			return Integer.parseInt(value);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			return 0;
		}
	}
	
	public double getDouble(String key)
	{
		String value = get(key);
		if (value == null || "".equals(value)) value = "0.0";
		try
		{
			return Double.parseDouble(value);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return 0.0;
		}
	}
	/**
	 * ��ָ���ַ��ַ��滻Ϊ���ַ�����  
	 * @param key
	 * @param str
	 * @return
	 */
	public String get(String key,String[] str)
	{
	    String ret = attributes.get(key.toUpperCase());
	    ret = ret == null ? "" : ret;
	    String result = null;
	    for(String s : str)
	    {
	        result = ret.replace(s, "");
	    }
        return  result;
	}
	
	
	
	
	/**
	 * ��ָ���ַ��ַ��滻Ϊ���ַ����� 
	 * @param key
	 * @param strs
	 * @return
	 */
	public String get(String key , String strs)
	{
	    return get(key,new String[]{strs});
	}
	/**
	 * �õ�YYYY-MM-DD��ʽ���ַ���
	 * @param key
	 * @return
	 */
	public String getDateString(String key){
		String value = get(key);
		value = value.length()>10?value.substring(0,10):value;
		return value;
	}
	/**
	 * �õ�YYYY-MM-DD HI24:MI:SS��ʽ���ַ���
	 * @param key
	 * @return
	 */
	public String getDateTimeString(String key){
		String value = get(key);
		value = value.length()>19?value.substring(0,19):value;
		return value;
	}
	
	/**
	 * ���ƶ���
	 * @return
	 */
    public Object deepClone() 
    {
        try
        {
         // ������д������
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(this);
            // �����������
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (oi.readObject());
        }
        catch(Exception t)
        {
            t.printStackTrace();
        }
        return this;
    }

}
