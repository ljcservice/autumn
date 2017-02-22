package com.hitzd.his.Web.Taglibs.Plugs;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 
 * @author hershel
 * ���ڻ�����ͼ
 *
 */

public class TagMeterCaugeGraph extends TagSupport
{
	/*���⣬��ѡ*/	
	private String title = "";
	/*X��*/
	private String value = "";
	private String height = "500";
	private String width = "500";
	private List<String> colors;
	private List<String> intervals;
	private List<String> ticks;
	private String msg = "";
	private String label = "";
	private String startDate = "";
	private String endDate = "";
	
	
	public void setIntervals(List<String> intervals) {
		this.intervals = intervals;
	}

	public void setTicks(List<String> ticks) {
		this.ticks = ticks;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setHeight(String height)
	{
		this.height = height;
	}
	public void setWidth(String width) 
	{
		this.width = width;
	}
	private void init()
	{
		if(value.equals(""))
		{
			value = "0";
		}
		if(colors == null )colors = new ArrayList<String>();
		if(intervals == null )intervals = new ArrayList<String>();
		if(ticks == null)ticks = new ArrayList<String>();
	}
	private void reset(){
		setTitle("");
		setValue("");
		setHeight("500");
		setWidth("500");
		setMsg("");
		setLabel("");
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
		if("".equals(title)){title = label;}
		String dateString = "\";";
		if((!"".equals(startDate)) && (!"".equals(endDate)))
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
			writer.append("plot4 = $.jqplot('"+serialId+"',[["+value+"]],{\n" +
			        "   title:'"+title+"',\n" +
			        "       seriesDefaults: {\n" +
			        "           renderer: $.jqplot.MeterGaugeRenderer,\n" +
			        "           rendererOptions: {\n" +
			        "               label: '" +msg+ "',\n" +
			        "               labelHeightAdjust: -5,\n" +
			        "               intervalOuterRadius: 85,\n" +
			        "               ticks: "+GraphTagUtil.convertList2JsArray(ticks)+",\n" +
			        "               intervals:"+GraphTagUtil.convertList2JsArray(intervals)+",\n" +
			        "               intervalColors:" +GraphTagUtil.convertList2JsArray(colors)+ "\n" +
			        "           }\n" +
			        "       }\n" +
			        "   });\n");
			 writer.append(
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
		        	"                    };"+
		        	"                });\n");
				writer.append("</script>\n");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		reset();
		return super.doEndTag();
	}

}
