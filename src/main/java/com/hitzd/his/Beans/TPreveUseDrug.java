package com.hitzd.his.Beans;

import com.hitzd.his.Beans.TBaseBean;

/**
 * Ԥ����ҩ��Ϣ��¼
 * 
 * @author Administrator
 * 
 */
public class TPreveUseDrug extends TBaseBean
{
    private static final long serialVersionUID = -1971327703516467313L;

    /* Ԥ����ҩid */
    private String            YF_ID;
    /* ����id��(סԺ��) */
    private String            PATIENT_ID;
    /* ��Ժ���� */
    private String            VISIT_ID;
    /* ���Ҵ��� */
    private String            DEPT_CODE;
    /* �������� */
    private String            DEPT_NAME;
    /* ҽ������ */
    private String            DOCTOR_NAME;
    /* ���� */
    private String            NAME;
    /* �Ա� */
    private String            SEX;
    /* ���� */
    private String            AGE;
    /* ���� */
    private String            WEIGHT;
    /* ����ҩƷ�� */
    private String            DRUG_CODE;
    /* ����ҩƷ���� */
    private String            DRUG_NAME;
    /* ��С���� */
    private String            DOSAGE;
    /* ��С��λ */
    private String            DOSAGE_UNITS;
    /* ��ҩ;�� */
    private String            ADMINISTRATION;
    /* ����ʱ�� */
    private String            OPERTOR_DATE;
    /* �������� */
    private String            OPERTOR_NAME;
    /* �������� */
    private String            OPERTOR_TYPE;
    /* ����ʱ�� */
    private String            OPERTOR_USE_TIME;
    /* ʹ��ʱ��� */
    private String            DRUG_USE_TIME;
    /* ����ʷ */
    private String            GMS;
    /* �¹���ʷ */
    private String            BTGMS;
    /* Σ������ */
    private String            WXYS;
    /* ���ܵ��β��� */
    private String            KNDZBJ;
    /* ���ּ������ϼ��� */
    private String            KLJLANJL;
    /* Ԥ��ʹ�ÿ���ҩ������� */
    private String            YF_USE_DRUG_YJ;
    /* ����Ҫ�� */
    private String            TSYQ;
    /* ��¼ʱ�� */
    private String REC_DATE;
    
    public String getREC_DATE()
    {
        return REC_DATE;
    }

    public void setREC_DATE(String rEC_DATE)
    {
        REC_DATE = rEC_DATE;
    }

    public String getYF_ID()
    {
        return YF_ID;
    }

    public void setYF_ID(String yF_ID)
    {
        YF_ID = yF_ID;
    }

    public String getPATIENT_ID()
    {
        return PATIENT_ID;
    }

    public void setPATIENT_ID(String pATIENT_ID)
    {
        PATIENT_ID = pATIENT_ID;
    }

    public String getVISIT_ID()
    {
        return VISIT_ID;
    }

    public void setVISIT_ID(String vISIT_ID)
    {
        VISIT_ID = vISIT_ID;
    }

    public String getDEPT_CODE()
    {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String dEPT_CODE)
    {
        DEPT_CODE = dEPT_CODE;
    }

    public String getDEPT_NAME()
    {
        return DEPT_NAME;
    }

    public void setDEPT_NAME(String dEPT_NAME)
    {
        DEPT_NAME = dEPT_NAME;
    }

    public String getDOCTOR_NAME()
    {
        return DOCTOR_NAME;
    }

    public void setDOCTOR_NAME(String dOCTOR_NAME)
    {
        DOCTOR_NAME = dOCTOR_NAME;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String nAME)
    {
        NAME = nAME;
    }

    public String getSEX()
    {
        return SEX;
    }

    public void setSEX(String sEX)
    {
        SEX = sEX;
    }

    public String getAGE()
    {
        return AGE;
    }

    public void setAGE(String aGE)
    {
        AGE = aGE;
    }

    public String getWEIGHT()
    {
        return WEIGHT;
    }

    public void setWEIGHT(String wEIGHT)
    {
        WEIGHT = wEIGHT;
    }

    public String getDRUG_CODE()
    {
        return DRUG_CODE;
    }

    public void setDRUG_CODE(String dRUG_CODE)
    {
        DRUG_CODE = dRUG_CODE;
    }

    public String getDRUG_NAME()
    {
        return DRUG_NAME;
    }

    public void setDRUG_NAME(String dRUG_NAME)
    {
        DRUG_NAME = dRUG_NAME;
    }

    public String getDOSAGE()
    {
        return DOSAGE;
    }

    public void setDOSAGE(String dOSAGE)
    {
        DOSAGE = dOSAGE;
    }

    public String getDOSAGE_UNITS()
    {
        return DOSAGE_UNITS;
    }

    public void setDOSAGE_UNITS(String dOSAGE_UNITS)
    {
        DOSAGE_UNITS = dOSAGE_UNITS;
    }

    public String getADMINISTRATION()
    {
        return ADMINISTRATION;
    }

    public void setADMINISTRATION(String aDMINISTRATION)
    {
        ADMINISTRATION = aDMINISTRATION;
    }

    public String getOPERTOR_DATE()
    {
        return OPERTOR_DATE;
    }

    public void setOPERTOR_DATE(String oPERTOR_DATE)
    {
        OPERTOR_DATE = oPERTOR_DATE;
    }

    public String getOPERTOR_NAME()
    {
        return OPERTOR_NAME;
    }

    public void setOPERTOR_NAME(String oPERTOR_NAME)
    {
        OPERTOR_NAME = oPERTOR_NAME;
    }

    public String getOPERTOR_TYPE()
    {
        return OPERTOR_TYPE;
    }

    public void setOPERTOR_TYPE(String oPERTOR_TYPE)
    {
        OPERTOR_TYPE = oPERTOR_TYPE;
    }

    public String getOPERTOR_USE_TIME()
    {
        return OPERTOR_USE_TIME;
    }

    public void setOPERTOR_USE_TIME(String oPERTOR_USE_TIME)
    {
        OPERTOR_USE_TIME = oPERTOR_USE_TIME;
    }

    public String getDRUG_USE_TIME()
    {
        return DRUG_USE_TIME;
    }

    public void setDRUG_USE_TIME(String dRUG_USE_TIME)
    {
        DRUG_USE_TIME = dRUG_USE_TIME;
    }

    public String getGMS()
    {
        return GMS;
    }

    public void setGMS(String gMS)
    {
        GMS = gMS;
    }

    public String getBTGMS()
    {
        return BTGMS;
    }

    public void setBTGMS(String bTGMS)
    {
        BTGMS = bTGMS;
    }

    public String getWXYS()
    {
        return WXYS;
    }

    public void setWXYS(String wXYS)
    {
        WXYS = wXYS;
    }

    public String getKNDZBJ()
    {
        return KNDZBJ;
    }

    public void setKNDZBJ(String kNDZBJ)
    {
        KNDZBJ = kNDZBJ;
    }

    public String getKLJLANJL()
    {
        return KLJLANJL;
    }

    public void setKLJLANJL(String kLJLANJL)
    {
        KLJLANJL = kLJLANJL;
    }

    public String getYF_USE_DRUG_YJ()
    {
        return YF_USE_DRUG_YJ;
    }

    public void setYF_USE_DRUG_YJ(String yF_USE_DRUG_YJ)
    {
        YF_USE_DRUG_YJ = yF_USE_DRUG_YJ;
    }

    public String getTSYQ()
    {
        return TSYQ;
    }

    public void setTSYQ(String tSYQ)
    {
        TSYQ = tSYQ;
    }
}
