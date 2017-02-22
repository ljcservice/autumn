package com.hitzd.his.report.utils;

/**
 * �����ൽjvm ��
 * @author Administrator
 *
 */
interface VMClassLoader
{
  public Class loadClass(String cls) throws ClassNotFoundException;
}

public class JillClassLoader
{
	private static VMClassLoader vmClassLoader;
	static
	{
		String vmVersion = System.getProperty("java.version");
		if (vmVersion.startsWith("1.1") || vmVersion.startsWith("1.0"))
		{
			vmClassLoader = new VMClassLoader()
			{
				public Class loadClass(String cls) throws ClassNotFoundException
				{
					return Class.forName(cls);
				}
			};
		}
		else
		//if (vmVersion.startsWith("1.2"))
		{
			vmClassLoader = new VMClassLoader()
			{
				public Class loadClass(String cls) throws ClassNotFoundException
				{
					Thread t = Thread.currentThread();
					ClassLoader cl = t.getContextClassLoader();
					return cl.loadClass(cls);
				}
			};
		}
	}
	public static Class loadClass(String cls) throws ClassNotFoundException
	{
		return vmClassLoader.loadClass(cls);
	}
}
