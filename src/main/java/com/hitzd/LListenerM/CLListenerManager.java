package com.hitzd.LListenerM;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.hitzd.his.Utils.DictCache;

public class CLListenerManager extends ContextLoaderListener
{
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        super.contextInitialized(event);
        ProgInit();
        DictCache.getNewInstance();
    }

    /**
     * ���Խ������������ִ�еķ��� �������� 
     */
    protected void ProgInit()
    {
    }
}
