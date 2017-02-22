package com.hitzd.DBUtils;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hitzd.his.Utils.DateUtils;

/**
 * �����ݲ�����Ϊ�������ÿ���sql
 * @author jingcong
 *
 */
public class JdbcTemplateHander implements InvocationHandler
{

    private static final Log logger = LogFactory.getLog(JdbcTemplateHander.class);
    
    private JdbcTemplate realJdbcTemplate;
    private JdbcOperations warpedJdbcTemplate;
    private String DBName ;
    private File debugger = null;

    public JdbcTemplateHander()  
    {
    	debugger = new File("debugger");
    } 

    public JdbcOperations bind(JdbcTemplate _JdbcTemplate,String DBName)
    {
        this.DBName = DBName;
        this.realJdbcTemplate = _JdbcTemplate;
        this.warpedJdbcTemplate = (JdbcOperations) Proxy.newProxyInstance(this
                .getClass().getClassLoader(),
                new Class[] { JdbcOperations.class }, this);
        return this.warpedJdbcTemplate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
    	if(debugger.exists())
    	{
        	if(args != null && args.length > 0 )
        	{
        	    System.out.println("[" + "DBName:" + this.DBName + "]");
        		PrintSql(args);
        	}	
    	}
    	Object result  = method.invoke(this.realJdbcTemplate, args);
//        if(result == null)
//        {
//            //System.out.println("���뻺������!");
//            //System.out.println("info:" + args[0]);
//        }	
        return result;
    }
    
    private void PrintSql(Object[] args)
    {
        StringBuffer strInfo =  new StringBuffer(DateUtils.getDateTime());
        for(Object o :args)
        {
            strInfo.append(disposeString(o));
            strInfo.append(disposeObjecArray(o));
            strInfo.append(disposeCommonMapper(o));
        }
        System.out.println(strInfo);
    }
    
    /**
     * �����ַ���
     * @param o
     * @return
     */
    private String disposeString(Object o)
    {
        if(!"String".equals(o.getClass().getSimpleName())) return "";
        return "[" + o.toString() + "]-";
    }
    
    /**
     * ����Object[]  Ŀ���ǳ��룿�����ľ�������ֵ
     * @param o
     * @return
     */
    private String disposeObjecArray(Object o)
    {
        StringBuffer sbfr = new StringBuffer("[param:");
        if(!"Object[]".equals(o.getClass().getSimpleName())) return "";
        Object[] os = (Object[])o;
        for(int i = 0 ; i < os.length ; i++)
        {
            sbfr.append(i + 1).append("?:").append(os[i].toString()).append(",");
        }
        return sbfr.append("]").toString();
    }
    
    /**
     * ����CommonMapper
     * @param o
     * @return
     */
    private String disposeCommonMapper(Object o)
    {
        if(!"CommonMapper".equals(o.getClass().getSimpleName())) return "";
        return "[" + o.getClass().getSimpleName() + "]";
    }
}
