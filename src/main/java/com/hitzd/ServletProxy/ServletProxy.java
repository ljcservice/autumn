package com.hitzd.ServletProxy;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hitzd.his.Web.Utils.CommonUtils;
import com.hitzd.his.tree.TreeNode;

/**
 * spring ������
 * @author liujc
 *
 */
@SuppressWarnings("serial")
public class ServletProxy extends GenericServlet
{
    private Servlet proxy;
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
    {
        
        /*2014-07-23 liujc ���  ��ž������ò˵�֮ǰ�Ĳ˵�id �������Ȩ�޽���� */
        /*��ž������ò˵�֮ǰ�Ĳ˵�id */
        String treeNodeMenuId = CommonUtils.getRequestParameter(((HttpServletRequest)req), TreeNode.URLMenuID, "");
        if("".equals(treeNodeMenuId)) treeNodeMenuId = (String)((HttpServletRequest)req).getSession().getAttribute(TreeNode.URLMenuID);
        ((HttpServletRequest)req).getSession().setAttribute(TreeNode.URLMenuID, treeNodeMenuId);
        proxy.service(req, res);
    }
    @Override
    public void init() throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.proxy = (Servlet) wac.getBean(getServletName());
        proxy.init(getServletConfig());
    }
}
