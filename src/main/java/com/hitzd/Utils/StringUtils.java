package com.hitzd.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hitzd.DBUtils.CommonMapper;
import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;
import com.hitzd.his.Utils.Config;
import com.hitzd.his.casehistory.helper.CaseHistoryFactory;
import com.hitzd.his.casehistory.helper.CaseHistoryHelperUtils;
import com.hitzd.his.casehistory.helper.ICaseHistoryHelper;

public class StringUtils 
{

	public static String styleQBy�� = "��###,##0";
	public static String styleQNo�� = "###,##0";
	public static String styleBy��  = "��##0";
	public static String styleNo��  = "##0";
	public static String styleRate  = "##0";
	/**
	 * 
	 * @param value  ��Ҫ��ʽ������ֵ
	 * @param decCount  ��Ҫ����λС����
	 * @param style  ��ʽ������ʽ
	 * @return
	 */
	public static String formatDouble(double value,int decCount,String style)
	{
		for(int i = 0; i < decCount; i++)
		{
			if(i == 0)
				style +=".";
			style += "0";
		}
		DecimalFormat df = new DecimalFormat(style);
		return df.format(value);
	}
	/**
	 * ���ظ�ʽ����ֵ��ʽ000,000.00
	 * @param value
	 * @return
	 */
	public static String formatQBy��(double value)
	{
		return formatDouble(value,2,styleQBy��);
	}
	/**
	 * ���ظ�ʽ����ֵ��ʽ000,000.00
	 * @param value
	 * @return
	 */
	public static String formatQNo��(double value)
	{
		return formatDouble(value,2,styleQNo��);
	}
	/**
	 * ���ظ�ʽ����ֵ��ʽ0.00
	 * @param value
	 * @return
	 */
	public static String formatBy��(double value)
	{
		return formatDouble(value,2,styleBy��);
	}
	/**
	 * ���ظ�ʽ����ֵ��ʽ0.00
	 * @param value
	 * @return
	 */
	public static String formatNo��(double value)
	{
		return formatDouble(value,2,styleNo��);
	}
	/**
	 * ���ر����ٷֱ�
	 * @param one ������
	 * @param two ����
	 * @param decCount ��λС����
	 * @return
	 */
	public static String getRate(double one,double two,int decCount,String sytle)
	{
		String zero = "0";
		for(int i = 0; i < decCount; i++)
		{
			if(i == 0)
			{
				sytle += ".";
				zero += ".";
			}
			sytle += "0";
			zero +="0";
		}
		if(checkZero(one)||checkZero(two))
			return zero + "%";
		DecimalFormat df = new DecimalFormat(sytle);
		return df.format(one/two * 100)+"%";
	}
	/**
	 * ���ر����ٷֱȣ�Ĭ����λС����
	 * @param one ������
	 * @param two ����
	 * @return
	 */
	public static String getRate(double one,double two)
	{
		return getRate(one, two, 2,styleRate);
	}
	/**
	 * ����һ��double�������ݣ����ظ���ֵ*100��С���㱣����λ 
	 * @param value
	 * @return �紫��99���򷵻�99*100+%=9900%
	 */
	public static String formatRate(double value)
	{
		if(checkZero(value))
			return "0.00%";
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(value * 100)+"%";
	}
	/**
	 * ��鴫�����ֵ�Ƿ�Ϊ0
	 * @param value
	 * @return ���򷵻�true ���򷵻�false
	 */
	public static boolean checkZero(double value)
	{
		if(value == 0)
			return true;
		else
			return false;
	}
	
	
    public static String DTFormat = "yyyy-mm-dd hh24:mi:ss";
    public static String getDateSQL(String src, String fmt)
    {
    	if ((src == null) || (src.length() == 0))
    		src = "1981-01-01 00:00:00";
    		//return "To_Date('1981-01-01 00:00:00', '" + fmt + "')";
    	if (src.length() > 19)
    		src = src.substring(0, 19);
    	if (fmt.length() < 19)
    		src = src.substring(0, fmt.length());
    	return "To_Date('" + src + "', '" + fmt + "')";
    			
    }
    
    public static void execCF(List<TCommonRecord> supplier, TCommonRecord tcr)
	{
		int center = 0;
		int Firmlength = 0;
		String firm_id = "";
		String configSupplier = Config.getParamValue("supplier_id");
		if(supplier != null && supplier.size()>0)
		{
			for(TCommonRecord firm : supplier)
			{
				if("1".equals(configSupplier)&&tcr.get("item_spec").contains(firm.get("supplier_id")))
				{
					if(firm.get("supplier_id").length()>Firmlength)
					{
						Firmlength = firm.get("supplier_id").length();
						firm_id = firm.get("supplier_id");
					}
				}
				else
				if("2".equals(configSupplier))
				{
					if((tcr.get("item_code") + tcr.get("item_spec")).equals(firm.get("drug_code") + firm.get("drug_spec") + firm.get("firm_id")))
					{
						tcr.set("Firm_ID",   firm.get("firm_id"));
						tcr.set("item_spec", firm.get("drug_spec"));
						return ;
					}
				}
			}
			if(Firmlength != 0)
			{
				center = tcr.get("item_spec").lastIndexOf(firm_id);
				tcr.set("Firm_ID", tcr.get("item_spec").substring(center, tcr.get("item_spec").length()));
				tcr.set("item_spec", tcr.get("item_spec").substring(0, center));
			}
		}
	}
	public static List<TCommonRecord> getSupplier()
	{
		System.out.println("��ѯ���Ҷ��ձ����Ҽ�д��");
		JDBCQueryImpl his = DBQueryFactory.getQuery("HIS");
		ICaseHistoryHelper chhr = CaseHistoryFactory.getCaseHistoryHelper();
		List<TCommonRecord> lsGroups = new ArrayList<TCommonRecord>();
		List<TCommonRecord> resultList = null;
		String configSupplier = Config.getParamValue("supplier_id");
		if("1".equals(configSupplier))
		{
			try {
				resultList = chhr.fetchDrugSupplierCatalog2CR("supplier_id", null, null, null, null);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			Map<String,TCommonRecord> tempMap = new HashMap<String,TCommonRecord>();
			for(TCommonRecord data:resultList) {
				tempMap.put(data.get("supplier_id"), data);
			}
			return new  ArrayList<TCommonRecord>(tempMap.values());
			//sql.append("select distinct trim(t.supplier_id) supplier_id  from DRUG_SUPPLIER_CATALOG t where t.supplier_id is not null");
		}
		else if("2".equals(configSupplier))
		{
			TCommonRecord group = CaseHistoryHelperUtils.genGroupCR("drug_code");
			lsGroups.add(group);
			group = CaseHistoryHelperUtils.genGroupCR("drug_spec");
			lsGroups.add(group);
			group = CaseHistoryHelperUtils.genGroupCR("firm_id");
			lsGroups.add(group);
			String strSQL = chhr.genSQL("drug_code,drug_spec,firm_id", "comm.drug_price_list", null, lsGroups, null);
			return his.query(strSQL, new CommonMapper());
//			sql.append("select t.drug_code,t.drug_spec,t.firm_id from comm.drug_price_list t group by t.drug_code,t.drug_spec,t.firm_id");
		}
		else
		{
			System.out.println("û�в�ֹ���볧�ҵĲ���ֵ������Ҫ���");
			return null;
		}
//		return his.query(sql.toString(), new CommonMapper());
	}
}
