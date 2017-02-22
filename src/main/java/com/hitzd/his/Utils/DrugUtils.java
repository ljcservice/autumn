package com.hitzd.his.Utils;

import java.util.ArrayList;

import java.util.List;

import com.hitzd.DBUtils.CommonMapper;
import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;

/**
 * ҩƷ������Ϣ
 * @author jingcong
 *
 */
public class DrugUtils 
{
    /**
     * ���ݹ���ȷ��ҩƷ����
     * @param DrugCode
     * @return
     *   1 ��ʾ����ҩƷ����ע���
     *   2 ��ʾ����ҩƷ������ҩ
     *   3 ��ʾ����ҩƷ����������ʹҩ
     *   4 ��ʾ����ҩƷ���Ǿ���ҩ    
     *   5 ��ʾ����ҩƷ���ǿ���ҩ      
     *   0 ��ʾ���ܸ���ҩƷ����
     *   
     *  PZ  Ʒ�֣�ҩƷ��ǰ����λ���Ա�ʶһ��ҩ
     *  ZS  ע�������ҩ;��������Ϊ����ʱ��ע���
     *  ZY  �г�ҩҩƷ����ʲô��ͷΪ��ҩ
     *  MZ  ������ʹҩ��ҩƷ�뿪ͷΪ��ֵʱΪ����ҩ
     *  JS  ����ҩ��ͬ��
     *  KJ  ����ҩ��ͬ��
     */
	
	/**
	 * �Ƿ���ע���
	 * 
	 */
    public static boolean isZSDrug(String DrugCode, String DrugSpec)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "' and IS_INJECTION = '1'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
	}
	
    /**
     * �Ƿ�������ҩ
     * @param DrugCode
     * @param DrugSpec
     * @return
     */
    public static boolean isExternalDrug(String DrugCode, String DrugSpec)
    {
    	JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "' and IS_EXTERNAL = '1'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
    }
    
    /**
     * �������ҩ����б�
     * @return
     */
    public static String getExternalDrugNos()
    {
    	JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
    	try
    	{
    		String sql = "select drug_no_local from drug_map where IS_EXTERNAL = '1'";
    		@SuppressWarnings("unchecked")
			List<TCommonRecord> list = query.query(sql, new CommonMapper());
            StringBuffer sbfr = new StringBuffer();
    		for (TCommonRecord cr : list)
    		{
    			sbfr.append("'").append(cr.get("drug_no_local")).append("',");
    		}
            if(sbfr.length() > 0)
            {
                sbfr.deleteCharAt(sbfr.length() - 1 );
            }
            return sbfr.toString();
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		query = null;
    	}
    	return "";
    }
    
    
    /**
     * ��ÿ�����ҩ�����б�
     * @return
     */
    public static String getAntiAllergyDrugNos()
    {
    	JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
    	try
    	{
    		String sql = "select drug_no_local from drug_map where IS_Allergy = '1'";
    		@SuppressWarnings("unchecked")
			List<TCommonRecord> list = query.query(sql, new CommonMapper());
            StringBuffer sbfr = new StringBuffer();
    		for (TCommonRecord cr : list)
    		{
    			sbfr.append("'").append(cr.get("drug_no_local")).append("',");
    		}
            if(sbfr.length() > 0)
            {
                sbfr.deleteCharAt(sbfr.length() - 1 );
            }
            return sbfr.toString();
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		query = null;
    	}
    	return "";
    }
        
    /**
     * ����ҩƷ�Ķ�������
     * @param DrugCode
     * @param DrugSpec
     * @return ����ֵΪ��ҩ����ҩ����һ�����������ء����䡢��Σ�����顣
     */
    public static String getDrugToxiProperty(String DrugCode, String DrugSpec)
    {
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			if (cr == null)
				return "";
			else
				return cr.get("Toxi_Property");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return "";
    }
    
    /**
     * ��ҩ
     */
    @Deprecated
    public static String getDYList()
    {
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where IS_POISON = '1' ";
			@SuppressWarnings("unchecked")
			List<TCommonRecord> list = query.query(sql, new CommonMapper());
			sql = null;
            StringBuffer sbfr = new StringBuffer();
    		for (TCommonRecord cr : list)
    		{
    			sbfr.append("'").append(cr.get("drug_no_local")).append("',");
    		}
            if(sbfr.length() > 0)
            {
                sbfr.deleteCharAt(sbfr.length() - 1 );
            }
            return sbfr.toString();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return "";
    }
    
    /**
     * �Ƿ�����ҩע���
     * @param drugCode
     * @return
     */
    public static boolean isCenterDrugZS(String DrugCode, String DrugSpec)
    {
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "' and IS_INJECTION = '1' and IS_CHINESEDRUG = '1'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
    }
	
    /**
     * �Ƿ�����ҩ
     * @param DrugCode
     * @return
     */
	public static boolean isZYDrug(String DrugCode)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and IS_PATENTDRUG = '1'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
	}
	
	/**
	 * �Ƿ�������ҩ
	 * @param DrugCode
	 * @return
	 */
	@Deprecated
	public static boolean isMZDrug(String DrugCode)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and IS_HABITFORMING = '1' ";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
	}
	
	/**
	 * �Ƿ��Ǿ�����ҩ
	 * @param DrugCode
	 * @return ���ؾ�һ������
	 */
	@Deprecated
	public static String isJSDrug(String DrugCode, String DrugSpec)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "' and TOXI_PROPERTY like '%��%'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			if (cr == null)
				return "";
			else
				return cr.get("TOXI_PROPERTY");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return "";
		/*
		String value = Config.getParamValue("JS");
        if (value == null) return false;
        String[] v = value.split(";");
        for (String x : v)
        {
            if (DrugCode.toUpperCase().startsWith(x.toUpperCase()))
                return true;
        }
        return false;
        */
	}
	
	/**
	 * �Ƿ���ҽ����ҩ
	 * @param DrugCode
	 * @param DrugSpec
	 * @return 0�Ƿ�ҽ����1�Ǽ���ҽ����2������ҽ��
	 */
	public static String isMedcareDrug(String DrugCode, String DrugSpec)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			if (cr == null)
				return "";
			else
				return cr.get("is_medcare");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return "";
	}

    /* ���һ���ҩ��*/
    private static List<TCommonRecord> Base = new ArrayList<TCommonRecord>();
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllBaseDrug()
	{
	    if(Base.size() > 0 ) return Base;
	    JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
	    CommonMapper  cmr   = new CommonMapper();
	    try
	    {
	    	String sql = "select * from Drug_Map where  is_basedrug='1'";
	        Base = query.query(sql, cmr);
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        query = null;
	        cmr   = null;
	    }
	    return Base;
	}
	/**
	 * �Ƿ��ǹ��һ���ҩƷĿ¼�е�ҩƷ
	 * @param DrugCode
	 * @param DrugSpec
	 * @return
	 */
	public static boolean isCountryBase(String DrugCode, String DrugSpec)
	{
		if(Base.size() <= 0 ) getAllBaseDrug();
		try
		{
			for(TCommonRecord t : Base)
			{
			    if((DrugCode + DrugSpec).equals(t.get("drug_no_local") + t.get("drug_spec")))
			    {
			        return true;
			    }
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * �Ƿ����˷ܼ�
	 * @param DrugCode
	 * @return
	 */
	public static boolean isExhilarantDrug(String DrugCode)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and is_Exhilarant = '1'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
	
	}
	
	/**
	 * �Ƿ��ǿڷ��Ƽ�
	 * @param DrugCode
	 * @param DrugSpec
	 * @return
	 */
	public static boolean IsOralDrug(String DrugCode, String DrugSpec)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and is_oral = '1'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
		
	}
	
	/**
	 *  �Ƿ��׼ע���ҩƷ 
	 * @param drugCode
	 * @return ��ȡ��
	 */
	@Deprecated
	public static boolean isZSNormDrug(String drugCode)
	{
	    TCommonRecord drug = DictCache.getNewInstance().getDrugDictInfo(drugCode);
        if("���".equals(drug.get("drug_form")))
        {
            return true;    
        }
        return false;
	}
	
	/* ����ҩ�Ｏ��  */
	private static List<TCommonRecord> KJDrug = new ArrayList<TCommonRecord>();
	@SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllKJDrug()
	{
	    if(KJDrug.size() > 0 ) return KJDrug;
	    JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
	    CommonMapper  cmr   = new CommonMapper();
	    try
	    {
	        String sql = "select * from Drug_Map where is_anti = '1'";
	        KJDrug = query.query(sql, cmr);
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        query = null;
	        cmr   = null;
	    }
	    return KJDrug;
	}
	
	/**
	 * ���ؿ���ҩ�б� 
	 * @return
	 */
	public static String getKJDrugList()
	{
	    if(KJDrug.size() <= 0 ) getAllKJDrug();
	    StringBuffer sb = new StringBuffer();
	    for(TCommonRecord t : KJDrug)
	    {
	        sb.append("'").append(t.get("drug_no_local")).append("',");
	    }
	    if(sb.length() > 1) sb.deleteCharAt(sb.length() - 1 );
	    return sb.toString();
	}
	
	/**
	 * �Ƿ��ǿ���ҩ
	 * @param DrugCode
	 * @return
	 */
	public static boolean isKJDrug(String DrugCode)
	{
	    if(KJDrug.size() <= 0 ) 
	    {
	        getAllKJDrug();
	    }
		try
		{
			for(TCommonRecord t : KJDrug)
			{
			    if(DrugCode.equals(t.get("drug_no_local")))
			    {
			        return true;
			    }
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * �ж��Ƿ��ǽ�������
	 * @param OperName
	 * @param OperID
	 * @return
	 */
	public static boolean isJROperation(String OperName, String OperID)
	{
		String value = Config.getParamValue("JROperation");
		if (value == null) return false;
		String[] v = value.split(";");
		for (String x : v)
		{
			if (OperName.equalsIgnoreCase(x))
				return true;
		}
		return false;
	}
	
	/**
	 * �Ƿ������⼶����ҩ
	 * @param DrugCode
	 * @param DrugSpec
	 * @return 
	 */
	public static boolean isSpecDrug(String DrugCode, String DrugSpec)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where is_anti = '1' and Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "' and anti_level = '3'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
		/*
		String value = Config.getParamValue("SpecDrug");
        if (value == null) return false;
        String[] v = value.split(";");
        for (String x : v)
        {
            if (DrugCode.toUpperCase().startsWith(x.toUpperCase()))
                return true;
        }
        return false;
        */
	}
	
	/**
	 * ����ҩƷ�Ǽ�
	 * @param DrugCode
	 * @param DrugSpec
	 * @return 1�Ƿ�������ҩ��2��������ҩ��3��������ҩ
	 */
	public static String getDrugLevel(String DrugCode, String DrugSpec)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where is_anti = '1' and  Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			if (cr == null)
				return "";
			else
				return cr.get("anti_level");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return "";
	}
	
	/**
	 * �Ƿ���������ҩ
	 * @param DrugCode
	 * @param DrugSpec
	 * @return
	 */
	public static boolean isLimitDrug(String DrugCode, String DrugSpec)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
		try
		{
			String sql = "select * from Drug_Map where is_anti = '1' and  Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "' and anti_level = '2'";
			TCommonRecord cr = (TCommonRecord)query.queryForObject(sql, new CommonMapper());
			sql = null;
			return cr != null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			query = null;
		}
		return false;
		/*
		String value = Config.getParamValue("LimitDrug");
        if (value == null) return false;
        String[] v = value.split(";");
        for (String x : v)
        {
            if (DrugCode.toUpperCase().startsWith(x.toUpperCase()))
                return true;
        }
        return false;
        */
	}
	
	/**
	 * ����ҩƷ����
	 * @param DrugCode
	 * @param DrugSpec
	 * @param administration
	 * @return 1��ע�����2����ҩ��3������ҩ��4�Ǿ���ҩ��5�ǿ���ҩ
	 */
    public static String getDrugType(String DrugCode, String DrugSpec, String administration)
    {
    	if (isZSDrug(DrugCode, DrugSpec)) return "1";
    	else if (isZYDrug(DrugCode)) return "2";
    	else if (isMZDrug(DrugCode)) return "3";
    	else if (isJSDrug(DrugCode, DrugSpec).length() > 0) return "4";
    	else if (isKJDrug(DrugCode)) return "5";
    	else return "0";
    }
    
    /**
     * �ù���ָ����ҽ������ҩ;��Ϊ��ֵʱ����ʶҩƷ����������ʹ�õ�
     * @param administration
     * @return
     */
    public static boolean isUseMiddle(String administration)
    {
    	String[] values = Config.getParamValue("Use_In_Oper").split(";");
    	for (String v: values)
    	{
    		if (v.trim().equalsIgnoreCase(administration.trim()))
    			return true;
    	}
    	return false;
    }
    
    /**
     * �ù���ָ����ҽ����Item_Class��ҽ����Щ����Ϊҽ������ҩҽ�� 
     * @param ItemClass
     * @return
     */
    public static boolean isDrugInOrder(String ItemClass)
    {
    	String[] values = Config.getParamValue("Drug_In_Order").split(";");
    	for (String v : values)
    	{
    		if (v.trim().equalsIgnoreCase(ItemClass))
    			return true;
    	}
    	return false;
    }
    
    /**
     * ��Ⱦ��־
     * @param DiagnosisType
     * @return
     */
    public static boolean isInfect(String DiagnosisType)
    {
    	String[] values = Config.getParamValue("Infect_Indi").split(";");
    	for (String v: values)
    	{
    		if (v.trim().equalsIgnoreCase(DiagnosisType))
    			return true;
    	}
    	return false;
    }
    
    /**
     * ����ҩƷ�� drug_map
     */
    public static void ReSetToxiProperty()
    {
        DM.clear();
        DY.clear();
        MY.clear();
        YJ.clear();
        EJ.clear();
        FS.clear();
        GZ.clear();
        KJDrug.clear();
        Base.clear();
    }

    /* ����ҩ*/
    private static List<TCommonRecord> DM = new ArrayList<TCommonRecord>();
    /**
     * �������ж���ҩ  
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllDM()   
    {
        if(DM.size() > 0 )
            return DM;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper cmr    = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where Is_Poison = '1' and IS_HABITFORMING = '1' ";
            DM = query.query(sql, cmr);
            return DM;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally 
        {
            query = null;
            cmr   = null;
        }
        return DM ;
    }
    
    /**
     * 
     * ������ҩ �б�ҩ�� 'code','code','code' ����cache�� 
     * @return
     */
    public static String getDMList()
    {
        if(DM.size() <= 0 ) getAllDM();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(DM.size() < 0 ) return "";
            for(TCommonRecord t : DM)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    /**
     * �����Ƿ�Ϊ����ҩ 
     * @param Code
     * @return
     */
    public static boolean isDMDrug(String drugCode )
    {
        if(DM.size() <= 0 ) getAllDM();
        for(TCommonRecord t : DM)
        {
            if(drugCode.equals(t.getObj("DRUG_NO_LOCAL")))
                return true;
        }
        return false;
    }
    
    /* ��ҩ */
    private static List<TCommonRecord> DY = new ArrayList<TCommonRecord>();
    /**
     * ������ҩ �� drug_map �������
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllDY()
    {
        
        if(DY.size() > 0 )
            return DY;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper cmr = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where IS_POISON = '1' " ;
            DY = query.query(sql, cmr);
            return DY ;
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            query  = null;
            cmr    = null;
        }
        return DY;
    }
    
    /**
     * 
     * ������ҩ �б�ҩ�� 'code','code','code' ����cache�� 
     * @return
     */
    public static String getDYList1()
    {
        if(DY.size() <= 0 ) getAllDY();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(DY.size() < 0 ) return "";
            for(TCommonRecord t : DY)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    /**
     * �����Ƿ�Ϊ��ҩ 
     * @param Code
     * @return
     */
    public static boolean isDDrug(String drugCode)
    {
        if(DY.size() <= 0 ) getAllDY();
        for(TCommonRecord t : DY)
        {
            if(drugCode.equals(t.getObj("DRUG_NO_LOCAL")))
                return true;
        }
        return false;
    }
    
    /* ��ҩ */
    private static List<TCommonRecord> MY = new ArrayList<TCommonRecord>();
    
    /**
     * ������ҩ �� drug_map �������
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllMY()
    {
        if(MY.size() > 0 )
            return MY;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper cmr    = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where IS_HABITFORMING = '1' ";
            MY = query.query(sql, cmr);
            return MY;
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            cmr   = null;
        }
        return MY;
    }
    
    /**
     * ������ҩ �б�ҩ�� 'code','code','code'
     * @return
     */
    public static String getMYList()
    {
        if(MY.size() <= 0 ) getAllMY();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(MY.size() < 0 ) return "";
            for(TCommonRecord t : MY)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    /**
     * �����Ƿ�Ϊ��ҩ 
     * @param Code
     * @return
     */
    public static boolean isMDrug(String drugCode)
    {
        if(MY.size() <= 0 ) getAllMY();
        for(TCommonRecord t : MY)
        {
            if(drugCode.equals(t.getObj("DRUG_NO_LOCAL")))
                return true;
        }
        return false;
    }
    
    /* һ�ྫ */
    private static List<TCommonRecord> YJ = new ArrayList<TCommonRecord>();
    
    /**
     * ����һ�ྫ��ҩ�� �� drug_map �������
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllYJ()
    {
        if(YJ.size() > 0 )
            return YJ;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper  cmr   = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where IS_PSYCHOTIC = '1' ";
            YJ = query.query(sql, cmr);
            return YJ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            cmr   = null;
        }
        return YJ;
    }
    
    /**
     * ����һ�ྫ���б�ҩ�� 'code','code','code'
     * @return
     */
    public static String getYJList()
    {
        if(YJ.size() <= 0 ) getAllYJ();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(YJ.size() < 0 ) return "";
            for(TCommonRecord t : YJ)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    /**
     * �����Ƿ�Ϊһ�ྫ��ҩ�� 
     * @param Code
     * @return
     */
    public static boolean isYJDrug(String drugCode)
    {
        if(YJ.size() <= 0 ) getAllYJ();
        for(TCommonRecord t : YJ)
        {
            if(drugCode.equals(t.getObj("DRUG_NO_LOCAL")))
                return true;
        }
        return false;
    }
    
    /* ���ྫ */
    private static List<TCommonRecord> EJ = new ArrayList<TCommonRecord>();
    
    /**
     * ���� ���ྫ��ҩ�� 
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllEJ()
    {
        if(EJ.size() > 0 )
            return EJ;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper  cmr   = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where IS_PSYCHOTIC = '2' ";
            EJ = query.query(sql, cmr);
            return EJ;
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        finally
        {
            query = null;
            cmr   = null;
        }
        return EJ;
    }
    
    /**
     * ���ض��ྫ���б�ҩ�� 'code','code','code'
     * @return
     */
    public static String getEJList()
    {
        if(EJ.size() <= 0 ) getAllEJ();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(EJ.size() < 0 ) return "";
            for(TCommonRecord t : EJ)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    /**
     * �����Ƿ�Ϊ���ྫ��ҩ�� 
     * @param Code
     * @return
     */
    public static boolean isEJDrug(String drugCode)
    {
        if(EJ.size() <= 0 ) getAllEJ();
        for(TCommonRecord t : EJ)
        {
            if(drugCode.equals(t.getObj("DRUG_NO_LOCAL")))
                return true;
        }
        return false;
    }
    
    /* ���� */
    private static List<TCommonRecord> FS = new ArrayList<TCommonRecord>();
    
    /**
     * ���� ����ҩƷ drug_map ����
     * @return
     */
    @SuppressWarnings ("unchecked")
    /**
     *  �������з���ҩ�� drug_map ����
     */
    public static List<TCommonRecord> getAllFS()
    {
        if(FS.size() > 0 )
            return FS;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper  cmr   = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where IS_RADIATION = '1' ";
            FS = query.query(sql, cmr);
            return FS;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            cmr   = null;
        }
        return FS;
    }
    
    /**
     * ���ط����б�ҩ�� 'code','code','code'
     * @return
     */
    public static String getFSList()
    {
        if(FS.size() <= 0 ) getAllFS();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(FS.size() < 0 ) return "";
            for(TCommonRecord t : FS)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    /**
     * �����Ƿ�Ϊ����ҩ�� 
     * @param Code
     * @return
     */
    public static boolean isFSDrug(String drugCode)
    {
        if(FS.size() <= 0 ) getAllFS();
        for(TCommonRecord t : FS)
        {
            if(drugCode.equals(t.getObj("DRUG_NO_LOCAL")))
                return true;
        }
        return false;
    }
    
    /* ����  */
    private static List<TCommonRecord> GZ = new ArrayList<TCommonRecord>();
    /**
     * ���� ����ҩƷ drug_map ����
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllGZ()
    {
        if(GZ.size() > 0 )
            return GZ;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper  cmr   = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where IS_PRECIOUS = '1' ";
            GZ = query.query(sql, cmr);
            return GZ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            cmr   = null;
        }
        return GZ;
    }

    /**
     * ���ع���ҩ���б�   'code','code','code'
     * @return
     */
    public static String getGZList()
    {
        if(GZ.size() <= 0 ) getAllGZ();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(GZ.size() < 0 ) return "";
            for(TCommonRecord t : GZ)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    /**
     * �����Ƿ�Ϊ����ҩ�� 
     * @param Code
     * @return
     */
    public static boolean isGZDrug(String drugCode)
    {
        if(GZ.size() <= 0 ) getAllGZ();
        for(TCommonRecord t : GZ)
        {
            if(drugCode.equals(t.getObj("DRUG_NO_LOCAL")))
                return true;
        }
        return false;
    }
    
    /* ����  */
    private static List<TCommonRecord> XZ = new ArrayList<TCommonRecord>();
    
    /**
     * ���� ����ҩƷ drug_map ����
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllXZ()
    {
        if(XZ.size() > 0 )
            return XZ;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper  cmr   = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where is_anti='1' and  anti_level='2'";
            XZ = query.query(sql, cmr);
            return XZ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            cmr   = null;
        }
        return XZ;
    }

    /**
     * ��������ҩ���б�   'code','code','code'
     * @return
     */
    public static String getXZList()
    {
        if(XZ.size() <= 0 ) getAllXZ();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(XZ.size() < 0 ) return "";
            for(TCommonRecord t : XZ)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }
    
    
    /* ���� */
    private static List<TCommonRecord> TS = new ArrayList<TCommonRecord>();
    /**
     * ���� ����ҩƷ drug_map ����
     * @return
     */
    @SuppressWarnings ("unchecked")
    public static List<TCommonRecord> getAllTS()
    {
        if(TS.size() > 0 )
            return TS;
        JDBCQueryImpl query = DBQueryFactory.getQuery("PDSS");
        CommonMapper  cmr   = new CommonMapper();
        try
        {
            String sql = "select * from drug_map where  is_anti='1' and  anti_level='3'";
            TS = query.query(sql, cmr);
            return TS;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            cmr   = null;
        }
        return TS;
    }

    /**
     * ��������ҩ���б�   'code','code','code'
     * @return
     */
    public static String getTSList()
    {
        if(TS.size() <= 0 ) getAllTS();
        StringBuffer sql = new StringBuffer();
        try
        {
            if(TS.size() < 0 ) return "";
            for(TCommonRecord t : TS)
            {
                sql.append("'").append(t.get("DRUG_NO_LOCAL")).append("',");
            }
            if(sql.length() > 0 )
                sql.deleteCharAt(sql.length() - 1 );
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return sql.toString();
    }

    /**
     * ȥ��������ظ�����Ϣ 
     * ÿ����ʹ�ú���Ҫ ����һ�� clearDistinctDrugIngerInfo() ��������� ���˵�����
     * @param drug1 ����Ψһȷ��ҩƷ1
     * @param drug2 ����Ψһȷ��ҩƷ2
     * @return 
     * 
     */
    private static List<String> Distinctdrug = new ArrayList<String>();
    public static boolean isDistinctDrugIngerInfo(String drug1 , String drug2)
    {
        try
        {   
            if(Distinctdrug.contains(new String(drug2 + drug1)))
            {
                return true;
            }
            Distinctdrug.add(drug1 + drug2);
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * ���ȥ�ؼ�¼
     * 
     */
    public static void clearDistinctDrugIngerInfo()
    {
        Distinctdrug.clear();
    }
}
