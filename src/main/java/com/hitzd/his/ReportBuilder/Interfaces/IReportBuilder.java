package com.hitzd.his.ReportBuilder.Interfaces;

import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.his.task.Task;

public interface IReportBuilder 
{
	/**
	 *  ������Ҫ�����ȡʵ��
	 *  ���ش�����Ϣ���޴��󷵻ؿ��ַ���
	 * @param ADate
	 * @return
	 */
	public String BuildReport(String ADate, Task AOwner);
	
	public String BuildReportWithCR(String ADate, TCommonRecord crPatInfo, Task AOwner);
	/**
	 *  ���ر��ε���־�ļ���
	 * @return
	 */
	public String getLogFileName();
	
	public void buildBegin(String ADate, Task AOwner);
	public void buildOver(String ADate, Task AOwner);
}
