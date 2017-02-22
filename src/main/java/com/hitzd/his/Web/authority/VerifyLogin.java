package com.hitzd.his.Web.authority;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.hitzd.his.Beans.frame.User;
import com.hitzd.his.Service.authority.IAuthorization;
import com.hitzd.his.Web.Utils.CommonUtils;
import com.hitzd.his.sso.SSOController;


/**
 * ��½����   
 */
@Component("VerifyLogin")
public class VerifyLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	@Resource(name="authorization")
	private IAuthorization authorization;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    
	    String o = CommonUtils.getRequestParameter(request, "o", "");
	    if(o.equals("logout"))
	    {
	    	User user = SSOController.getUser(request);
	    	String token = (String)request.getSession().getAttribute(SSOController.Token);
	        SSOController.logoutUser(user, token,request.getContextPath());
	        request.getSession().invalidate();
	        response.sendRedirect("/AllLogin.jsp");
	        return ;
	    }
	    /* �ж��Ƿ��root����   */
	    String root = CommonUtils.getRequestParameter(request, "root", "" );
	    if("yes".equals(root))
	    {
	        this.rootLoginM(request, response);
	        return ;
	    }
	    /* ƽ̨ͨ�õ�½���� */
	    CommLoginM(request, response);
	}

	/**
	 * ����ƽ̨����
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void rootLoginM(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
	    String UserName = CommonUtils.getRequestParameter(request, "UserName", "");
        String PassWord = CommonUtils.getRequestParameter(request, "PassWord", "");
        if ((UserName.length() > 0) && (PassWord.length() > 0))
        {
            User user = authorization.VerifyLogin(UserName, PassWord, request.getContextPath(),true);
            int error = authorization.getErrorCode();
            if (error == 0)
            {
                request.getSession().setAttribute(User.UserSessionCode, user);
                String token = SSOController.loginUser(user, request.getContextPath());
                request.getSession().setAttribute(SSOController.Token, token);
                // 2014-04-28 ���Z��  ���� oken���� �����û���¼Ψһ��ʾ 
                
                response.sendRedirect(request.getContextPath() + "/AllLogin.jsp?error=0&" + SSOController.getToken(request.getSession()));
            }
            else 
            {
                // �û������ڻ��������
                response.sendRedirect(request.getContextPath() + "/AllLogin.jsp?error=" + error);
            }
        }
        else
        {
            // �û������벻ȫ
            response.sendRedirect( request.getContextPath() + "/AllLogin.jsp?error=100");
        }
	}
	
	/**
	 * ƽ̨ͨ�õ�½���� 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
    private void CommLoginM(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String UserName = CommonUtils.getRequestParameter(request, "UserName", "");
		String PassWord = CommonUtils.getRequestParameter(request, "PassWord", "");
		if ((UserName.length() > 0) && (PassWord.length() > 0))
		{
			User user = authorization.VerifyLogin(UserName, PassWord, request.getContextPath());
			int error = authorization.getErrorCode();
			if (error == 0)
            {
                request.getSession().setAttribute(User.UserSessionCode, user);
                String token = SSOController.loginUser(user, request.getContextPath());
                request.getSession().setAttribute(SSOController.Token, token);
                response.sendRedirect(request.getContextPath() + "/WebPage/MainFrame.jsp?" + SSOController.getToken(request.getSession()));
            }
            else
            {
                // �û������ڻ��������
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=" + error);
            }
        }
        else
        {
            // �û������벻ȫ
            response.sendRedirect( request.getContextPath() + "/index.jsp?error=100");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}