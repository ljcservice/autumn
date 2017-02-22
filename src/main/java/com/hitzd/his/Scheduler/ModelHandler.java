package com.hitzd.his.Scheduler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.hitzd.Annotations.MHPerformProp;
import com.hitzd.DBUtils.CommonMapper;
import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;
import com.hitzd.his.SysLog.SysLog4Dcdt;
import com.hitzd.his.report.utils.JillClassLoader;

/**
 * ���������
 * 
 * @author jingcong
 * 
 */
final public class ModelHandler extends SysLog4Dcdt implements Runnable
{
    /* ������ */
    private String       mhGroupCode = "";

    /* ��ѡд����� */
    private List<Object> writerParam = new ArrayList<Object>();

    public List<Object> getWriterParam()
    {
        return writerParam;
    }

    public void setWriterParam(List<Object> writerParam)
    {
        this.writerParam = writerParam;
    }

    /**
     * ����š�
     */
    public ModelHandler(String aMHGroupCode)
    {
        this.mhGroupCode = aMHGroupCode;
    }

    @Override
    public void run()
    {
        PerformIt();
    }

    /**
     * ִ�д���÷���
     */
    @SuppressWarnings ({ "unchecked", "unused" })
    public Object PerformIt()
    {
        JDBCQueryImpl query = DBQueryFactory.getQuery("PEAAS");
        CommonMapper cmr = new CommonMapper();
        try
        {
            if ("".equals(this.mhGroupCode))
                throw new RuntimeException("���Ϊ�գ��޷��ҵ�������� ");
            String sql = "select * from modelHandler t where t.mh_action = 1 and t.mh_groupCode = '"
                    + this.mhGroupCode + "'";
            List<TCommonRecord> list = (List<TCommonRecord>) query.query(sql,
                    cmr);
            if (list == null || list.size() == 0)
                throw new RuntimeException("[" + sql + "]��sqlδ�ܼ������õ����");
            for (TCommonRecord t : list)
            {
                /* ���� */
                Object[] param = new Object[] {};
                // �����·��
                String mhClassPath = t.get("mh_ClassPath");
                // ���Code
                String mhCode = t.get("mh_Code");
                // �������
                String mhCaption = t.get("mh_Caption");
                // �������
                String mhLevel = t.get("mh_Level");
                if ("".equals(mhClassPath))
                    throw new RuntimeException(mhCode + "," + mhCaption + ":û�еǼ����·��!");

                Class clazz = JillClassLoader.loadClass(mhClassPath);
                Method performMethod = null;
                for (Method m : clazz.getMethods())
                {
                    if (m.isAnnotationPresent(MHPerformProp.class))
                    {
                        performMethod = m;
                        /* �����Ѿ�ע��õĲ��� */
                        MHPerformProp mh = m.getAnnotation(MHPerformProp.class);
                        // ���ע����� 
                        Class[] clazzParam = mh.MethodParam();
                        if (this.writerParam != null && this.writerParam.size() > 0 && clazzParam != null &&  this.writerParam.size() == clazzParam.length )
                        {
                            param = (Object[]) this.writerParam.toArray(new Object[0]);
                        }
                        else
                        {
                            new RuntimeException("���������ʵ�������������һ�£�");
                            if(clazzParam != null &&  clazzParam.length > 0 )
                            {
                                param = new Object[clazzParam.length];
                                for (int i =  0 ; i < clazzParam.length ; i++)
                                {
                                   Class c = clazzParam[i];
                                   param[i] =  this.writerParam.size() >= (i+1) ? this.writerParam.get(i):c.newInstance();
                                }
                            }
                        }
                        break;
                    }
                }
                Object obj = clazz.newInstance();
                Object result = performMethod.invoke(obj, param);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            query = null;
            cmr = null;
        }

        return new Object();
    }

//    private static Object setParam4Type(Class cType,String value)
//    {
//        
//
//        
//    }
    
    
}
