<%--
  Created by IntelliJ IDEA.
  User: apachexiong
  Date: 10/30/13p
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk" %>
<%@page import="com.hitzd.WebPage.PageView,com.hitzd.his.Utils.Browser" %>
<%@page import="com.hitzd.his.Web.Utils.CommonUtils" %>
<%@page import="com.hitzd.DBUtils.*" %>
<%@page import="java.util.*" %>
<%!
   String getStringRequestAttribute(HttpServletRequest req, String name, String replace){
       String result = (String)req.getAttribute(name);
       if(result == null  || "".equals(result))result = replace;
       return result;
   }
%>

<%
    String path = request.getContextPath();
    String pageNoStr = CommonUtils.getRequestParameter(request, "page", "");
    int pageNo = 1;
    if (null != pageNoStr && "".equals(pageNoStr)) {
        pageNo = Integer.valueOf(pageNo);
    }
    String toxi_property = (String)request.getAttribute("toxi_property");
    if(null == toxi_property){
    	toxi_property = "";
    }
    List<String> toxiList = (List<String>) request.getAttribute("toxiPropertyList");
    if (null == toxiList) {
        toxiList = new ArrayList<String>();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>HIS_Drug_Dict����ץȡ</title>
    <%@include file="/WebPage/reportView/Common.jsp" %>
	<script type="text/javascript">
		//table�߶� ���ݲ�ͬҪ���ֶ�����
		var SCROLL_TABLE_HIEGHT = 340;
	    function ThisTransfer() {
	        if (confirm("ϵͳ��ʾ��ȷ��Ǩ���������ݣ�")) {
	            with (formPost) {
	                o.value = "transferAll";
	                submit();
	            }
	        }
	    }
	    function transferSingle(_drugCode, _drugSpec,_drug_name) {
	        with (formPost) {
	            o.value = "transferSingle";
	            drug_code.value = _drugCode;
	            drug_spec.value = _drugSpec;
	            drug_name.value = _drug_name;
	            submit();
	        }
	
	    }
	</script>
	<script type="text/javascript"  src="<%=request.getContextPath() %>/WebPage/public/Newjs.js" ></script>
</head>
<body  background="<%=path%>/skin/images/allbg.gif" style="background-color: white;margin: 0 0 0 0;overflow: hidden;">
<div class="search-bar-container">  
    <form name="formPost" style="margin: 0 0 0 0" action="<%=path%>/control/FetchDataFromDrugDict" method="post">
        <input name="o" type="hidden" value="query"/>
        <input type="hidden" name="page" value="<%=pageNo%>"/>
        <input type="hidden" name="drug_code" value=""/>
        <input type="hidden" name="drug_spec" value=""/>
        <input type="hidden" name="drug_name" value=""/>
        <!-- !����-->
		<div id="popover-charge-type-box" class="popover-box" data-field="field-dept-name">
			<div class="close-container">
				<a href="#">���رա�</a>
			</div>
			<input type="hidden" class="JUST_FOR_FIREFOX_DONT_REMOVE"/>
			<div class="popover-body" style="width:220px;height:250px;">
				<label class="chk-all" style="width:200px;">
					<input type="checkbox" class="chk-all"/>
					ȫ��
				</label>
					<%
	                    for (String toxi : toxiList) {
	                %>
					<label style="width:100px;">
						<input type="checkbox" value='<%=toxi %>' name="ORG_CODE" text="22"/>
						<%=toxi%>
					</label>
					<% 
						}
                	%>
			</div>
		</div>
        <div class="form-inline search-bar-top">
		    <label>  ҩƷ����:<input name="q_drug_code" value="<%=getStringRequestAttribute(request,"q_drug_code","")%>" style="width:150px"></label>
		    <label>ҩƷ����:<input name="q_drug_name" value="<%=getStringRequestAttribute(request,"q_drug_name","")%>" style="width:150px"> </label>          
            <label class="select" >
            	<a id="popover-dept-name" data-field="field-dept-name" class="popover-toggle" href="#">
            	���ࣺ
            	</a>
            	<input type="text" id="deptNameValue" data-field="field-dept-name" style="width: 100px;"  name="toxi_property" readonly value="<%=toxi_property%>"/>
            </label>
        </div>
    </form>
	<table width="100%">
	    <tr>
	        <td background="<%=request.getContextPath() %>/images/tbg.gif" align="right">
	            <img alt="ͳ�Ƽ���"  src="<%=request.getContextPath() %>/images/query.jpg" onclick="javascript:ThisSubmit()" style="cursor: pointer;">
	            <input type="button" value="ȫ��Ǩ��" onclick="ThisTransfer();"style="width: 60px;" />
	        </td>
	    </tr>
	</table>
</div>
	<div style="font-family:����;font-size:20pt;font-color:black;line-height:40px;text-align:center;">
		HISҩƷ����
	</div>
	<table class="table table-bordered header-fixed table-striped table-hover">
		 <thead>
		      <tr>
		          <th>No</th>
		          <th>ҩƷ����</th>
		          <th>ҩƷ����</th>
		          <th>���</th>
		          <th>��λ</th>
		          <th>����</th>
		          <th>�������</th>
		          <th>��С��λ����</th>
		          <th>������λ</th>
		          <th>ҩƷ����־</th>
		          <th>������</th>
		          <th id="last_th">����</th>
		      </tr>
	      </thead>
	      <tbody>
		      <%
		          PageView<TCommonRecord> pageView = (PageView<TCommonRecord>) request.getAttribute("pageView");
		          if (null != pageView) {
		              List<TCommonRecord> list = pageView.getRecords();
		              if(list != null && list.size()>0)
		              {
			              for (int i = 0; i < list.size(); i++) 
			              {
			                  String bgcolor = "#FFFFFF";
			                  TCommonRecord tcr = list.get(i);
		      %>
		      <tr>
		          <td><%=i + 1 + (pageView.getCurrentpage() - 1) * 12%>
		          </td>
		          <td><%=tcr.get("drug_code")%>
		          </td>
		          <td><%=tcr.get("drug_name")%>
		          </td>
		          <td><%=tcr.get("drug_spec")%>
		          </td>
		          <td><%=tcr.get("units")%>
		          </td>
		          <td><%=tcr.get("drug_form")%>
		          </td>
		          <td><%=tcr.get("toxi_property")%>
		          </td>
		          <td><%=tcr.get("dose_per_unit")%>
		          </td>
		          <td><%=tcr.get("dose_units")%>
		          </td>
		          <td><%=tcr.get("drug_indicator")%>
		          </td>
		          <td><%=tcr.get("input_code")%>
		          </td>
		          <td>
		          	<a href="javascript:transferSingle('<%=tcr.get("drug_code")%>','<%=tcr.get("drug_spec")%>','<%=tcr.get("drug_name")%>')">
		          		Ǩ��
		          	</a>
		          </td>
		      </tr>
		      <%
		              }
		      %>
	      </tbody>
	      	<%  } 
		      if(pageView.getTotalpage() > 1) {%>
	      <tfoot>
		      <tr height="20">
		          <td  colspan="12" >
		              <%@ include file="/WebPage/share/fenye.jsp" %>
		          </td>
		      </tr>
	     </tfoot>
	     <%
		      }   
		          }
	     %>
	</table>
</body>
</html>