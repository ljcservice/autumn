package com.hitzd.his.SysLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import com.hitzd.his.Utils.DateUtils;

/**
 * �ڲ�������־ 
 * @author jingcong
 *
 */
public class SysLog4Dcdt
{
    
    /* ������־ */
    protected Vector<String> vctLog     = new Vector<String>();
    
    /**
     * 
     * ���Լ�����������Log��Ϣ�����������Խ�������ϢԽ��
     * 
     */
    private int DebugLevel = 0;
    public void setDebugLevel(int debugLevel)
    {
        System.out.println(toString() + " ���õ����������Ϊ" + debugLevel);
        DebugLevel = debugLevel;
    }
    
    /**
     * ������־
     * @param info ��־���� 
     */
    protected void Log(String info)
    {
        Log(DebugLevel, info);
    }
    
    /**
     * ������־
     * @param level ��־����
     * @param info  
     */
    protected void Log(int level, String info)
    {
        String s = DateUtils.getDateTime() + "  " + info;
        if (level >= DebugLevel)
        {
            vctLog.add(s);
            System.out.println(s);
        }
    }
    
    /**
     * ������־ 
     * @param FileName
     * @return
     */
    public String saveLog(String FileName)
    {
        File file = new File(FileName);
        File tempF = new File(file.getParent());
        try
        {
            if (!tempF.exists())
            {
                tempF.mkdirs();
            }
            FileWriter o = new FileWriter(file);
            for (String x : vctLog)
            {
                o.write(x + "\n");
            }
            o.flush();
            o.close();
        }
        catch (IOException e)
        {
            System.out.print("������־�ļ������쳣");
            e.printStackTrace();
        }
        System.out.println("��־������" + file.getAbsolutePath());
        vctLog.clear();
        return file.getAbsolutePath();
    }
}
