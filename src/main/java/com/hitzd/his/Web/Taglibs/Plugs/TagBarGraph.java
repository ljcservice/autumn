package com.hitzd.his.Web.Taglibs.Plugs;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TagBarGraph extends TagSupport 
{
	/*���⣬��ѡ*/	
	private String title = "";
	/*X��*/
	private List<String> x;
	/*Y��*/
	private List<String> y;
	
	private String xLabel = "";
	private String yLabel = "";
	private String startDate = "";
	private String endDate = "";
	private String width = "";//��ʼ��ֵ
	private String height = "";//��ʼ��ֵ
	
	
	public void setWidth(String width) 
	{
		this.width = width;
	}
	public void setHeight(String height) 
	{
		this.height = height;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public void setxLabel(String xLabel)
	{
		this.xLabel = xLabel;
	}
	public void setyLabel(String yLabel) 
	{
		this.yLabel = yLabel;
	}
	public void setX(List<String> x) 
	{
		this.x = x;
	}
	public void setY(List<String> y) 
	{
		this.y = y;
	}
	private void init()
	{
		if("".equals(height)){setHeight("500");}
		//�Զ�����
		if("".equals(width) ){setWidth(((x.size()*35)>500?(x.size()*35):500) + "");}
	}
	private void reset()
	{
		setTitle("");
		setHeight("");
		setWidth("");
		setxLabel("");
		setyLabel("");
		setStartDate("");
		setEndDate("");
	}
	@Override
	public int doEndTag() throws JspException 
	{
		init();
		JspWriter writer = this.pageContext.getOut();
		String serialId = UUID.randomUUID().toString();
		String aid = UUID.randomUUID().toString();
		//׼������
		if("".equals(title)){title = xLabel + "-" + yLabel ;}
		String dateString = "\";";
		if(!"".equals(startDate) && !"".equals(endDate))
			dateString = "<div style='height:10px;'>&nbsp;</div><span style='font-size:12px;padding-top:30px;'>" +
			"��ʼʱ�䣺" +startDate+ "&nbsp;&nbsp;����ʱ��"+endDate+"</span>\";";
		try 
		{
			writer.append("<div><div id='"+serialId+ "' style='height:"+height+"px;width:"+width+"px;' /></div>\n");
			writer.append("<a id="+aid+" href='javascript:void(0)' style='text-decoration: none;color: black;font-size: 12px;'>�������ͼƬ</a>\n");
			writer.append("<script type=\"text/javascript\">\n");
			writer.append("$(document).ready(function () {\n" +
	"                    $.jqplot.config.enablePlugins = true;\n");
			
			writer.append("var title = \"<span>"+title+"</span><br/>" + dateString + "\n");
			writer.append("$.jqplot('" + serialId +"', [" + GraphTagUtil.get2ListValue(x, y)+ "], {\n" +
	"                        title:title,\n" + 
	"                        series:[{renderer:$.jqplot.BarRenderer}],\n" +
	"                        axes: {\n" +
	"                            xaxis: {\n" +
	"                                renderer: $.jqplot.CategoryAxisRenderer,\n" +
	"                                label: '"+xLabel+"',\n" +
	"                                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,\n" +
	"                                tickRenderer: $.jqplot.CanvasAxisTickRenderer,\n" +
	"                                tickOptions: {\n" +
	"                                    angle: 30,\n" +
	"                                    fontFamily: 'Courier New',\n" +
	"                                    fontSize: '9pt'\n" +
	"                                }\n" +
	"                            },\n" +
	"                            yaxis: {\n" +
	"                                autoscale:true,\n" +
	"                                label: '"+yLabel+"',\n" +
	"                                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,\n" +
	"                                tickRenderer: $.jqplot.CanvasAxisTickRenderer\n" +
	"                            }\n" +
	"                        }\n" +
	"                    });"+
	"                    var chart = $('#"+serialId+"');\n" +
	"                    try{\n" +
	"                        if(!!document.createElement('canvas').getContext){\n" +
	"                            document.getElementById('"+aid+"').onclick=function (){\n" +
	"                                chart.jqplotSaveImage();\n" +
	"                            };\n" +
	"                        }else{\n" +
	"                            $('#" +aid+ "').text('�Բ��������������֧��ͼƬ����,��������֧��HTML5�����');\n"+
	"                        }      \n"+
	"                    }catch(e){\n" +
	"                         $('#" +aid+ "').hide();\n"+
	"                    };\n"+
	"                });\n");
				writer.append("</script>\n");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		reset();
		return super.doEndTag();
		
	}
	

}
