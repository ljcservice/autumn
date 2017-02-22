package com.hitzd.his.DDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.hitzd.DBUtils.CommonMapper;
import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;
import com.hitzd.his.Utils.DictCache;

public class DDDUtils  
{
	
	public static long HoursBetween(String Start, String Stop)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
		    Date beginTime = df.parse(Start);
		    Date endTime = df.parse(Stop);
		    long dayInterval = 0;
		    dayInterval = (endTime.getTime() - beginTime.getTime()) / (24 * 60 * 60 * 1000) + 1;
		    // ���쿪ҩ�������������1��
		    dayInterval = dayInterval == 0 ? 1 : dayInterval; 
		    return dayInterval * 24;
		}
		catch (Exception ex)
		{
			return 0;
		}
		
	}
	
	public static String getDrugDDDUnit(String DDD_ID, JDBCQueryImpl query)
	{
		String sql = "select * from dddd where DDD_ID = '" + DDD_ID + "'";
		@SuppressWarnings("unchecked")
		List<String> listx = (List<String>)query.query(sql, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException 
			{
				return rs.getString("DDD_Value");
			}
		});
		if (listx.size() > 0)
			return listx.get(0);
		else return "";
	}
	
	public static String getAdministrationStdCode(String Administration, JDBCQueryImpl query)
	{
		String sql = "select * from comm.Administration_Dict where administration_name = '" + Administration + "'";
		@SuppressWarnings("unchecked")
		List<TCommonRecord> list = query.query(sql, new CommonMapper());
		if (list.size() > 0)
		{
			TCommonRecord cr = list.get(0);
			if (cr.get("IS_Injection").equals("1"))
			{
				return "P";
			}
		}
		return "O";
	}
	
	/**
	 * ����DDD����һ�ַ������÷���ֻ����ҩƷ�е�����֮�ȣ������ǵ�λ�����Ǽٶ��Ǽǵ�DDD��λ�Ͱ�ҩ�г��ֵĵ�λ��һ�µ�
	 * ��˼���ĺô��ǲ���Ҫ���е�λת��������Ҫ��ҽԺ����дDDD��ʱ�򣬽����ʹ涨��DDD��λת����ʵ��ʹ���еĵ�λ��
	 * ������
	 *     ҩƷA�����ʹ涨DDDΪ3.6g����ʵ��ʹ��ʱ���ܵ�λΪ֧��ÿ֧��ҩƷA1.8g��������ҩƷAÿ����������2֧�����ҩƷ�������д2���ɣ�
	 *     ����ԭ���ķ�ʽ��ҩƷ�������д3.6��ͬʱҪ��д��λת����֧->�˵�ת����λΪ1.8g/֧�����㸴�ӡ�
	 *     
	 * �پ���
	 *     ҩƷB�����ʹ涨DDDΪ2g��ʵ��ʹ�õ�λΪ��λ��ÿ100��λ��ҪҩƷB 0.8g����ˣ�ҩƷBÿ����������250��λ��ҩƷ�������д250���ɡ�
	 *     
	 * @param DrugCode
	 * @param DrugSpec
	 * @param DrugUnits
	 * @param FirmID
	 * @param Amount
	 * @param Costs
	 * @return
	 */
	public static double CalcDDDEx(String DrugCode, String DrugSpec, String DrugUnits, String FirmID, String Amount, String Costs)
	{
		double dddValue        = 0;
		TCommonRecord DrugInfo = DictCache.getNewInstance().getDrugInfoByCodeUnitSpec(DrugCode, DrugUnits, DrugSpec);
		if (DrugInfo == null)
		{
			String s = "ҩƷ��" + DrugCode + " " + DrugSpec + " " + DrugUnits + " δ���ҵ���Ӧ��¼�����ܼ���DDD";
			System.out.println(s);
			return 0;
		}
		double Total           = Integer.parseInt(Amount);
		JDBCQueryImpl query    = DBQueryFactory.getQuery("PDSS");
	    @SuppressWarnings("unchecked")
		List<TCommonRecord> crList = query.query("select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "'", new CommonMapper());
	    if (crList.size() > 0)
	    {
	    	TCommonRecord crAntiDrug = crList.get(0);
	    	double dddUnit = crAntiDrug.getDouble("DDD_Value");
	    	if (DrugUnits.equalsIgnoreCase(crAntiDrug.get("DDD_Unit")))
	    	{
		    	if (dddUnit == 0)
		    		return 0;
		    	dddValue = Total /dddUnit;
	    	}
	    	else
	    	if (DrugUnits.equalsIgnoreCase(crAntiDrug.get("DDD_Package_Unit")))
	    	{
		    	if (dddUnit == 0)
		    		return 0;
		    	dddValue = Total * crAntiDrug.getInt("DDD_Package_Value") / dddUnit;
	    	}
	    	else
	    	{
	    		if (dddUnit == 0)
	    		{
	    			String s = "ҩƷ��" + DrugCode + " " + DrugSpec + " " + DrugUnits + " δ���ҵ���Ӧ��¼�����ܼ���DDD";
	    			System.out.println(s);
	    			return 0;
	    		}
		    	dddValue = Total /dddUnit;
	    	}
	    }		
		return dddValue;
	}
	
	@SuppressWarnings("unchecked")
	public static double CalcDDD(String DrugCode, String DrugSpec, String DrugUnits, String FirmID, String Amount, String Costs)
	{
		double dddValue        = 0;
		JDBCQueryImpl query    = DBQueryFactory.getQuery("PDSS");
		List<TCommonRecord> crList = query.query("select * from Drug_Map where Drug_No_Local = '" + DrugCode + "' and Drug_Spec = '" + DrugSpec + "' and IS_ANTI = 1", new CommonMapper());
		if (crList.size() > 0)
	    {
	    	TCommonRecord crAntiDrug = crList.get(0);
	    	//2014-04-30 liujc �޸�  �������� DDD_PER_UNIT �ֶ�   ����dddʱ�� ��Ҫ����������������Ϊ�˲�Ӱ��ҽԺ���ݹʴ˴�����������Ѿ�ʵʩ��ҽԺ 
	    	// ���ǵط�ҽԺ���ݿ����û�� Dose_Per_Unit �� Dose_Units 
	    	double DosePerUnit = crAntiDrug.getDouble("DDD_PER_UNIT") != 0 ? crAntiDrug.getDouble("DDD_PER_UNIT") : crAntiDrug.getDouble("Dose_Per_Unit");
			// ȡ�����������ĵ�λ�������ǿˡ����ˡ�g��mg��ml����������λ����λ����u����iu������������
			String DoseUnit    = crAntiDrug.getDouble("DDD_PER_UNIT") != 0 ? crAntiDrug.get("DDD_UNIT") : crAntiDrug.get("Dose_Units").trim();
	    	double dddUnit     = crAntiDrug.getDouble("DDD_Value");
	    	// ȡ��ÿ��λ�Ļ�������
			double Total       = DosePerUnit * Double.parseDouble(Amount);
	    	if (dddUnit == 0 || dddUnit < 0)
	    		return 0;
	    	// ��λͳһת����mg��ĿǰӦ�������ֵ�λ:�ˡ���λ������
	    	if (DoseUnit.equals("��") || DoseUnit.equals("����") || 
	    		DoseUnit.equalsIgnoreCase("g") || DoseUnit.equalsIgnoreCase("mg"))
	    	{
	    		// ����ת��Ϊ��
	    		if (DoseUnit.equals("����") || DoseUnit.equalsIgnoreCase("mg"))
	    		{
	    			Total = Total / 1000;
	    		}
	    	}
			else
			{
				return 0;
			}
	    	dddValue = Total /dddUnit;
	    }		
		return dddValue;
	}
}
