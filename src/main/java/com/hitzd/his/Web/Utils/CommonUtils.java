package com.hitzd.his.Web.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.hitzd.his.Beans.frame.User;
import com.hitzd.his.sso.SSOController;
import com.hitzd.his.tree.TreeNode;

/**
 * ��ͨ�ķ���
 * @author liujc
 *
 */
public final class CommonUtils
{
    private CommonUtils() {}
    
    /**
     * ���� ��Ŀ·��  
     */
    public static String  getWebRoot(HttpServletRequest request) {
        return request.getContextPath();
    }
    
    
    public static String genURL(TreeNode node, HttpServletRequest request)
    {
		String action = "control";
		if (node.getMenuIsRpt().equals("1"))
			action = "report";
		String Program = request.getContextPath();
		String url = node.getMenuID();
		String param = node.getMenuParam();
		if (node.getRefMenu() != null)
		{
			Program = "/" + node.getRefMenu().getProgramID();
			url = node.getRefMenu().getMenuID();
			if (node.getRefMenu().getMenuParam().length() > 0)
				param += "&" + node.getRefMenu().getMenuParam();
			param += "&token=" + (String)request.getSession().getAttribute(SSOController.Token);
		}
		url = Program + "/" + action + "/" + url + "?o=" + param;
		return url;
    }
    /**
     * �õ�session�е��û���Ϣ
     * @param request
     * @return
     */
    public static User getSessionUser(HttpServletRequest request)
    {
    	User user = SSOController.getUser(request);
        return user;
    }

    /**
     *  �õ�request����ֵ
     * @param request
     * @param parameterName
     * @param defual
     * @return
     */
    public static String getRequestParameter(HttpServletRequest request,String parameterName,String defaultValue)
    {
        if("".equals(parameterName))
            new RuntimeException("�������ֲ���Ϊ��");
        return (request.getParameter(parameterName)!= null && !"".equals(request.getParameter(parameterName)))? request.getParameter(parameterName).trim(): defaultValue;
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
    public static double div(double v1, double v2, int scale) {
       if (scale < 0) {
        throw new IllegalArgumentException(
          "The scale must be a positive integer or zero");
       }
       BigDecimal b1 = new BigDecimal(Double.toString(v1));
       BigDecimal b2 = new BigDecimal(Double.toString(v2));
       return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    } 
}
