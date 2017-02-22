package com.hitzd.his.Beans;

/**
 * ҽ������
 * 
 * @author Administrator
 * 
 */
public class TPatientOrder implements java.io.Serializable
{

    private static final long        serialVersionUID        = 1L;
    /* ����ID */
    private String                   patientID               = null;
    /* ������Ҫ��Ϣ */
    private TPatient                 patient                 = null;
    /* ���˾�����չ��Ϣ */
    private TPatOrderInfoExt         patInfoExt              = null;
    /* ����סԺ��Ϣ */
    private TPatOrderVisitInfo       patVisitInfo            = null;
    /* ������ҩ��¼ */
    private TPatOrderDrug[]          patOrderDrugs           = null;
    /* ����������Ϣ */
    private TPatSigns[]              patSigns                = null;
    /* ҽ�������Ϣ */
    private TPatOrderDiagnosis[]     patOrderDiagnosiss      = null;
    /* ҽ��ҩ�������Ϣ */
    private TPatOrderDrugSensitive[] patOrderDrugSensitives  = null;
    /* ����ҽ��ҽ��ID */
    private String                   doctorID                = null;
    /* ����ҽ��ҽ�� */
    private String                   doctorName              = null;
    /* ����ҽ������ */
    private String                   doctorDeptID            = null;
    /* ����ҽ������ */
    private String                   doctorDeptName          = null;
    /* ����ҽ��ְ�� */
    private String                   doctorTitleID           = null;
    /* ����ҽ��ְ�� */
    private String                   doctorTitleName         = null;
    /* ��ɫ����ҽ���䶯�� */
    private String                   redQuestionChangeNum    = null;
    /* ��ɫ����ҽ���䶯�� */
    private String                   yellowQuestionChangeNum = null;
    /* ������¼ */
    private TPatOperation[]          patOperation           = null;
    /* Ԥ����ҩ��Ϣ  */
    private TPreveUseDrug[]          preveUseDrug           = null;
    /* ������ҩ��Ϣ  */
    private TTreatUseDrug[]          treatUseDrug           = null;
    
    public TPatOperation[] getPatOperation()
    {
		return patOperation;
	}

	public void setPatOperation(TPatOperation[] patOperation)
	{
		this.patOperation = patOperation;
	}

	public String getPatientID()
    {
        return patientID;
    }

    public void setPatientID(String patientID)
    {
        this.patientID = patientID;
    }

    public TPatient getPatient()
    {
        return patient;
    }

    public void setPatient(TPatient patient)
    {
        this.patient = patient;
    }

    public TPatOrderInfoExt getPatInfoExt()
    {
        return patInfoExt;
    }

    public void setPatInfoExt(TPatOrderInfoExt patInfoExt)
    {
        this.patInfoExt = patInfoExt;
    }

    public TPatOrderVisitInfo getPatVisitInfo()
    {
        return patVisitInfo;
    }

    public void setPatVisitInfo(TPatOrderVisitInfo patVisitInfo)
    {
        this.patVisitInfo = patVisitInfo;
    }

    public TPatSigns[] getPatSigns()
    {
        return patSigns;
    }

    public void setPatSigns(TPatSigns[] patSigns)
    {
        this.patSigns = patSigns;
    }

    public TPatOrderDrug[] getPatOrderDrugs()
    {
        return patOrderDrugs;
    }

    public void setPatOrderDrugs(TPatOrderDrug[] patOrderDrugs)
    {
        this.patOrderDrugs = patOrderDrugs;
    }

    public TPatOrderDiagnosis[] getPatOrderDiagnosiss()
    {
        return patOrderDiagnosiss;
    }

    public void setPatOrderDiagnosiss(TPatOrderDiagnosis[] patOrderDiagnosiss)
    {
        this.patOrderDiagnosiss = patOrderDiagnosiss;
    }

    public TPatOrderDrugSensitive[] getPatOrderDrugSensitives()
    {
        return patOrderDrugSensitives;
    }

    public void setPatOrderDrugSensitives(
            TPatOrderDrugSensitive[] patOrderDrugSensitives)
    {
        this.patOrderDrugSensitives = patOrderDrugSensitives;
    }

    public String getDoctorID()
    {
        return doctorID;
    }

    public void setDoctorID(String doctorID)
    {
        this.doctorID = doctorID;
    }

    public String getDoctorName()
    {
        return doctorName;
    }

    public void setDoctorName(String doctorName)
    {
        this.doctorName = doctorName;
    }

    public String getDoctorDeptID()
    {
        return doctorDeptID;
    }

    public void setDoctorDeptID(String doctorDeptID)
    {
        this.doctorDeptID = doctorDeptID;
    }

    public String getDoctorDeptName()
    {
        return doctorDeptName;
    }

    public void setDoctorDeptName(String doctorDeptName)
    {
        this.doctorDeptName = doctorDeptName;
    }

    public String getDoctorTitleID()
    {
        return doctorTitleID;
    }

    public void setDoctorTitleID(String doctorTitleID)
    {
        this.doctorTitleID = doctorTitleID;
    }

    public String getDoctorTitleName()
    {
        return doctorTitleName;
    }

    public void setDoctorTitleName(String doctorTitleName)
    {
        this.doctorTitleName = doctorTitleName;
    }

    public String getRedQuestionChangeNum()
    {
        return redQuestionChangeNum;
    }

    public void setRedQuestionChangeNum(String redQuestionChangeNum)
    {
        this.redQuestionChangeNum = redQuestionChangeNum;
    }

    public String getYellowQuestionChangeNum()
    {
        return yellowQuestionChangeNum;
    }

    public void setYellowQuestionChangeNum(String yellowQuestionChangeNum)
    {
        this.yellowQuestionChangeNum = yellowQuestionChangeNum;
    }

    public TPreveUseDrug[] getPreveUseDrug()
    {
        return preveUseDrug;
    }

    public void setPreveUseDrug(TPreveUseDrug[] preveUseDrug)
    {
        this.preveUseDrug = preveUseDrug;
    }

    public TTreatUseDrug[] getTreatUseDrug()
    {
        return treatUseDrug;
    }

    public void setTreatUseDrug(TTreatUseDrug[] treatUseDrug)
    {
        this.treatUseDrug = treatUseDrug;
    }
}
