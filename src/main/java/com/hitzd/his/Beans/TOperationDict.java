package com.hitzd.his.Beans;

/**
 * �����ֵ���Ϣ 
 * @author Administrator
 *
 */
public class TOperationDict extends TBaseBean
{
    private static final long serialVersionUID = -1166457621702576200L;
    
    /* �������� */
    private String OperationCode ;
    /* �������� */
    private String OperationName ;
    /* ������  */
    private String inputCode;
    public String getOperationCode()
    {
        return OperationCode;
    }
    public void setOperationCode(String operationCode)
    {
        OperationCode = operationCode;
    }
    public String getOperationName()
    {
        return OperationName;
    }
    public void setOperationName(String operationName)
    {
        OperationName = operationName;
    }
    public String getInputCode()
    {
        return inputCode;
    }
    public void setInputCode(String inputCode)
    {
        this.inputCode = inputCode;
    }
}
