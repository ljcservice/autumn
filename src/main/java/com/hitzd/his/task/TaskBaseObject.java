package com.hitzd.his.task;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.hitzd.his.Scheduler.IScheduler;
import com.hitzd.his.Scheduler.SchedulerFactory;
import com.hitzd.his.Utils.DateUtils;

/**
 * ���������࣬����������һ��IScheduler�ӿڵ��������
 * �������������������ɣ�����������ֻ�����¼��������ִ��״̬����Ϣ
 * 
 * @author Administrator
 *
 */

public class TaskBaseObject 
{
	public final static String TaskRunning   = "Running";
	public final static String TaskPausing   = "Pausing";
	public final static String BeforeRunning = "BeforeRunning";
	public final static String AfterRunning  = "AfterRunning";
	public final static String TaskError     = "TaskError";
	
	public TaskBaseObject()
	{
		taskID = UUID.randomUUID().toString();
	}
	
	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	// ������
	private String taskID             = "";
	// ����ʼʱ��
	private String startTime          = "";
	// �������ʱ��
	private String endTime            = "";
	// ������صĲ���
	private Map<String, String> param = new HashMap<String, String>();
	// ����ǰ״̬
	private String status             = "";
	// ������Ҫ���������������
	private int TotalCount            = 0;
	// ������ĵ�ǰ����
	private int CurCount              = 0;
	// ִ������ľ���������
	private String clazz              = "";
	// ����ǰ״̬
	private String taskStatus         = "";
	// ������Ϣ
	private String ErrorInfo          = "";
	// �������
	private String taskTitle          = "";
	// ��������
	private String taskDesc           = "";
	// ����ǰ״̬����
	private String taskStatusDesc     = "";
	
	public void taskOver()
	{
		setTaskStatus(AfterRunning);
	}
	
	public String getTaskStatusDesc() 
	{
		return taskStatusDesc;
	}

	public void setTaskStatusDesc(String taskStatusDesc) 
	{
		this.taskStatusDesc = taskStatusDesc;
	}

	public String getTaskTitle() 
	{
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) 
	{
		this.taskTitle = taskTitle;
	}

	public String getTaskDesc() 
	{
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) 
	{
		this.taskDesc = taskDesc;
	}

	public void runIt()
	{
		if ((clazz == null) && ("".equals(clazz)))
		{
			setTaskStatus(TaskError);
			setErrorInfo("no clazz be defined!");
		}
		
		IScheduler is = SchedulerFactory.getScheduler(clazz);
		if (is == null)
		{
			setTaskStatus(TaskError);
			setErrorInfo("clazz " + clazz + " can not be create!");
		}
		try
		{
			setStartTime(DateUtils.getDateTime());
			setTaskStatus(TaskRunning);
			is.performTask(param, (Task)this);
			setTaskStatus(AfterRunning);
			setEndTime(DateUtils.getDateTime());
		}
		catch (Exception ex)
		{
			setTaskStatus(TaskError);
			setErrorInfo("Task Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public String getStartTime() 
	{
		return startTime;
	}
	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}
	public String getEndTime() 
	{
		return endTime;
	}
	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}
	public Map<String, String> getParam() 
	{
		return param;
	}
	public String getParamValue(String key)
	{
		return param.get(key);
	}
	public void setParam(String key, String value) 
	{
		param.put(key, value);
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public int getTotalCount() 
	{
		return TotalCount;
	}
	public void setTotalCount(int totalCount) 
	{
		TotalCount = totalCount;
	}
	public int getCurCount() 
	{
		return CurCount;
	}
	public void setCurCount(int curCount) 
	{
		CurCount = curCount;
	}
	public String getClazz() 
	{
		return clazz;
	}
	public void setClazz(String clazz) 
	{
		this.clazz = clazz;
	}
	public String getTaskStatus() 
	{
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) 
	{
		this.taskStatus = taskStatus;
	}
	public String getErrorInfo() 
	{
		return ErrorInfo;
	}
	public void setErrorInfo(String errorInfo) 
	{
		ErrorInfo = errorInfo;
	}
}