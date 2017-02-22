package com.hitzd.his.Beans;

import java.io.Serializable;

//import org.codehaus.xfire.aegis.type.java5.IgnoreProperty;

import com.hitzd.his.Utils.Config;
import com.hitzd.his.Utils.DictCache;

/**
 * @description ������ҩ��¼�ࣺPatOrderDrug ��Ӧ���ݿ��������ҩ��¼(Pat_Order_Drug)
 * @author
 */
public class TPatOrderDrug implements Serializable
{

    private static final long serialVersionUID = 1L;
    // ҩƷID
    private String            drugID;
    // ҩƷ��׼ID
    private String            drugStandID;
    /* ҩƷ���� */
    private String            DrugName;
    private String            DoctorName;
    // ҽ�����
    private String            recMainNo;
    // ҽ�������
    private String            recSubNo;
    // һ��ʹ�ü���
    private String            dosage;
    // ������λ
    private String            doseUnits;
    // ��ҩ;��ID
    private String            administrationID;
    // ��ҩ;����׼ID
    private String            administrationStandID;
    // ִ��Ƶ��ID
    private String            performFreqDictID;
    // ִ��Ƶ�ʱ�׼ID
    private String            performFreqDictStandID;
    // ִ��ִ��Ƶ���ı�(����ִ��Ƶ������)
    private String            performFreqDictText;
    // ��ʼ����ʱ��
    private String            startDateTime;
    // ��������ʱ��
    private String            stopDateTime;
    // ����ҽ������
    private String            doctorDept;
    // ����ҽ��ҽ��
    private String            doctor;
    // �Ƿ���
    private String            isGroup;
    // ҩƷ����ID
    private String            firmID;
    // �Ƿ�Ϊ��ҽ��
    private String            isNew;
    // ȷ��ʱ��
    private String            dvaliddate;
    /* ��ҩ;������ */
    private String            adminName;
    /* "Ԥ����"��1��ʾ���������á���2��ʾ */
    private String            useType;
    /* ʹ��ԭ�� */
    private String            useCause;
    
    
    /* ������Ҫ����Ϣbean Begin */
    /* ������� */
    private String PRESC_NO;
    /* ��Ŀ��� */
    private String ITEM_NO ;
    /* ���� */
    private String AMOUNT; 
    /* �Ƽ۽�� */
    private String COSTS;
    /* ��λ */
    private String UNITS;
    /* ҩƷ��� */
    private String DRUG_SPEC;
    /* ��ˮ�� */
    private String SERIAL_NO;
    /* ��Ŀ���  */
    private String ITEM_CLASS;
    /* ������Ҫ����Ϣbean End*/
    
    public String getUseCause()
    {
        return useCause;
    }

    public void setUseCause(String useCause)
    {
        this.useCause = useCause;
    }

    public String getUseType()
    {
        return useType;
    }

    public void setUseType(String useType)
    {
        this.useType = useType;
    }

    public String getAdminName()
    {
        return adminName;
    }

    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }

    public String getDrugName()
    {
        return DrugName;
    }

    public void setDrugName(String drugName)
    {
        DrugName = drugName;
    }

    public String getDoctorName()
    {
        return DoctorName;
    }

    public void setDoctorName(String doctorName)
    {
        DoctorName = doctorName;
    }

    public String getDvaliddate()
    {
        return dvaliddate;
    }

    public void setDvaliddate(String dvaliddate)
    {
        this.dvaliddate = dvaliddate;
    }

    public String getAdministrationID()
    {
        return administrationID;
    }

    public void setAdministrationID(String administrationID)
    {
        String admin = administrationID;
        /* ����һ�� ������Ƿ��� code Ϊ 1 ʱ administrationID �������� ��Ҫת�� 
         * 1Ϊ ��ҩ;�������� 2Ϊ his�е���ҩ���� 0Ϊ �����еı�׼�� 
         * */
        DictCache dc = DictCache.getNewInstance();
        if (Config.getParamValue("admin_conv_flag").equals("1"))
        {
            admin = dc.getAdminByName(administrationID)
                    .get("administration_code");
            this.setAdminName(administrationID);
        }
        this.administrationID = admin;
    }

    public String getDoctor()
    {
        return doctor;
    }

    public void setDoctor(String doctor)
    {
        this.doctor = doctor;
    }

    public String getDoctorDept()
    {
        return doctorDept;
    }

    public void setDoctorDept(String doctorDept)
    {
        this.doctorDept = doctorDept;
    }

    public String getDoseUnits()
    {
        return doseUnits;
    }

    public void setDoseUnits(String doseUnits)
    {
        this.doseUnits = doseUnits;
    }

    public String getDrugID()
    {
        return drugID;
    }

    public void setDrugID(String drugID)
    {
        this.drugID = drugID;
    }

    public String getPerformFreqDictID()
    {
        return performFreqDictID;
    }

    public void setPerformFreqDictID(String performFreqDictID)
    {
    	 String performFreq = performFreqDictID;
         /* ����һ�� ������Ƿ��� code Ϊ 1 ʱ performFreqDictID �������� ��Ҫת�� 
          * 1Ϊ Ƶ�ε�����  
          * */
         DictCache dc = DictCache.getNewInstance();
         if (Config.getParamValue("perform_conv_flag").equals("1"))
         {
        	 performFreq = dc.getPerformMapByName(performFreqDictID)
                     .get("serial_no");
             this.setPerformFreqDictText(performFreqDictID);
         }
        this.performFreqDictID = performFreq;
    }

    public String getRecMainNo()
    {
        return recMainNo;
    }

    public void setRecMainNo(String recMainNo)
    {
        this.recMainNo = recMainNo;
    }

    public String getRecSubNo()
    {
        return recSubNo;
    }

    public void setRecSubNo(String recSubNo)
    {
        this.recSubNo = recSubNo;
    }

    public String getDosage()
    {
        return dosage;
    }

    public void setDosage(String dosage)
    {
        this.dosage = dosage;
    }

    public String getStartDateTime()
    {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime)
    {
        this.startDateTime = startDateTime;
    }

    public String getStopDateTime()
    {
        return stopDateTime;
    }

    public void setStopDateTime(String stopDateTime)
    {
        this.stopDateTime = stopDateTime;
    }

    public String getIsGroup()
    {
        return isGroup;
    }

    public void setIsGroup(String isGroup)
    {
        this.isGroup = isGroup;
    }

    public String getIsNew()
    {
        return isNew;
    }

    public void setIsNew(String isNew)
    {
        this.isNew = isNew;
    }

    public String getPerformFreqDictText()
    {
        return performFreqDictText;
    }

    public void setPerformFreqDictText(String performFreqDictText)
    {
        this.performFreqDictText = performFreqDictText;
    }

    // @IgnoreProperty
    public String getPerformFreqDictStandID()
    {
        return performFreqDictStandID;
    }

    public void setPerformFreqDictStandID(String performFreqDictStandID)
    {
        this.performFreqDictStandID = performFreqDictStandID;
    }

    // @IgnoreProperty
    public String getAdministrationStandID()
    {
        return administrationStandID;
    }

    public void setAdministrationStandID(String administrationStandID)
    {
        this.administrationStandID = administrationStandID;
    }

    // @IgnoreProperty
    public String getDrugStandID()
    {
        return drugStandID;
    }

    public void setDrugStandID(String drugStandID)
    {
        this.drugStandID = drugStandID;
    }

    public String getFirmID()
    {
        return firmID;
    }

    public void setFirmID(String firmID)
    {
        this.firmID = firmID;
    }

}
