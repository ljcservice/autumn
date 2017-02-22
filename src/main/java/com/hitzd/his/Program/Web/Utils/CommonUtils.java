package com.hitzd.his.Program.Web.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;





import com.hitzd.his.Beans.frame.User;
import com.hitzd.his.sso.SSOController;

/**
 * ��ͨ�ķ���
 * @author liujc
 *
 */
public final class CommonUtils
{
    private CommonUtils() {}
    private static String WebExcelKey = "ExpExcelKey_"; 
    /**
     * sessionȡ�����ݼ� 
     * @param request
     * @param key
     * @return 
     */
    @SuppressWarnings("unchecked")
    public static List<Object> getSessionObject(HttpServletRequest request,String key)
    {
        return (List<Object>)request.getSession().getAttribute(WebExcelKey + key);
    }
    
    /**
     * ɾ�������Ľ����
     * @param request
     * @param key
     */
    public static void delSessionObject(HttpServletRequest request , String key )
    {
        Enumeration<Object> ER = request.getSession().getAttributeNames();
        while(ER.hasMoreElements())
        {
            String keyName = (String)ER.nextElement();
            if(keyName.indexOf(WebExcelKey) != -1)
            {
                request.getSession().removeAttribute(keyName);
            }
        }
    }
    
    /** session �з� ���ݼ� 
     * @param request
     * @param obj
     */
    public static void setSessionObject(HttpServletRequest request,String key ,Object... obj)
    {
        List<Object>  list = new ArrayList<Object>();
        for(Object o : obj)
        {
            list.add(o);
        }
        delSessionObject(request,key);
        request.getSession().setAttribute(WebExcelKey + key, list);
    }
    
    /**
     * ���� ��Ŀ·��  
     */
    public static String  getWebRoot(HttpServletRequest request) {
        return request.getContextPath();
    }
    
    /**
     * �õ�session�е��û���Ϣ
     * @param request
     * @return
     */
    public static User getSessionUser(HttpServletRequest request)
    {
        Object user = SSOController.getUser(request);
        
        return user == null ? null:(User)user;
    }

    /**
     *  �õ�request����ֵ
     * @param request
     * @param parameterName
     * @param defual
     * @return
     */
    public static String getRequestParameter(HttpServletRequest request,String parameterName,String defual)
    {
        if("".equals(parameterName))
            new RuntimeException("�������ֲ���Ϊ��");
        return request.getParameter(parameterName)!= null?request.getParameter(parameterName).trim(): defual;
    }
    
    /**
     * ���ص������� 
     * @return
     */
    public static String getNowDate()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
        
    }
    
    /**
    * �ṩ����ԣ���ȷ�ĳ������㡣�����������������ʱ����scale����ָ
    * �����ȣ��Ժ�������������롣
    * @param v1 ������
    * @param v2 ����
    * @param scale ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
    * @return ������������
    */
    public static double div(double v1, double v2, int scale)
    {
       if (scale < 0) {
        throw new IllegalArgumentException(
          "The scale must be a positive integer or zero");
       }
       BigDecimal b1 = new BigDecimal(Double.toString(v1));
       BigDecimal b2 = new BigDecimal(Double.toString(v2));
       return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
