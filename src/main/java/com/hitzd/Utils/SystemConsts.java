package com.hitzd.Utils;

import java.util.List;
import java.util.Map;

public class SystemConsts 
{
	private static String ABCAS           = "����ҩ���ٴ�Ӧ�ù���ϵͳ";
	private static String ACSS            = "���˷���ͳ��ƽ̨";
	private static String ADDM            = "����ҩ�����ϱ�ϵͳ";
	private static String ADR             = "������Ӧ�Զ�����뱨��ϵͳ";
	private static String Config          = "��Ϣ�Ƽ���֧��ƽ̨";
	private static String CPMS            = "�ٴ�ҩѧ���ƽ̨";
	private static String DAMS            = "�ٴ������μ��ƽ̨";
	private static String EMR             = "����ҩ����ҩʦ������/����ƽ̨";
	private static String HIMS            = "�пؿƿ���ҩ���ٴ�Ӧ�ü��ƽ̨"; 
	private static String IFMIX           = "ҽҩ֪ʶ���߲�ѯƽ̨";
	private static String MPSS            = "ҽ�����ƽ̨";
	private static String PH              = "���˲����鿴ƽ̨";
	private static String PDSS            = "������ҩ��ȫ���ϵͳ";
	private static String PEAAS           = "ҩƷʹ��ͳ�ƻ��ܷ���ϵͳ";
	private static String Portal          = "";
	private static String PrescEvaluate   = "���ﴦ������Ԥ��ϵͳ";
	private static String PrescReview     = "ҽ������վҽ����ȫ���ƽ̨";
	private static String RDUM            = "������ҩ�����ϱ�ϵͳ";
	private static String WPAMS           = "סԺҩ�����ƽ̨";
	private static String BOSS            = "Ժ��(ҽ�����)���ƽ̨";
	private static String GLMS            = "΢������ϸ����ҩ���ƽ̨";
	private static String DrugManager     = "ҩƷ��ȫ��֪ʶ��Ϣά������";
	private static String DrugMatchWeb    = "����ҩƷ����";
	private static String MatcherIntranet = "ҽԺ��������";
	
	public static List<Map<String, String>> getSystemCodeorName()
	{
	   
	    return null;
	}
	
	/**
	 * ����ƽ̨���� 
	 * @param URI
	 * @return
	 */
	public static String getSystemName(String URI)  
	{
		if ("/ABCAS".equalsIgnoreCase(URI)) return ABCAS;
		if ("/ACSS".equalsIgnoreCase(URI)) return ACSS;
		if ("/ADDM".equalsIgnoreCase(URI)) return ADDM;
		if ("/ADR".equalsIgnoreCase(URI)) return ADR;
		if ("/Config".equalsIgnoreCase(URI)) return Config;
		if ("/CPMS".equalsIgnoreCase(URI)) return CPMS;
		if ("/DAMS".equalsIgnoreCase(URI)) return DAMS;
		if ("/EMR".equalsIgnoreCase(URI)) return EMR;
		if ("/HIMS".equalsIgnoreCase(URI)) return HIMS;
		if ("/".equalsIgnoreCase(URI)) return IFMIX;
		if ("/MPSS".equalsIgnoreCase(URI)) return MPSS;
		if ("/PatientHistory".equalsIgnoreCase(URI)) return PH;
		if ("/HIS_EPH".equalsIgnoreCase(URI)) return PH;
		if ("/PDSS".equalsIgnoreCase(URI)) return PDSS;
		if ("/PEAAS".equalsIgnoreCase(URI)) return PEAAS;
		if ("/Portal".equalsIgnoreCase(URI)) return Portal;
		if ("/PrescEvaluate".equalsIgnoreCase(URI)) return PrescEvaluate;
		if ("/PrescReview".equalsIgnoreCase(URI)) return PrescReview;
		if ("/RDUM".equalsIgnoreCase(URI)) return RDUM;
		if ("/WPAMS".equalsIgnoreCase(URI)) return WPAMS;
		if ("/BOSS".equalsIgnoreCase(URI)) return BOSS;
		if ("/GLMS".equalsIgnoreCase(URI)) return GLMS;
		if ("/DrugManager".equalsIgnoreCase(URI)) return DrugManager;
		if ("/DrugMatchWeb".equalsIgnoreCase(URI)) return DrugMatchWeb;
		if ("/MatcherIntranet".equalsIgnoreCase(URI)) return MatcherIntranet;
		return "ҽԺҩ�¹�����Ϣ���������";
	}
	
	private static String PFURI = "/WebPage/MainFrame.jsp";
	
	/**
	 * ���ؿ�ƽ̨��ַ
	 * @param URI
	 * @return
	 */
	public static String getPlatFormUrl(String URI)
	{
	    if ("/ABCAS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/ACSS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/ADDM".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/ADR".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/Config".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/CPMS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/DAMS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/EMR".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/HIMS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/".equalsIgnoreCase(URI)) return IFMIX;
        if ("/MPSS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/PatientHistory".equalsIgnoreCase(URI)) return URI;
        if ("/HIS_EPH".equalsIgnoreCase(URI)) return URI;
        if ("/PDSS".equalsIgnoreCase(URI)) return PDSS;
        if ("/PEAAS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/Portal".equalsIgnoreCase(URI)) return Portal;
        if ("/PrescEvaluate".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/PrescReview".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/RDUM".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/WPAMS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/BOSS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/GLMS".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/DrugManager".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/DrugMatchWeb".equalsIgnoreCase(URI)) return URI + PFURI;
        if ("/MatcherIntranet".equalsIgnoreCase(URI)) return URI + PFURI;
        return "/AllLogin.jsp"; 
	}
}
