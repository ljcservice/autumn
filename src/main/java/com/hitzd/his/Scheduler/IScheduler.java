package com.hitzd.his.Scheduler;

import java.util.Map;

import com.hitzd.his.task.Task;

public interface IScheduler 
{
	// ����ʼִ��
	public void performTask(Map<String, String> param, Task owner);
	// �ϱ�����״̬
	public void reportStatus(Task owner);
}
