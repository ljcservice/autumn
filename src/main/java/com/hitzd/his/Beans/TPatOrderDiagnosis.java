package com.hitzd.his.Beans;

import com.hitzd.his.Utils.Config;
import com.hitzd.his.Utils.DictCache;

/**
 * @description ҽ�������Ϣ�ࣺPatOrderDiagnosis ��Ӧ���ݿ��ҽ�������Ϣ(Pat_Order_Diagnosis)
 * @author
 */
public class TPatOrderDiagnosis
{

    /* ���ID*/
    private String diagnosisDictID;

    /* ������� */
    private String diagnosisName;
    // ��ϴ���*/

    // �����Ա�־ 1����3����2�м�
    // private String acuteFlag;
    // // �Ƿ�����
    // private String isNew;

    // public String getAcuteFlag() {
    // return acuteFlag;
    // }
    // public void setAcuteFlag(String acuteFlag) {
    // this.acuteFlag = acuteFlag;
    // }
    // public String getIsNew() {
    // return isNew;
    // }
    // public void setIsNew(String isNew) {
    // this.isNew = isNew;
    // }
    //
    public String getDiagnosisDictID()
    {
        return diagnosisDictID;
    }

    public void setDiagnosisDictID(String diagnosisDictID)
    {
    	String diagId = diagnosisDictID;
    	this.diagnosisDictID = diagId; 
    	if(Config.getParamValue("diag_icd9_conv_flag").equals("1") || Config.getParamValue("diag_icd10_conv_flag").equals("1") )
    	{
    	    this.diagnosisDictID = diagnosisDictID ;
    	}
    	else
    	if(Config.getParamValue("diagnosis_conv_flag").equals("1"))
        {
    		diagId = DictCache.getNewInstance().getDiagnosisByName(diagId).get("diagnosis_code");
    		this.setDiagnosisName(diagnosisDictID);
        }
    	if(!"".equals(diagId)&& diagId != null) this.diagnosisDictID = diagId;
    }

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
   
    
}
