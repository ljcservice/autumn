package com.hitzd.his.task;

import java.util.ArrayList;
import java.util.List;

/**
 * �����������ÿ������ִ��ʱ����Ҫ��taskʵ���Ž�����������
 * �Ա�����ʱ�鿴����״̬
 * @author Administrator
 *
 */
public class TaskManager 
{
	private static List<Task> list = new ArrayList<Task>();
	
	public static int getTaskCount()
	{
		return list.size();
	}
	
	public static Task getTask(int index)
	{
		if ((index >= 0) && (index < list.size()))
			return list.get(index);
		else
			return null;
	}
	
	public synchronized static int putTask(Task task)
	{
		list.add(task);
		return list.size() - 1;
	}
	
	public static void startTask(int index)
	{
		Task task = getTask(index);
		if (task != null)
			task.runIt();
		task.taskOver();
	}
	
	public static void deleteTask(int index)
	{
		if ((index >= 0) && (index < list.size()))
		{
			@SuppressWarnings("unused")
			Task task = getTask(index);
			list.remove(index);
			task = null;
		}
	}
}