package com.hitzd.his.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;
import com.hitzd.his.casehistory.helper.CaseHistoryFactory;
import com.hitzd.his.casehistory.helper.CaseHistoryHelperUtils;
import com.hitzd.his.casehistory.helper.ICaseHistoryHelper;
import com.hitzd.DBUtils.CommonMapper;

/**
 * ������Դ his �����ֵ���
 * @author Administrator
 *
 */
public final class DictCache
{
    /* ҽ�� */
    private static HashMap<String, TCommonRecord>   doctorMap = null;
    /* ����  */
    private static HashMap<String, TCommonRecord>     deptMap = null;
    /* ���� */
    private static HashMap<String, TCommonRecord>     mergeDeptMap = null;
    /* �걾 */
    private static HashMap<String, TCommonRecord>    speciMap = null;
    /* ΢�����ֵ��*/
    private static HashMap<String, TCommonRecord> germCodeMap = null;
    /* ϸ��ҩ���ֵ� */
    private static HashMap<String, TCommonRecord> germSensMap = null;
    /* ҩƷ�ֵ� */
    private static HashMap<String, TCommonRecord> drugCodeMap = null;
    /* ҩƷ�ֵ�  code + units + drug_spec */
    private static HashMap<String, TCommonRecord> drugdictMap = null;
    /* ҩƷ���ֵ� */
    private static HashMap<String, TCommonRecord> drugClassMap = null;
    /* ��ҩ;���ֵ� */
    private static HashMap<String, TCommonRecord>     adminMap = null;
    /* ����ֵ�*/
    private static HashMap<String, TCommonRecord> diagnosisMap = null;
    /* Ƶ���ֵ�*/
    private static HashMap<String, TCommonRecord>   performMap = null;
    /* �ѱ��ֵ�*/
    private static HashMap<String, TCommonRecord>   chargeMap = null;
    /* ����ֵ�*/
    private static HashMap<String, TCommonRecord>   identityMap = null;
    /* �����ֵ�*/
    private static HashMap<String, TCommonRecord>   formMap = null;
    private JDBCQueryImpl hisQuery   = null;
    private JDBCQueryImpl iasQuery   = null;
    private JDBCQueryImpl peaasQuery = null;
    
    private static DictCache singClass = null;
    private DictCache()
    {
        System.out.println("---------------------�������� ");
        doctorMap    = new HashMap<String, TCommonRecord>();
        deptMap      = new LinkedHashMap<String, TCommonRecord>();
        mergeDeptMap = new LinkedHashMap<String, TCommonRecord>();
        speciMap     = new HashMap<String, TCommonRecord>();
        drugCodeMap  = new HashMap<String, TCommonRecord>();
        drugdictMap  = new HashMap<String, TCommonRecord>();
        drugClassMap = new HashMap<String, TCommonRecord>();
        germSensMap  = new HashMap<String, TCommonRecord>();
        germCodeMap  = new HashMap<String, TCommonRecord>();
        adminMap     = new HashMap<String, TCommonRecord>();
        diagnosisMap = new HashMap<String, TCommonRecord>();
        performMap   = new HashMap<String, TCommonRecord>();
        chargeMap    = new HashMap<String, TCommonRecord>();
        identityMap  = new HashMap<String, TCommonRecord>();
        formMap      = new HashMap<String, TCommonRecord>();
        hisQuery     = DBQueryFactory.getQuery("HIS"); 
        //iasQuery     = DBQueryFactory.getQuery("IAS"); 
        peaasQuery   = DBQueryFactory.getQuery("PEAAS");
        /* ���� */
        this.setDept(hisQuery);
        /* ���� */
        this.setMergeDept(hisQuery);
        /* ҽ��������Ϣ������ʱ��ԴΪ�û�ά����ʹ�ù�������û�У����Զ����棬����������ݿ��У����û��Լ�ά��*/
        this.setDoctorCode();
        /* �걾 */
        this.setSpeci(hisQuery);
        /* ΢�����ֵ�� */
        //this.setGermCode(hisQuery);
        /* ϸ��ҩ���ֵ�  */
        //this.setGermSens(hisQuery);
        /* ҩƷ�ֵ�� */
        this.setDrugDict(hisQuery);
        /* ҩƷ���ֵ�� */
        this.setDrugClassDict(hisQuery);
        /* ��ҩ;�� */
        this.setAdmin(hisQuery);
        /* ����ֵ� */
        this.setDiagnosis(hisQuery);
        /* Ƶ���ֵ� */
        this.setPerformMap(hisQuery);
        /* �ѱ��ֵ� */
        this.setChargeMap(hisQuery);
        /* ����ֵ� */
        this.setIdentityMap(hisQuery);
        /* �����ֵ� */
        this.setFormMap(hisQuery);
        hisQuery   = null;
        iasQuery   = null;
        peaasQuery = null;
        System.out.println("---------------------������������ ");
    }

    /**
     *  ����
     * @return
     */
    public static synchronized DictCache getNewInstance()
    {
        if(singClass == null)
        {
            singClass = new DictCache();
        }
        return singClass;
    }

    private void setPerformMap(JDBCQueryImpl hisQuery)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> list = ichh.fetchPerformFreqDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t :list)
            {
                if(!t.get("serial_no").equals(""))
                    performMap.put(t.get("serial_no").toUpperCase(), t);    
            }    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ichh      = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            strFields = null;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    private void setAdmin(JDBCQueryImpl hisQuery)
    {
        
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> list = ichh.fetchAdministrationDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t :list)
            {
                if(!t.get("serial_no").equals(""))
                    adminMap.put(t.get("serial_no").toUpperCase(), t);    
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
    }
    
    private void setDiagnosis(JDBCQueryImpl hisQuery)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> list = ichh.fetchDiagnosisDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t : list)
            {
                TCommonRecord tCom = diagnosisMap.get(t.get("diagnosis_code")) ;
                if(tCom == null) 
                {
                    diagnosisMap.put(t.get("diagnosis_code").toUpperCase(), t);
                }
                else
                {
                    tCom.set("diagnosis_name", tCom.get("diagnosis_name") + ",[" + t.get("diagnosis_name") + "]" );
                    diagnosisMap.put(t.get("diagnosis_code").toUpperCase(), tCom);
                }
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
    }
    
    /**
     * Ƶ�� �ֵ� 
     * �������� �õ� ��� 
     * @param perFormName
     * @return
     */
    public TCommonRecord getPerformMapByName(String perFormName)
    {
        for(TCommonRecord cr : performMap.values())
        {
            if(perFormName.equalsIgnoreCase(cr.get("freq_desc")))
                return cr;
        }
        return new TCommonRecord();
    }
    
    /**
     * Ƶ���ֵ�� 
     * @param admin  �� serial_no
     * @return
     */
    public TCommonRecord getPerForm(String serial_no)
    {
        if (!performMap.containsKey(serial_no.toUpperCase()))
            return new TCommonRecord();
        return performMap.get(serial_no.toUpperCase());
    }
    
    /**
     * �� ���ּ���id ���
     * @param diagName
     * @return
     */
    public TCommonRecord getDiagnosisByName(String diagName)
    {
        for(TCommonRecord cr : diagnosisMap.values())
        {
            if(diagName.trim().indexOf(cr.get("diagnosis_Name")) != -1 )
                return cr;
            if(cr.get("diagnosis_Name").indexOf(diagName) != -1)
                return cr;
        }  
        return new TCommonRecord();
    }
    
    /**
     * his ����code ��ѯ������ 
     * @param diagID
     * @return
     */
    public TCommonRecord getDiagnosisById(String diagID)
    {
        TCommonRecord result = this.diagnosisMap.get(diagID);
        if(result == null)
            result = new TCommonRecord();
        return result;
    }
    
    /**
     * �����ּ���id ��ҩ;��
     * @param adminName
     * @return
     */
    public TCommonRecord getAdminByName(String adminName)
    {
        for (TCommonRecord cr: adminMap.values())
        {
            if (adminName.equalsIgnoreCase(cr.get("ADMINISTRATION_NAME")))
                return cr;
        }
        return new TCommonRecord();
    }
    
    /**
     * ��ҩ;��
     * @param admin  �� serial_no
     * @return
     */
    public TCommonRecord getAdmin(String admin)
    {
        if (!adminMap.containsKey(admin.toUpperCase()))
            return new TCommonRecord();
        return adminMap.get(admin.toUpperCase());
    }
    
    /**
     * ҩƷ�ֵ��
     * @param hisQuery
     */
    private void setDrugDict(JDBCQueryImpl hisQuery)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
        	//TODO ���� 
            /*String sql = "select * from comm.pham_basic_info";
            List<TCommonRecord> list = hisQuery.query(sql, new CommonMapper());
            for(TCommonRecord t :list)
            {
                drugCodeMap.put(t.get("pham_code").toUpperCase(), t);
                drugdictMap.put(t.get("pham_code").toUpperCase() + t.get("pham_unit").toUpperCase() + t.get("pham_spec").toUpperCase(), t);
            }*/
            List<TCommonRecord> list = ichh.fetchDrugDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t :list)
            {
                drugCodeMap.put(t.get("drug_code").toUpperCase(), t);
                drugdictMap.put(t.get("drug_code").toUpperCase() + t.get("units").toUpperCase() + t.get("drug_spec").toUpperCase(), t);
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
    }
    
    /**
     * ҩƷ�ֵ��
     * @param hisQuery
     */
    private void setDrugClassDict(JDBCQueryImpl hisQuery)
    { 
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord>  list = ichh.fetchDrugClassDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t :list)
            {
            	drugClassMap.put(t.get("class_code").toUpperCase(), t);    
            }    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
    }
    
    public String getDrugClassName(String drugCode)
    {
        if (drugClassMap.containsKey(drugCode.toUpperCase()))
        {
            return drugClassMap.get(drugCode.toUpperCase()).get("CLASS_NAME");
        }
        return "";
    }
    
    /**
     * ϸ��ҩ���ֵ� 
     * @param hisQuery
     */
    private void setGermSens(JDBCQueryImpl hisQuery)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "item_code,item_name";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord group = CaseHistoryHelperUtils.genGroupCR("item_code");
            lsGroup.add(group);
            group = CaseHistoryHelperUtils.genGroupCR("item_name");
            lsGroup.add(group);
            List<TCommonRecord> list = ichh.fetchGermdrugsensitDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t :list)
            {
                germSensMap.put(t.get("ITEM_CODE").toUpperCase(), t);    
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ichh      = null;
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
    }
    
    
    /**
     * ΢�����ֵ�� 
     * @param hisQuery
     */
    private void setGermCode(JDBCQueryImpl hisQuery)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> list = ichh.fetchGermCodeDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t :list)
            {
                germCodeMap.put(t.get("GERM_CODE").toUpperCase(), t);    
            }            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
//            cmr = null;
        }
    }
    
    
    /**
     * �걾��Ϣ 
     * @param hisQuery
     */
    private void setSpeci(JDBCQueryImpl hisQuery)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> list = ichh.fetchSpecimanDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            for(TCommonRecord t :list)
            {
                speciMap.put(t.get("SPECIMAN_CODE").toUpperCase(), t);    
            }
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
    }
    
    /**
     * ���� �Զ���Ĺ����� 
     * @param typeCode
     */
    public String getDrugTypeCode(String typeCode)
    {
        String re = Config.getParamValue(typeCode);
        if(re == null)
            new RuntimeException("û�ҵ����Ӧ�Ĺ���ţ�");
        return re;
    }
    
    /**
     * ��ʼ�� 
     * ҽ����Ϣ
     * @param query
     */
    @SuppressWarnings ("unchecked")
    private void setDoctorCode()
    {
        /*  2014-10-29 liujc �޸�  ��ҽ��������Ϣ���û��Լ�ά��*/
        JDBCQueryImpl query = DBQueryFactory.getQuery("PEAAS");
        List<TCommonRecord> list = query.query("select * from PEAAS.staff_dict ", new CommonMapper());
        try
        {
            for(TCommonRecord ds : list)
            {
                doctorMap.put(ds.get("NAME").toUpperCase(), ds);
            }
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            list  = null;
        }
        
        /*
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            //EMP_NO,NAME
            List<TCommonRecord> list = ichh.fetchStaffDict2CR(strFields, lsWheres, lsGroup, lsOrder, query);
            for(TCommonRecord ds : list)
            {
                doctorMap.put(ds.get("NAME").toUpperCase(), ds);
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }*/
    }
    
    /**
     * ��ʼ��  
     * ������Ϣ 
     * @param query
     */
    private void setDept(JDBCQueryImpl query)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord order = CaseHistoryHelperUtils.genOrderCR("dept_Name", "desc");
            lsOrder.add(order);
            List<TCommonRecord> depts = ichh.fetchDeptDict2CR(strFields, lsWheres, lsGroup, lsOrder, query);
            for(TCommonRecord d : depts )
            {
                deptMap.put(d.get("DEPT_CODE").toUpperCase(), d);
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
    }
    
    /**
     * ��ʼ��  
     * ������Ϣ 
     * @param query
     */
    private void setMergeDept(JDBCQueryImpl query1)
    {
        try
        {
            String sql = "SELECT * FROM peaas.dept_dict WHERE DEPT_CODE=PARENT_DEPT_CODE order by order_no";
            JDBCQueryImpl query4PEAAS = DBQueryFactory.getQuery("PEAAS");
            List<TCommonRecord> depts = query4PEAAS.query(sql,
                    new CommonMapper());
            for (TCommonRecord d : depts)
            {
                mergeDeptMap.put(d.get("dept_code").toUpperCase(), d);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * ��ʼ��
     * �ѱ���Ϣ 
     * @param query
     */
    private void setChargeMap(JDBCQueryImpl query)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> charge = ichh.fetchChargeTypeDict2CR(strFields, lsWheres, lsGroup, lsOrder, query);
            for(TCommonRecord d : charge )
            {
                chargeMap.put(d.get("charge_type_code").toUpperCase(), d);
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
    }
    
    /**
     * ��ʼ��
     * �����Ϣ 
     * @param query
     */
    private void setIdentityMap(JDBCQueryImpl query)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> identity = ichh.fetchIdentityDict2CR(strFields, lsWheres, lsGroup, lsOrder, query);
            for(TCommonRecord d : identity )
            {
                identityMap.put(d.get("identity_code").toUpperCase(), d);
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
    }
    
    /**
     * ��ʼ��
     * ������Ϣ 
     * @param query
     */
    private void setFormMap(JDBCQueryImpl query)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            List<TCommonRecord> drug_form = ichh.fetchDrugFormDict2CR(strFields, lsWheres, lsGroup, lsOrder, query);
            for(TCommonRecord d : drug_form )
            {
                formMap.put(d.get("form_code").toUpperCase(), d);
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsOrder   = null;
            lsGroup   = null;
            ichh      = null;
        }
    }
    
    /**
     * ����ҽ���������ҽ������
     * @param DoctorName
     * @return
     */
    public String getDoctorCode(JDBCQueryImpl hisQuery , String DoctorName)
    {
    	if(doctorMap.containsKey(DoctorName))
    	{
    		return doctorMap.get(DoctorName).get("EMP_NO");
    	}
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord where = CaseHistoryHelperUtils.genWhereGbkCR("name", DoctorName, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchStaffDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord doctorInfo = list != null && list.size() > 0 ?list.get(0) : null;
            if(doctorInfo != null)
                doctorMap.put(DoctorName.toUpperCase(), doctorInfo);
            return doctorInfo != null?doctorInfo.get("EMP_NO"):"";
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            lsWheres  = null;
            strFields = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return "";
    }
    
    /**
     *  ��������ҽ����¼
     * @param hisQuery
     * @param DoctorName
     * @return
     */
    public TCommonRecord getDoctorInfo(JDBCQueryImpl hisQuery , String DoctorName)
    {
    	if(doctorMap.containsKey(DoctorName))
    	{
    		return doctorMap.get(DoctorName);
    	}
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord where = CaseHistoryHelperUtils.genWhereGbkCR("name", DoctorName, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchStaffDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord doctorInfo = list != null && list.size() > 0 ?list.get(0) : null;
            if(doctorInfo != null)
            {
                doctorMap.put(DoctorName.toUpperCase(), doctorInfo);
            }
            else
            {
                System.out.println("��ȡhisְ�Ʊ� ���� : " + DoctorName + " û���䱸�ϵ�����");
                doctorInfo = new TCommonRecord();
            }
                
            return doctorInfo;
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            lsWheres  = null;
            strFields = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return new TCommonRecord();
    }
    
    /**
     * ���ݲ��Ŵ����ò�������
     * @param DeptName
     * @return
     */
    @Deprecated
    public String getDeptName(JDBCQueryImpl hisQuery , String DeptCode)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {   if (deptMap.containsKey(DeptCode.toUpperCase()))
                return deptMap.get(DeptCode.toUpperCase()).get("DEPT_NAME");
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("dept_code", DeptCode, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchDeptDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord deptInfo = list != null && list.size() > 0 ?list.get(0):new TCommonRecord();
            if(deptInfo != null)
                deptMap.put(DeptCode.toUpperCase(), deptInfo);
            return deptInfo!= null?deptInfo.get("DEPT_NAME"):"";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            ichh      = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
        return "";
    }
    /**
     * ���ݲ��Ŵ����ò�������
     * @param DeptName
     * @return
     */
    public String getDeptName(String DeptCode)
    {
    	if(hisQuery == null)hisQuery = DBQueryFactory.getQuery("HIS"); 
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {   if (deptMap.containsKey(DeptCode.toUpperCase()))
                return deptMap.get(DeptCode.toUpperCase()).get("DEPT_NAME");
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("dept_code", DeptCode, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchDeptDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord deptInfo = list != null && list.size() > 0 ?list.get(0):new TCommonRecord();
            if(deptInfo != null)
                deptMap.put(DeptCode.toUpperCase(), deptInfo);
            return deptInfo!= null?deptInfo.get("DEPT_NAME",true):"";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            ichh      = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
        return "";
    }
    
    /**
     * ��ô��������
     * @param deptCode
     * @return
     */
    public TCommonRecord getMergeDept(String deptCode)
    {
        if (mergeDeptMap.containsKey(deptCode.toUpperCase()))
        {
            return mergeDeptMap.get(deptCode.toUpperCase());
        }
        try
        {
            String sql = "select * from peaas.dept_dict where dept_code='" + deptCode.toUpperCase() + "'";
            JDBCQueryImpl query4PEAAS = DBQueryFactory.getQuery("PEAAS");
            List<TCommonRecord> depts = query4PEAAS.query(sql, new CommonMapper());
            if (depts != null && depts.size() > 0)
            {
                return new TCommonRecord();
            }
            else
            {
                mergeDeptMap.put(depts.get(0).get("dept_code").toUpperCase(),
                        depts.get(0));
                return depts.get(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new TCommonRecord();
    }

    /**
     * �������в�����Ϣ 
     * @param hisQuery
     * @param deptCode
     * @return
     */
    public TCommonRecord getDeptInfo(JDBCQueryImpl hisQuery ,String deptCode)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {   if (deptMap.containsKey(deptCode.toUpperCase()))
                return deptMap.get(deptCode.toUpperCase());
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("dept_code", deptCode, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchDeptDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord deptInfo = list != null && list.size() > 0 ?list.get(0):new TCommonRecord();
            if(deptInfo != null)
                deptMap.put(deptCode.toUpperCase(), deptInfo);
            return deptInfo;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            ichh      = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
        }
        return new TCommonRecord();
    }
    
    /**
     * �������в��� 
     * @return
     */
    @SuppressWarnings ("static-access")
    public List<TCommonRecord> getDeptAll()
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for(TCommonRecord t : this.deptMap.values())
        {
            list.add(t);
        }
        return list;
    }

    /**
     * �������в���
     * 
     * @return
     */
    @SuppressWarnings ("static-access")
    public List<TCommonRecord> getMergeDeptAll()
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for (TCommonRecord t : this.mergeDeptMap.values())
        {
            list.add(t);
        }
        return list;
    }
    
    /**
     * �������зѱ� 
     * @return
     */
    @SuppressWarnings ("static-access")
    public List<TCommonRecord> getChargeAll()
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for(TCommonRecord t : this.chargeMap.values())
        {
            list.add(t);
        }
        return list;
    }
    
    /**
     * �����������
     * @return
     */
    @SuppressWarnings ("static-access")
    public List<TCommonRecord> getIdentityAll()
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for(TCommonRecord t : this.identityMap.values())
        {
            list.add(t);
        }
        return list;
    }
    /**
     * �������м���
     * @return
     */
    @SuppressWarnings ("static-access")
    public List<TCommonRecord> getFormAll()
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for(TCommonRecord t : this.formMap.values())
        {
            list.add(t);
        }
        return list;
    }
    /**
     * ��������ȥ���� 
     * Ŀǰֻ��Ե������� 
     * @return
     */
    @SuppressWarnings ("static-access")
    public List<TCommonRecord> getDeptWhere(String key,String value)
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for(TCommonRecord t : this.deptMap.values())
        {
            if(value.equals(t.get(key)))
            {
                list.add(t);    
            }
        }
        return list;
    }
//    
//    private TCommonRecord wheresEquals(TCommonRecord t ,String[] keys,String[] values )
//    {
//        for(int i = 0 ;i<keys.length;i++)
//        {
//            if(values[i].equals(t.get(keys[i])))
//            {
//                return t;
//            }
//        }
//        return null;
//    }
    
    /**
     * ���ر걾��Ϣ 
     * @param hisQuery
     * @param code
     * @return
     */
    public TCommonRecord getSpeciInfo(JDBCQueryImpl hisQuery,String code)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            if(speciMap.containsKey(code.toUpperCase()))
                return speciMap.get(code.toUpperCase());
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("SPECIMAN_CODE", code, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchSpecimanDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord speci = list != null && list.size() > 0 ?list.get(0):new TCommonRecord();
            if(speci == null)
              speci = new TCommonRecord();
            speciMap.put(speci.get("SPECIMAN_CODE").toUpperCase(),speci);
            return speci;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return new TCommonRecord();
    }
    
    /**
     * �������б걾��Ϣ
     * @return
     */
    @SuppressWarnings ("static-access")
    public List<TCommonRecord> getSpeciAll()
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for(TCommonRecord t : this.speciMap.values())
        {
            list.add(t);
        }
        return list;
    }
    
    /**
     * ϸ��ҩ���ֵ�  
     * @param hisQuery
     * @param code
     * @return
     */
    public TCommonRecord getGermSensInfo(JDBCQueryImpl hisQuery,String code)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            if(germSensMap.containsKey(code.toUpperCase()))
                return germSensMap.get(code.toUpperCase());
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("Item_Code", code, "Char", "", "", "");
            lsWheres.add(where);
            TCommonRecord group = CaseHistoryHelperUtils.genGroupCR("item_code");
            lsGroup.add(group);
            group = CaseHistoryHelperUtils.genGroupCR("item_name");
            lsGroup.add(group);
            List<TCommonRecord> list = ichh.fetchGermdrugsensitDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord germSens = list != null && list.size() > 0 ? list.get(0):new TCommonRecord();
            if(germSens == null)
                germSens = new TCommonRecord();
            germSensMap.put(code.toUpperCase(),germSens);
            return germSens;
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            lsWheres  = null;
            strFields = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return new TCommonRecord();
    }
    
    /**
     * ΢�����ֵ�� 
     * @param hisQuery
     * @param code
     * @return
     */
    public TCommonRecord getGermCodeInfo(JDBCQueryImpl hisQuery,String code)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("Germ_code", code, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchGermCodeDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord germCode = list != null && list.size() > 0 ?list.get(0):new TCommonRecord();
            if(germCode == null)
                germCode = new TCommonRecord();
            germCodeMap.put(code.toUpperCase(),germCode);
            return germCode;
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return new TCommonRecord();
    }
    /**
     * ��������ϸ������
     * @return
     */
    /*public List<TCommonRecord>  getGermCodeAll()
    {
        List<TCommonRecord>  list = new ArrayList<TCommonRecord>();
        for(TCommonRecord t : germCodeMap.values())
        {
            list.add(t);
        }
        return list;
    }
    
    /**
     * �����ȡ�� ҩƷ��Ϣ
     * @param code
     * @return
     */
    public TCommonRecord getDrugDictInfo(String code)
    {
        if(drugCodeMap.containsKey(code.toUpperCase()))
            return drugCodeMap.get(code.toUpperCase());
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("drug_code", code, "Char", "", "", "");
            lsWheres.add(where);
            if(hisQuery ==  null) hisQuery = DBQueryFactory.getQuery("HIS");
            List<TCommonRecord> list = ichh.fetchDrugDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord drugdict   = list != null && list.size() > 0 ?list.get(0):new TCommonRecord();
            if(drugdict == null)
                drugdict = new TCommonRecord();
            drugCodeMap.put(code.toUpperCase(),drugdict);
            return drugdict;
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            hisQuery  = null;
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return new TCommonRecord();
    }
    
    /**
     * �����ȡ�� ҩƷ��Ϣ
     * @param code
     * @return
     */
    public TCommonRecord getDrugDictInfo(JDBCQueryImpl hisQuery ,String code)
    {
        if(drugCodeMap.containsKey(code.toUpperCase()))
            return drugCodeMap.get(code.toUpperCase());
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("drug_code", code, "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> list = ichh.fetchDrugDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            TCommonRecord drugdict = list != null && list.size() > 0 ?list.get(0):new TCommonRecord();
            if(drugdict == null)
                drugdict = new TCommonRecord();
            drugCodeMap.put(code.toUpperCase(),drugdict);
            return drugdict;
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return new TCommonRecord();
    }
    
    /**
     * ����� ��װ  ��� 
     * @param key =  drug_code + units + drug_spec
     * @return
     */
    public TCommonRecord getDrugDictKeyInfo(String Key)
    {
        if(drugdictMap.containsKey(Key.toUpperCase()))
            return drugdictMap.get(Key.toUpperCase());
        TCommonRecord drugdict = new TCommonRecord();
        drugdictMap.put(Key.toUpperCase(),drugdict);
        return drugdict;
    }
    
    public TCommonRecord getDrugInfoByCodeUnitSpec(String DrugCode, String Units, String DrugSpec)
    {
        return getDrugDictKeyInfo(DrugCode + Units + DrugSpec);
    }

    /**
     * �����ģ����ѯҩƷ����
     * @param code
     * @return
     */
    public String getDrugNameLikely(JDBCQueryImpl hisQuery, String code)
    {
        ICaseHistoryHelper ichh = CaseHistoryFactory.getCaseHistoryHelper();
        String strFields = "*";
        List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsGroup = new ArrayList<TCommonRecord>();
        List<TCommonRecord> lsOrder = new ArrayList<TCommonRecord>();
        try
        {
            TCommonRecord where = CaseHistoryHelperUtils.genWhereCR("drug_code", "%" + code + "%", "Char", "", "", "");
            lsWheres.add(where);
            List<TCommonRecord> drugdicts = ichh.fetchDrugDict2CR(strFields, lsWheres, lsGroup, lsOrder, hisQuery);
            if (drugdicts.size() > 0)
            {
                drugCodeMap.put(code.toUpperCase(),drugdicts.get(0));
                return drugdicts.get(0).get("drug_NAME");
            }
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            strFields = null;
            lsWheres  = null;
            lsGroup   = null;
            lsOrder   = null;
            ichh      = null;
        }
        return "";
    }
    
    /**
     * �������ҩƷ��Ϣ 
     * @return
     */
    public List<TCommonRecord> getDrugDictAll()
    {
        List<TCommonRecord> list = new LinkedList<TCommonRecord>();
        for(TCommonRecord t : drugCodeMap.values())
        {
            list.add(t);
        }
        return list;
    }
    
    /* ���Һϴ�����е�����С���� */
    @SuppressWarnings ("unchecked")
    public String getSearchMergeDept(String parm)
    {
        int i = 0;
        String searchmergeDeptMap = "";
        try
        {
            String sql = "SELECT dept_code FROM peaas.dept_dict WHERE PARENT_DEPT_CODE='" + parm + "'  order by order_no";
            JDBCQueryImpl query = DBQueryFactory.getQuery("PEAAS");
            List<TCommonRecord> depts = query.query(sql, new CommonMapper());
            if (depts != null && depts.size() > 0)
            {
                for (TCommonRecord d : depts)
                {
                    i++;
                    if (i == 1)
                    {
                        searchmergeDeptMap = d.get("dept_code").toUpperCase();
                    }
                    else
                    {
                        searchmergeDeptMap += "," + d.get("dept_code").toUpperCase();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return searchmergeDeptMap;
    }
}
