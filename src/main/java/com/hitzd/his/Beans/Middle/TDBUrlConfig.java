package com.hitzd.his.Beans.Middle;

/**
 * �м������Դ�ֵ�� 
 * @author jingcong
 *
 */
public class TDBUrlConfig
{

    /* id */
    private String id;     
    /* ����Դ����*/
    private String db_url;
    /* ��ע */
    private String remark;
    /* ���ݿ����� */
    private String db_base;
    /* ���ݿ��û����� */
    private String  db_user;
    /* ���ݿ����� */
    private String  db_pwd;  
    /* ���ݿ����ӵ�ַ */
    private String  conn_url;
    /* �����ļ��Ƿ��������  */
    private String  flag;    
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getDb_url()
    {
        return db_url;
    }
    public void setDb_url(String db_url)
    {
        this.db_url = db_url;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getDb_base()
    {
        return db_base;
    }
    public void setDb_base(String db_base)
    {
        this.db_base = db_base;
    }
    public String getDb_user()
    {
        return db_user;
    }
    public void setDb_user(String db_user)
    {
        this.db_user = db_user;
    }
    public String getDb_pwd()
    {
        return db_pwd;
    }
    public void setDb_pwd(String db_pwd)
    {
        this.db_pwd = db_pwd;
    }
    public String getConn_url()
    {
        return conn_url;
    }
    public void setConn_url(String conn_url)
    {
        this.conn_url = conn_url;
    }
    public String getFlag()
    {
        return flag;
    }
    public void setFlag(String flag)
    {
        this.flag = flag;
    }
}
