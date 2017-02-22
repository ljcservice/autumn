package com.hitzd.his.casehistory.helper;

import java.util.List;
import java.util.Map;

import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.WebPage.PageView;
import com.hitzd.his.Beans.Middle.TTableConfig;
import com.hitzd.his.casehistory.CaseHistory;

public interface ICaseHistoryHelper 
{
	/**
	 * �����¼����
	 */
	public static String Key_PatVisit         = "PatVisit";

	public static String Key_OperationMaster  = "OperationMaster";
	public static String Key_Operation        = "Operation";
	public static String Key_OperationName    = "OperationName";
	
	public static String Key_DrugDispenseRec  = "DrugDispenseRec";
	public static String Key_PatsInHospital   = "PatsInHospital";
	public static String Key_Orders           = "Orders";
	public static String Key_Diagnosis        = "Diagnosis";
	public static String Key_InpBillDetail    = "InpBillDetail";
	public static String Key_VitalSignsRec    = "VitalSignsRec";
	public static String Key_DrugPrescMaster  = "DrugPrescMaster";
	public static String Key_DrugPrescDetail  = "DrugPrescDetail";
	
	public static String Key_LabTestMaster    = "LabTestMaster";
	public static String Key_LabTestItems     = "LabTestItems";
	public static String Key_LabResult        = "LabResult";
	
	public static String Key_ExamMaster       = "ExamMaster";
	public static String Key_ExamItems        = "ExamItems";
	public static String Key_ExamReport       = "ExamReport";

	public static String Key_GermTest         = "GermTest";
	public static String Key_DrugSensitResult = "DrugSensitResult";
	public static String Key_GermTestResult   = "GermTestResult";
	public static String ROWID = "rowid";
	public static Map<String, TTableConfig> tables = CaseHistoryFactory.getTableMap();

    
    /**
     * ��ȡhis��������
     * @param originalTable
     * @return
     */
	public abstract String getDbUrl(String originalTable);
	
	/**
	 * ��ȡָ�������ź�סԺ�ŵĲ��˵Ĳ�������ʱ���ؿ�ֵ
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 */
	public abstract CaseHistory fetchCaseHistory(String PatientID, String VisitID, JDBCQueryImpl srcQuery);
	/**
	 * ���ָ�������ź�סԺ�ŵĲ��˵Ĳ���������TCommonRecord�ṹ��������crPatInfo����ö��󣬸ýṹ�������˻�����Ϣ��ͬʱ�������˵ľ�����Ϣ
	 * ���˵ľ�����Ϣͨ�����ؽ����crPatInfo.getObj(ICaseHistoryHelper.Key_PatVisit)����ã�������Ϣ����Ϊһ��TCommonRecord����������crVisit����
	 * ���˵ľ�����Ϣ�а������˵�ҽ������ϡ���������ҩ����顢���顢΢����������˵�����Ϣ;
	 * ��ò��˵�ҽ����ͨ��������Ϣ��crVisit.getObj(ICaseHistoryHelper.Key_Orders)����ã�
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 */
	public abstract TCommonRecord fetchCaseHistory2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery);
	
	/**
	 * ��ò��˵Ļ�����Ϣ��ͨ��PatientIDȡ���û�������Ϣ
	 * @param PatientID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract TCommonRecord fetchPatInfo2CR(String PatientID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract TCommonRecord fetchPatInfo2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchPatInfo2List(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵ľ�����Ϣ���������סԺ����Ϣ
	 * @param PatientID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchPatVisit2CR(String PatientID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchPatVisit2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	public abstract TCommonRecord fetchPatVisit2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ��ѯ���˵ľ�����Ϣ��ͨ����������ƴ�������sql���
	 * @param strFields �ֶ��б�һ����FieldName1, FieldName2,...FieldNameN��Ҳ������*��������count(*)�ȵ�
	 * @param lsWheres �����б���һ��TCommonRecord���б�ÿһ��TCommonRecord����FieldName, GroupNo, FieldValue, FieldType, Relation, Condition
	 * ����
	 * FieldName : ��ʾ���������ĸ��ֶ����
	 * FieldValue: ��ʾ���������ֶ�ȡֵ�����FieldValue�ĵ�һ�������һ���ַ�Ϊ%��������like���
	 * FieldType : ��ʾ���ֶε����ͣ������Char����ƴ�õ�sql�а���'�����򲻰���'��Ĭ��ֵΪ��char��������'
	 * Relation  : ��ʾ�ֶ������ֶ�ֵ֮��Ĺ�ϵ��=��>=��<=�ȵȣ�Ĭ��ֵΪ��ֵ����ʾ��ϵΪ=
	 * GroupNo   : ��ʾ����������ţ����2������Ϊһ�飬��Ҫ������ѯ�����磺
	 * ((StartDateTime <= to_date('2011-01-01', 'yyyy-mm-dd')) and (StopDateTime >= to_date('2011-02-01', 'yyyy-mm-dd')))
	 * �����2�������ͱ���һ���ã��������2���ֶ�Ϊһ�飬GroupNo��Ӧ�÷����ظ���Ĭ��Ϊ��ֵ����ʾ��������������һ������
	 * Condition : ��ʾ������ǰ�������and ���� or��Ĭ��ֵΪ��ֵ����ʾǰ��� and 
	 * @param strGroup group by ��������ŵ��ֶ��б�����1�����ݣ�FieldName
	 * @param strOrder order by ������ŵ��ֶ��б�����2�����ݣ�һ����FieldName��һ����By��By��ȡֵΪ�գ�asc��desc
	 * 
	 * @return ���ط������������ݽ��
	 * @throws Exception
	 * 
	 * �ٸ����ӣ�
	 * ��ѯ���˵�ȫ����Ϣ������Ϊ2011��1��1����Ժ�Ĳ��ˣ������������Ĳ��ˣ����Ұ����˵�סԺ���ڽ�����������
	 * String strFields = "*";
	 * List<TCommonRecord> lsWheres = new ArrayList<TCommonRecord>();
	 * TCommonRecord crWheres1 = new TCommonRecord();
	 * crWheres1.set("FieldName", "Name");
	 * crWheres1.set("FieldValue", "��%");
	 * lsWheres.add(crWheres1);
	 * 
	 * crWheres1 = new TCommonRecord();
	 * crWheres1.set("FieldName", "Admission_Date");
	 * crWheres1.set("FieldValue", "to_date('2011-01-01', 'yyyy-mm-dd')");
	 * crWheres1.set("Relation", "<=");
	 * crWheres1.set("GroupNo", "1");
	 * crWheres1.set("Condition", "and");
	 * lsWheres.add(crWheres1);
	 * 
	 * crWheres1 = new TCommonRecord();
	 * crWheres1.set("FieldName", "Discharge_Date");
	 * crWheres1.set("FieldValue", "to_date('2011-01-01', 'yyyy-mm-dd')");
	 * crWheres1.set("Relation", ">=");
	 * crWheres1.set("GroupNo", "1");
	 * crWheres1.set("Condition", "and");
	 * lsWheres.add(crWheres1);
	 * 
	 * List<TCommonRecord> lsPatVisitInfo = fetchPatVisitInfo(strFields, lsWheres, "", "Patient_ID");
	 * ��������ƴ����sqlΪ
	 * select * from Pat_Visit where 1=1 and Name like '��%' and 
	 *   ((Admission_Date <= to_date('2011-01-01', 'yyyy-mm-dd')) and (Discharge_Date >= to_date('2011-01-01', 'yyyy-mm-dd')))
	 * order by Patient_ID asc  
	 * 
	 */
	public abstract List<TCommonRecord> fetchPatVisitInfo(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ����sql���
	 * @param strFields
	 * @param strTables
	 * @param lsWheres
	 * @param strGroup
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 */
	public abstract String genSQL(String strFields, String strTables, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders);
	/**
	 * ����sql���
	 * @param strFields
	 * @param strTables
	 * @param lsWheres
	 * @param strGroup
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 */
	public abstract String genSQLComm(String strFields, String strTables, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders);
	
	/**
	 * ��ò��˵�������Ϣ��ָ���˲���id�;���id
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchOperationMaster2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchOperationMaster2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
     * ��� �����ڿƼ�¼ ��Ϣ��ָ���˲���id�;���id
     * @param PatientID
     * @param VisitID
     * @param srcQuery
     * @return
     * @throws Exception
     */
    public abstract List<TCommonRecord> fetchTransfer2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
    public abstract List<TCommonRecord> fetchTransfer2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * 
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchOperation2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchOperation2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * 
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchOperationName2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchOperationName2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵İ�ҩ��¼
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugDispenseRec2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchDrugDispenseRec2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵���Ժ��Ϣ
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchPatsInHospital2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchPatsInHospital2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵�ҽ����¼
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchOrders2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchOrders2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵���ϼ�¼
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDiagnosis2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchDiagnosis2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵ķ�����ϸ
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchInpBillDetail2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchInpBillDetail2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵ļ����Ϣ
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchLabTestMaster2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchLabTestMaster2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchLabResult2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchLabTestItems2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵�������Ϣ
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchVitalSignsRec2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchVitalSignsRec2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵ļ�����Ϣ
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchExamMaster2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchExamReport2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchExamItems2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchExamMaster2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵�΢�������
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchGermTest2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchGermTest2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord>  fetchDrugSensitResult2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵�ͿƬ������Ϣ
	 * @param PatientID
	 * @param VisitID
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchGermTestResult2CR(String PatientID, String VisitID, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchGermTestResult2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ò��˵�סԺ������Ϣ
	 * @param PatientID
	 * @param InpDate
	 * @param OutDate
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugPrescMaster2CR(String PatientID, String InpDate, String OutDate, JDBCQueryImpl srcQuery) throws Exception;
	public abstract List<TCommonRecord> fetchDrugPrescMaster2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	public abstract List<TCommonRecord> fetchDrugPrescDetail2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	/**
	 * ��ѯ���˵�ҽ����Ϣ
	 * @param PatientID
	 * @param VisitID
	 * @param OrderClass
	 * @param StartDateTime
	 * @param EndDateTime
	 * @param RepeatIndicator
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> queryOrders(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ��ѯҩƷ�ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ��������
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchMrIndex2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * �����ļ�����
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchMrFileIndex2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * Ƶ���ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchPerformFreqDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ;���ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchAdministrationDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ����ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDiagnosisDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҩƷ����ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugClassDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ϸ��ҩ���ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchGermdrugsensitDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ΢�����ֵ��
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchGermCodeDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * �걾��Ϣ
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchSpecimanDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҽ����Ϣ
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchStaffDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ������Ϣ
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDeptDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * �ѱ���Ϣ
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchChargeTypeDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * �����Ϣ
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchIdentityDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ������Ϣ
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugFormDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ��ҳ��ѯ
	 * @param maxresult
	 * @param currentpage
	 * @param fields
	 * @param tableName
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @return
	 */
	public abstract PageView<TCommonRecord> fetchTable2PV(int maxresult, int currentpage, String strFields, String strTables, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery);
	
	/**
	 * ����״̬�仯�ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchPatientStatusChgDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҩƷ��Ӧ��Ŀ¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugSupplierCatalog2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҩƷ��������ֵ�
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugToxiPropertyDict2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҩƷ��������
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugExportMaster2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҩƷ������ϸ��
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugExportDetail2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҩƷ�������
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugImportMaster2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ҩƷ�����ϸ��
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchDrugImportDetail2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ��������¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchClinicMaster2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ����ҽ������¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchOutpOrders2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ����ҽ����ϸ��¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchOutpPresc2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ���ﲡ����¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchOutpMr2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ������ϼ�¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchClinicDiagnosis2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * �ȴ����˼�¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchWaitBedPats2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * �������ת��״̬�仯��־
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchAdtLog2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * ׼����Ժ���˼�¼
	 * @param strFields
	 * @param lsWheres
	 * @param lsGroups
	 * @param lsOrders
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> fetchPreDischgedPats2CR(String strFields, List<TCommonRecord> lsWheres, List<TCommonRecord> lsGroups, List<TCommonRecord> lsOrders, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * Ԥ�����ѯ�б�
	 * @param sql
	 * @param objects
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract List<TCommonRecord> queryForListByPS(String sql, JDBCQueryImpl srcQuery) throws Exception;
	
	/**
	 * Ԥ�����ѯ����
	 * @param sql
	 * @param objects
	 * @param srcQuery
	 * @return
	 * @throws Exception
	 */
	public abstract TCommonRecord queryForObjectByPS(String sql, JDBCQueryImpl srcQuery) throws Exception;
}
