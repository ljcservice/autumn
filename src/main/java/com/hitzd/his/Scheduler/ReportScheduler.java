package com.hitzd.his.Scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;
import com.hitzd.his.ReportBuilder.Interfaces.IReportBuilder;
import com.hitzd.his.SysLog.SysLog4Dcdt;
import com.hitzd.his.Utils.Config;
import com.hitzd.his.Utils.DateUtils;
import com.hitzd.his.task.Task;
import com.hitzd.DBUtils.CommonMapper;


/**
 *   �������������
 *   @author Administrator
 */
public class ReportScheduler extends SysLog4Dcdt
{
	private List<IReportBuilder> lsBuilder = new ArrayList<IReportBuilder>();

    private Task owner = null;
    
	public Task getOwner() {
		return owner;
	}

	public void setOwner(Task owner) {
		this.owner = owner;
	}

	public boolean canRun()
	{
		try
		{
	    	if (Config.getParamValue("SchedulerSwitch").equalsIgnoreCase("false"))
	    	{
	    		Log("��ʱ������Ϊ��״̬�����������ݳ�ȡ����");
	    		return false;
	    	}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
    	return true;
	}
	
	public void beforeBuild(String ADate, String key)
	{
	    JDBCQueryImpl confQuery = DBQueryFactory.getQuery("PEAAS");
	    StringBuffer sql = new StringBuffer("select * from reportBuild where action = '1' and progCode = '").append(key).append("' and orderNo = -1 order by orderNo");
		List<TCommonRecord> builders = confQuery.query(sql.toString(), new CommonMapper());
		for (TCommonRecord cr: builders)
		{
			try 
			{
			    
			    Class clazz        = Class.forName(cr.get("classpath"));
			    Class[] iClazz     = clazz.getInterfaces();
			    boolean flag       = false;
			    if(iClazz.length == 0)
			    {
			        vctLog.add(ADate + " " + cr.get("classpath") + ": ��û�м̳� com.hitzd.his.ReportBuilder.Interfaces.IReportBuilder �ӿ� !");
			        break;
			    }
			    for(Class c : iClazz)
			    {
			        if(IReportBuilder.class == c )
			        {
			            flag = true ;
			            break; 
			        }
			    }
			    if(!flag)
			    {
			        vctLog.add(ADate + " " + cr.get("classpath") + ": ��û�м̳� com.hitzd.his.ReportBuilder.Interfaces.IReportBuilder �ӿ� !");
			        break;
			    }
				IReportBuilder rb = (IReportBuilder) clazz.newInstance();
				rb.buildBegin(ADate, owner);
				lsBuilder.add(rb);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	    confQuery = null;
	}
	
	public void afterBuild(String ADate)
	{
		for (IReportBuilder rb: lsBuilder)
		{
			rb.buildOver(ADate, owner);
		}
	}
	
	public void onBuild(String ADate, TCommonRecord crPatInfo)
	{
		for (IReportBuilder rb: lsBuilder)
		{
			rb.BuildReportWithCR(ADate, crPatInfo, owner);
		}
	}
	
	/**
	 * ��������
	 * @param ADate
	 * @param key
	 * @param vctLog
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void BuildReport(String ADate, String key, Vector<String> vctLog)
	{
	    JDBCQueryImpl confQuery = DBQueryFactory.getQuery("PEAAS");
	    /*
	     * reportid
	     * reportCode
	     * reportName
	     * classPath
	     * createDate
	     * createpersion
	     * orderNo
	     * action 
	     * progCode
	     * */
	    StringBuffer sql = new StringBuffer("select * from reportBuild where action = '1' and progCode = '").append(key).append("' and orderNo >= 0 order by orderNo");
	    //StringBuffer sql = new StringBuffer("select * from reportBuild where action = '1' order by orderNo");
		List<TCommonRecord> builders = confQuery.query(sql.toString(), new CommonMapper());
		String Error = "";
		for (TCommonRecord cr: builders)
		{
			try 
			{
			    Class clazz        = Class.forName(cr.get("classpath"));
			    Class[] iClazz     = clazz.getInterfaces();
			    boolean flag       = false;
			    if(iClazz.length == 0)
			    {
			        vctLog.add(ADate + " " + cr.get("classpath") + ": ��û�м̳� com.hitzd.his.ReportBuilder.Interfaces.IReportBuilder �ӿ� !");
			        break;
			    }
			    for(Class c : iClazz)
			    {
			        if(IReportBuilder.class == c )
			        {
			            flag = true ;
			            break; 
			        }
			    }
			    if(!flag)
			    {
			        vctLog.add(ADate + " " + cr.get("classpath") + ": ��û�м̳� com.hitzd.his.ReportBuilder.Interfaces.IReportBuilder �ӿ� !");
			        break;
			    }
				IReportBuilder rb = (IReportBuilder) clazz.newInstance();
				//rb.buildBegin(ADate, owner);
				Error = rb.BuildReport(ADate, owner);
				//rb.buildOver(ADate, owner);
				if (Error.length() > 0)
				{
					vctLog.add(Error);
					System.out.println((new Date()) + " " + Error);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally
			{
			    confQuery = null;
			}
		}
	}
    
    protected JDBCQueryImpl query = null; 
    public void getQuery(String SysCode)
    {
    	query = DBQueryFactory.getQuery(SysCode);
    }
}
