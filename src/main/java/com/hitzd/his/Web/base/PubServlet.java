package com.hitzd.his.Web.base;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.his.Program.Web.Utils.ChartUtils;
import com.hitzd.his.Program.Web.Utils.CommonUtils;
import com.hitzd.his.Program.Web.Utils.ICharStrategy;

/**
 * servlet�ĸ��࣬����ҳ�����o��ִֵ����Ӧ�ķ���
 * @author tyl
 *
 */
public  class PubServlet extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	/* Ĭ��ת��ҳ */
	protected String        forword = "/index.jsp";
	/**/
	protected String childClassName = "";
	
	/* �����ж��Ƿ�Ϊ �첽�ύ */
    protected boolean AjaxFlag = false;
    
	public PubServlet(){}
	
	/**
	 *  ͨ�ô��� 
	 */
	@SuppressWarnings("rawtypes")
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String o = CommonUtils.getRequestParameter(request, "o", "query");
		o = o.length() == 0 ? "query" : o;
		if (o != null && !"".equals(o))
		{
			List list = null;
			if (o.equals("insert"))
			{
				list = insert(request,response);
			} 
			else if (o.equals("update")) 
			{
				list = update(request,response);
			} 
			else if (o.equals("delete")) 
			{
				list = delete(request,response);
			}
			else if (o.equals("query"))
			{
				list = query(request,response);
			}
			else if (o.equals("modify"))
			{
				list = modify(request,response);
			}
			else if (o.equals("addNew")) 
			{
				list = addNew(request,response);
			}
			else if (o.equals("excelPrint")) 
			{
			    excelPrint(request, response);
			    getSessionObj(request);
			}
			else if (o.equals("Print"))
			{
			    Print(request, response);
			    getSessionObj(request);
			}
			else if(o.equals("Graph"))
			{
				graph(request,response);
				getSessionObj(request);
			}
			else if(o.equals("GraphPanel"))
			{
				graphPanel(request,response);
				getSessionObj(request);
			}
			else if (o.equals("PDFPrint"))
			{
			    PDFPrint(request, response);
			    getSessionObj(request);
			}
			else
			{
				list = option(o, request, response);
			}
			if(list != null)
			    request.setAttribute("List",list);
			/* �ж� �Ƿ����첽�ύ  */
            if(this.AjaxFlag)
            {
                this.forword = "";
            }
            else
            {
                request.getRequestDispatcher(forword).forward(request, response);
            }
			return;
		}
		response.sendRedirect(request.getContextPath() + forword);
	}

	/**
	 * ��� ������Ϣ 
	 * @param request
	 */
	private void getSessionObj(HttpServletRequest request)
	{
	    List<Object>  objs = CommonUtils.getSessionObject(request, getCurClassName());
        if(objs == null)
        {
            //TODO  ��session �Ҳ������ݼ�ʱ ��Ҫһ������ҳ�� �����û����� ��
            if("".equals(this.forword)) this.forword = "/index.jsp" ;
        }
        else
        {
            request.setAttribute("sessionObject",objs);
        }
	}
	/**
	 * ��õ�ǰ������ ��Ϊ session key ������ݼ�
	 * @return
	 */
	protected String getCurClassName()
	{
	    return this.getClass().getSimpleName();
	}
	
	/**
	 * ������ݼ� ���ڴ�ӡ ��������pdf �����ظ����� 
	 * @param request
	 * @param objs
	 */
	protected void setSessionObject(HttpServletRequest request, Object... objs)
	{
	    CommonUtils.setSessionObject(request,getCurClassName(),objs);
	}
	
	
	/**
	 * ��ͼ��  
	 * @param request
	 * @param response
	 */
	protected void graphPanel(HttpServletRequest request,HttpServletResponse response)
	{
	    String graphPanel = CommonUtils.getRequestParameter(request, "graphPanel", "");
	    if(graphPanel.equals(""))
	    {
	            System.out.println("������ַ����Ϊ��!");
	    }
	    this.forword = graphPanel;
	}
	/**
	 * ͼ��  
	 * @param request
	 * @param response
	 */
	protected void graph(HttpServletRequest request,HttpServletResponse response)
	{
	    String graph = CommonUtils.getRequestParameter(request, "graph", "");
	    if(graph.equals(""))
	    {
	            System.out.println("������ַ����Ϊ��!");
	    }
	    this.forword = graph ;
	}
	/**
	 * ����excel  
	 * @param request
	 * @param response
	 */
	protected void excelPrint(HttpServletRequest request,HttpServletResponse response)
	{
	    String excelPrint = CommonUtils.getRequestParameter(request, "excelPrint", "");
	    if(excelPrint.equals(""))
	    {
	            System.out.println("������ַ����Ϊ��!");
	    }
	    this.forword = excelPrint;
	}
	
	 /**
     * ��ӡ 
     * @param request
     * @param response
     */
    protected void Print(HttpServletRequest request,HttpServletResponse response)
    {
        String Print = CommonUtils.getRequestParameter(request, "Print", "");
        if(Print.equals(""))
        {
                System.out.println("��ӡ��ַ����Ϊ��!");
        }
        this.forword = Print;
    }
    
    /**
     * pdf���� 
     * @param request
     * @param response
     */
    protected void PDFPrint(HttpServletRequest request,HttpServletResponse response){}
	
	
    /**
     * ��ʾͼ�εĻ�������
     * ʹ����ʵ�������Ľӿڷ��� charBaseData.setCharBaseData() ����֯������   
     * @param request
     * @return
     */
    protected String chartView(HttpServletRequest request,ICharStrategy charBaseData)
    {
        String graphtype = CommonUtils.getRequestParameter(request,"graphtype","");  // ͼ�θ�ʽ
        LinkedHashMap<String, String> chart  = charBaseData.setCharBaseData();
        String strChar = "";
        switch(Integer.parseInt(graphtype))
        {
            case 1:                     //����ͼ
                strChar = ChartUtils.ColumnChart(chart);
                break;
            case 3:                     //����ͼ
                strChar = ChartUtils.LineChart(chart);
                break;
            case 6:                     //��ͼ
                strChar = ChartUtils.PieChart(chart);
                break;
        }
        request.setAttribute("ChartsPage", strChar);
        return strChar; 
    }
    
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
	/**
	 * ִ�в�������
	 * @param request
	 * @param response
	 * @return
	 */
	protected List<TCommonRecord> insert(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
	/**
	 * ִ�в鿴����ת���޸�ҳ��
	 * @param request
	 * @param response
	 * @return
	 */
	protected List<TCommonRecord> modify(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
	/**
	 * ִ�и���
	 * @param request
	 * @param response
	 * @return
	 */
	protected List<TCommonRecord> update(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
	/**
	 * ִ��ɾ��
	 * @param request
	 * @param response
	 * @return
	 */
	protected List<TCommonRecord> delete(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
	/**
	 * ִ��ת�����ҳ��
	 * @param request
	 * @param response
	 * @return
	 */
	protected List<TCommonRecord> addNew(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
	
	/**
	 * ִ�в�ѯ
	 * @param request
	 * @param response
	 * @return
	 */
	protected List<TCommonRecord> query(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
	
	/**
	 * �û��Զ��巽��
	 * @param o
	 * @param request
	 * @param response
	 * @return
	 */
	protected List<TCommonRecord> option(String o,HttpServletRequest request,HttpServletResponse response){
		return null;
	}
}
