package com.hitzd.his.Beans;

import java.io.Serializable;

/**
 * @description ҽ��ҩ�������Ϣ�ࣺPatOrderDrugSensitive ��Ӧ���ݿ��ҽ��ҩ�������Ϣ(PAT_ORDER_DRUG_SENSITIVE)
 * @author
 */
public class TPatOrderDrugSensitive implements Serializable{
	
    private static final long serialVersionUID = 1L;
    /* ҩ�������¼ID*/
	private String  patOrderDrugSensitiveID;

	/* ҩ����Ϣ���� */
	private String  drugAllergenID;
	/* ����Դ*/
	//private String sensitiveSource;

	
    public String getPatOrderDrugSensitiveID()
    {
        return patOrderDrugSensitiveID;
    }

    public void setPatOrderDrugSensitiveID(String patOrderDrugSensitiveID)
    {
        this.patOrderDrugSensitiveID = patOrderDrugSensitiveID;
    }

    public String getDrugAllergenID()
    {
        return drugAllergenID;
    }

    public void setDrugAllergenID(String drugAllergenID)
    {
        this.drugAllergenID = drugAllergenID;
    }
//	public String getSensitiveSource() {
//		return sensitiveSource;
//	}
//	public void setSensitiveSource(String sensitiveSource) {
//		this.sensitiveSource = sensitiveSource;
//	}
   
}