package com.hitzd.his.report.utils;

/**
 * ������������
 * @author
 *
 */
public class ReportHandlerFactory 
{
	private static Object Locker = new Object();

	public synchronized static ReportHandler getInstance(String Objects) 
	{
		//synchronized (Locker) {
			try 
			{
				return (ReportHandler) JillClassLoader.loadClass(Objects)
						.newInstance();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		//}
	}
}
