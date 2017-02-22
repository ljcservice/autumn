package com.hitzd.his.Beans;

import java.io.Serializable;

/**
 * @description ����סԺ��Ϣ�ࣺPatOrderVisitInfo ��Ӧ���ݿ������סԺ��Ϣ(PAT_ORDER_VISITINFO)
 * @author
 */
public class TPatOrderVisitInfo implements Serializable
{

    private static final long serialVersionUID = 1L;
    /* ���˱���סԺ��ʶ HISϵͳID*/
    private String visitID;
    /* ����ID*/
    private String patientID;
    /* ��Ժ����*/
    private String inDept;
    /* ��Ժ��ʽ ʹ�ô��룬������ת���*/
    private String inMode;
    /* ��Ժ����*/
    private String inDate;
    /* סԺĿ��*/
    // private String inCause;
    /* ��Ժ����*/
    private String outDept;
    /* ��Ժ���ڼ�ʱ��*/
    // private String outDate;
    /* ��Ժ��ʽ*/
    // private String outMode;
    /* ��������*/
    // private String consultingDate;
    /* ��Ժ���� ʹ�ô���, Σ������һ��*/
    private String patAdmCondition;
    /* ����ҽʦ */
    private String mainDoctor;
    /* ����ҽʦ */
    private String otherDoctor;
    /* ����ҽʦ*/
    private String consultingDoctor;
    public String getVisitID()
    {
        return visitID;
    }

    public void setVisitID(String visitID)
    {
        this.visitID = visitID;
    }

    public String getPatientID()
    {
        return patientID;
    }

    public void setPatientID(String patientID)
    {
        this.patientID = patientID;
    }

    public String getInDept()
    {
        return inDept;
    }

    public void setInDept(String inDept)
    {
        this.inDept = inDept;
    }

    public String getInMode()
    {
        return inMode;
    }

    public void setInMode(String inMode)
    {
        this.inMode = inMode;
    }

    public String getInDate()
    {
        return inDate;
    }

    public void setInDate(String inDate)
    {
        this.inDate = inDate;
    }

    // public String getInCause() {
    // return inCause;
    // }
    // public void setInCause(String inCause) {
    // this.inCause = inCause;
    // }
    public String getOutDept()
    {
        return outDept;
    }

    public void setOutDept(String outDept)
    {
        this.outDept = outDept;
    }

    // public String getOutDate() {
    // return outDate;
    // }
    // public void setOutDate(String outDate) {
    // this.outDate = outDate;
    // }
    // public String getOutMode() {
    // return outMode;
    // }
    // public void setOutMode(String outMode) {
    // this.outMode = outMode;
    // }
    // public String getConsultingDate() {
    // return consultingDate;
    // }
    // public void setConsultingDate(String consultingDate) {
    // this.consultingDate = consultingDate;
    // }
    public String getPatAdmCondition()
    {
        return patAdmCondition;
    }

    public void setPatAdmCondition(String patAdmCondition)
    {
        this.patAdmCondition = patAdmCondition;
    }

	public String getMainDoctor() 
	{
		return mainDoctor;
	}

	public void setMainDoctor(String mainDoctor) 
	{
		this.mainDoctor = mainDoctor;
	}

	public String getOtherDoctor()
	{
		return otherDoctor;
	}

	public void setOtherDoctor(String otherDoctor) 
	{
		this.otherDoctor = otherDoctor;
	}

	public String getConsultingDoctor()
	{
		return consultingDoctor;
	}

	public void setConsultingDoctor(String consultingDoctor) 
	{
		this.consultingDoctor = consultingDoctor;
	}

}
