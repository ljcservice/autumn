package com.hitzd.his.Beans;

import java.io.Serializable;

public class TPatOrderInfoExt implements Serializable {

    private static final long serialVersionUID = 1L;
    /* �Ƿ����� */
	private String isLact;
	
	/* �Ƿ��и�  */
	private String isPregnant;
	
	/* ҽ�����  */
	private String insureanceType;
	
	/* ҽ�Ʊ��պ�  */
	private String insuranceNo;
	
	/* �ι��ܲ���ȫ��־  */
	private String isLiverWhole;
	
	/* �����ܲ���ȫ��־  */
	private String isKidneyWhole;
	
	/* ��ְ��־  */
	//private String isWorking;
	
	/* ���  */
	private String height;
	
	/* ����  */
	private String weight;

	/**
	 * �����Ƿ�Ϊ������ȫ
	 * @return
	 */
	public boolean TheIsKidneyWhole()
	{
	    if(this.isKidneyWhole != null)
        {
            if("��".equals(this.isKidneyWhole)||"Y".equals(this.isKidneyWhole.toUpperCase())
                    ||"YES".equals(this.isKidneyWhole.toUpperCase())||"TRUE".equals(this.isKidneyWhole.toUpperCase())
                    ||"1".equals(this.isKidneyWhole))
            {
                return true;
            }
        }
	    return false;
	}
	
	/**
	 * �����Ƿ�Ϊ�ι���ȫ
	 * @return
	 */
	public boolean TheIsLiverWhole()
	{
	    if(this.isLiverWhole != null)
        {
            if("��".equals(this.isLiverWhole)||"Y".equals(this.isLiverWhole.toUpperCase())
                    ||"YES".equals(this.isLiverWhole.toUpperCase())||"TRUE".equals(this.isLiverWhole.toUpperCase())
                    ||"1".equals(this.isLiverWhole))
            {
                return true;
            }
        }
	    return false;
	}
	
	/**
	 * �����Ƿ�Ϊ������
	 */
	public boolean TheIsLact()
	{
	    if(this.isLact != null)
	    {
    	    if("��".equals(this.isLact)||"Y".equals(this.isLact.toUpperCase())
    	            ||"YES".equals(this.isLact.toUpperCase())||"TRUE".equals(this.isLact.toUpperCase())
    	            ||"1".equals(this.isLact))
    	    {
    	        return true;
    	    }
	    }
	    return false;
	}
	
	
	/**
	 * �����Ƿ�Ϊ�и�
	 * @return
	 */
	public boolean TheIsPregnant()
	{
	    if(this.isPregnant != null)
        {
            if("��".equals(this.isPregnant)||"Y".equals(this.isPregnant.toUpperCase())
                    ||"YES".equals(this.isPregnant.toUpperCase())||"TRUE".equals(this.isPregnant.toUpperCase())
                    ||"1".equals(this.isPregnant))
            {
                return true;
            }
        }
	    return false;
	}
	
	public String getIsLact() {
		return isLact;
	}

	public void setIsLact(String isLact) {
		this.isLact = isLact;
	}

	public String getIsPregnant() {
		return isPregnant;
	}

	public void setIsPregnant(String isPregnant) {
		this.isPregnant = isPregnant;
	}

	public String getInsureanceType() {
		return insureanceType;
	}

	public void setInsureanceType(String insureanceType) {
		this.insureanceType = insureanceType;
	}

	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	public String getIsLiverWhole() {
		return isLiverWhole;
	}

	public void setIsLiverWhole(String isLiverWhole) {
		this.isLiverWhole = isLiverWhole;
	}

	public String getIsKidneyWhole() {
		return isKidneyWhole;
	}

	public void setIsKidneyWhole(String isKidneyWhole) {
		this.isKidneyWhole = isKidneyWhole;
	}

//	public String getIsWorking() {
//		return isWorking;
//	}
//
//	public void setIsWorking(String isWorking) {
//		this.isWorking = isWorking;
//	}

	public String getHeight() 
	{
		if(this.height == null || "".equals(this.height.trim()))
			this.height = "0";
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
	}

	public String getWeight() 
	{
		if(weight == null || "".equals(weight.trim()))
			this.weight = "0";
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}


	
  
}
