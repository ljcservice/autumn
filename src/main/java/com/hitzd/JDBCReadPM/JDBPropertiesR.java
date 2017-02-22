package com.hitzd.JDBCReadPM;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

/**
 * ���ƶ�ȡjdbc.properties
 * @author liujc
 */
@Component("jdbc.Properties")
public class JDBPropertiesR extends Properties
{
    private static final long serialVersionUID = 1L;
    public JDBPropertiesR()
    {
        File f = new File("jdbc.properties");
        try
        {
            if(!f.exists())
            {
                throw new RuntimeException("δ�ҵ��ĸ��ļ�:\"" + f.getCanonicalPath() + "\"");
            }
            InputStream input = new BufferedInputStream(new FileInputStream(f));
            load(input);       
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
