package com.hitzd.his.Beans;

import java.io.Serializable;

public class TBaseBean implements Serializable
{
    /* ʹ�ô��� */
    private long usrCount = 0l ;
    /* ����ʱ��  */
    private String createDate = "";
    /* ���һ��ʹ��ʱ��   */
    private String lastUseDate = "";
    
    public long getUsrCount()
    {
        return usrCount;
    }
    /**
     * ��Ӵ���
     */
    public void addUsrCount()
    {
        this.usrCount++;
    }
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.addUsrCount();
        this.createDate = createDate;
    }
    public String getLastUseDate()
    {
        return lastUseDate;
    }
    public void setLastUseDate(String lastUseDate)
    {
        this.addUsrCount();
        this.lastUseDate = lastUseDate;
    }
}
