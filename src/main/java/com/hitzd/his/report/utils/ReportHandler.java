package com.hitzd.his.report.utils;

import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.his.Program.Web.Utils.ChartUtils;
import com.hitzd.his.Program.Web.Utils.CommonUtils;
import com.hitzd.his.Program.Web.Utils.ICharStrategy;

/**
 * ���� ������
 * @author jingcong
 *
 */
public class ReportHandler 
{
	protected String        forward = "/index.jsp";
	
	public String getForword() 
	{
		return forward;
	}

	public void setForword(String forward) 
	{
		this.forward = forward;
	}

	public List<TCommonRecord> doReport(IPage page)
	{
		String o = page.getParameter("o", "");
		if (o != null && !"".equals(o))
		{
			List<TCommonRecord> list = null;
			if (o.equals("query"))
			{
				list = query(page);
			}
			else if (o.equals("excelPrint")) 
			{
				
				excelPrint(page);
			    getSessionObj(page);
			}
			else if (o.equals("Print"))
			{
				
				Print(page);
			    getSessionObj(page);
			}
			else if (o.equals("PDFPrint"))
			{
				PDFPrint(page);
			    getSessionObj(page);
			}
			else if(o.equals("Graph"))
			{
				Graph(page);
				getSessionObj(page);
			}
			else if(o.equals("GraphPanel"))
			{
				GraphPanel(page);
				getSessionObj(page);
			}
			else
			{
				list = option(page);
			}

			return list;
		}
		else
			return first(page);
	}
	/**
	 * ���ڻ�ͼ��ת
	 * @param page
	 */
	public List<TCommonRecord> GraphPanel(IPage page)
	{
		forward = forward + "_GraphPanel.jsp";
		return null;
	}
	/**
	 * ���ڻ�ͼ��ת
	 * @param page
	 */
	public List<TCommonRecord> Graph(IPage page)
	{
		forward = forward + "_Graph.jsp";
		return null;
	}

	public List<TCommonRecord> first(IPage page)
	{
		forward = forward + "_detail.jsp";
		return null;
	}
	
	public List<TCommonRecord> query(IPage page)
	{
		forward = forward + "_detail.jsp";
		return null;
	}
	public List<TCommonRecord> excelPrint(IPage page)
	{
		forward = forward + "_Excel.jsp";
		return null;
	}
	public List<TCommonRecord> Print(IPage page)
	{
		forward = forward + "_Print.jsp";
		return null;
	}
	public List<TCommonRecord> option(IPage page)
	{
		forward = forward + "_detail.jsp";
		return null;
	}
	/**
	 * ��� ������Ϣ 
	 * @param request
	 */
	private void getSessionObj(IPage page)
	{
	    List<Object>  objs = CommonUtils.getSessionObject((HttpServletRequest)page.getAttribute("Request"), getCurClassName());
        if(objs == null)
            //TODO  ��session �Ҳ������ݼ�ʱ ��Ҫһ������ҳ�� �����û����� ��
            this.forward = "/index.jsp" ;
        else
        	((HttpServletRequest)page.getAttribute("Request")).setAttribute("sessionObject",objs);
        
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
	protected void setSessionObject(IPage page, Object... objs)
	{
	    CommonUtils.setSessionObject((HttpServletRequest)page.getAttribute("Request"), getCurClassName(), objs);
	}
	
    /**
     * pdf���� 
     * @param request
     * @param response
     */
    protected void PDFPrint(IPage page){}
	
	
    /**
     * ��ʾͼ�εĻ�������
     * ʹ����ʵ�������Ľӿڷ��� charBaseData.setCharBaseData() ����֯������   
     * @param request
     * @return
     */
    protected String chartView(IPage page, ICharStrategy charBaseData)
    {
        String graphtype = page.getParameter("graphtype", "");  // ͼ�θ�ʽ
        LinkedHashMap<String, String> chart  = charBaseData.setCharBaseData();
        String strChar = "";
        switch(Integer.parseInt(graphtype))
        {
            case 1:                     //����ͼ
                strChar = ChartUtils.ColumnChart(page, chart);
                break;
            case 3:                     //����ͼ
                strChar = ChartUtils.LineChart(page, chart);
                break;
            case 6:                     //��ͼ
                strChar = ChartUtils.PieChart(page, chart);
                break;
        }
        page.setAttribute("ChartsPage", strChar);
        return strChar; 
    }
}
