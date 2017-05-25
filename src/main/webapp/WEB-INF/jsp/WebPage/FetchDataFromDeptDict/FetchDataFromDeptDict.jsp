<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk" %>
<%@page import="com.hitzd.WebPage.PageView,com.hitzd.his.Utils.Browser" %>
<%@page import="com.hitzd.his.Web.Utils.CommonUtils" %>
<%@page import="com.hitzd.DBUtils.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
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
%>
<!DOCTYPE html>
<html>
<head>
    <title>���ҵ���</title>
    <%@include file="/WebPage/reportView/Common.jsp" %>
	<script type="text/javascript">
		//table�߶� ���ݲ�ͬҪ���ֶ�����
		var SCROLL_TABLE_HIEGHT = 340;
		function thisSubmit(){
			with(formPost){
				o.value="query";
				o.target="_self";
				submit();
				
			}
			
		}
		
		 function ThisTransfer() {
		        if (confirm("ϵͳ��ʾ��ȷ��Ǩ���������ݣ�")) {
		            with (formPost) {
		                o.value = "transferAll";
		                submit();
		            }
		        }
		    }
		    function transferSingle(deptCode) {
		        with (formPost) {
		            o.value = "transferSingle";
		            p_dept_code.value = deptCode;
		            submit();
		        }
		
		    }
		</script>
	<script type="text/javascript"  src="<%=request.getContextPath() %>/WebPage/public/Newjs.js" ></script>
</head>

<body  background="<%=path%>/skin/images/allbg.gif" style="background-color: white;margin: 0 0 0 0;overflow: hidden;">
<div class="search-bar-container">
<form name="formPost" style="margin: 0 0 0 0" action="<%=path%>/control/FetchDataFromDeptDict" method="post">
        <input name="o" type="hidden" value="query"/>
        <input type="hidden" name="page" value="<%=pageNo%>"/>
        <input type="hidden" name="p_dept_code" value=""/>

        <div class="form-inline search-bar-top search-bar-container">
	            <label>���Ҵ���:<input name="dept_code" value="<%=getStringRequestAttribute(request,"dept_code","")%>"
                        style="width:70px"></label>
            <label>��������:<input name="dept_name" value="<%=getStringRequestAttribute(request,"dept_name","")%>"
                        style="width:80px"></label>
	 		<label> �������Ա�ʶ:<input name="clinic_attr" value="<%=getStringRequestAttribute(request,"clinic_attr","")%>"
                        style="width:80px"></label>
	  		<label>����סԺ��ʶ:<input name="outp_or_inp" value="<%=getStringRequestAttribute(request,"outp_or_inp","")%>"
                        style="width:80px"></label>
           	<label>  ����Ʊ�ʶ:<input name="internal_or_sergery" value="<%=getStringRequestAttribute(request,"internal_or_sergery","")%>"
                        style="width:80px"></label>
          	<label>  ������:<input name="input_code" value="<%=getStringRequestAttribute(request,"input_code","")%>"
                        style="width:80px"></label>
	     </div>
		   <table width="100%">
			    <tr >
			        <td  align="right">
						<div class="form-inline search-bar-bottom" style="height: 20px">
							<img alt="ͳ�Ƽ���"  src="<%=request.getContextPath() %>/images/query.jpg" onclick="javascript:thisSubmit()" style="cursor: pointer;">
							<input type="button" value="ȫ��Ǩ��" onclick="ThisTransfer();" style="width:60px;" />
						</div>
					</td>
			    </tr>
			   </table>
    </div>
<center>
   <div style="font-family:����;font-size:20pt;font-color:black;line-height:40px;text-align:center;">
		HIS���ҵ���
	</div>
    <table class="table table-bordered header-fixed table-striped table-hover">  
    	<thead> 
	        <tr>
	             <th>No</th>
	             <th>���Ҵ���</th>
	             <th>��������</th>
	             <th>�������Ա�ʶ</th>
	             <th>����סԺ��ʶ</th>
	             <th>����Ʊ�ʶ</th>
	             <th>������</th>
	             <th id="last_th">����</th>
	         </tr>
         </thead>
       <tbody>
             <%
             PageView<TCommonRecord> pageView = (PageView<TCommonRecord>) request.getAttribute("pageView");
             if (null != pageView) {

                 List<TCommonRecord> list = pageView.getRecords();
                 if(list != null)
                 for (int i = 0; i < list.size(); i++) {
                     String bgcolor = "#FFFFFF";
                     TCommonRecord tcr = list.get(i);
         %>
         <tr>
             <td><%=tcr.get("serial_no")%>
             </td>
             <td><%=tcr.get("dept_code")%>
             </td>
             <td><%=tcr.get("dept_name")%>
             </td>
             <td><%=tcr.get("clinic_attr")%>
             </td>
             <td><%=tcr.get("outp_or_inp")%>
             </td>
             <td><%=tcr.get("internal_or_sergery")%>
             </td>
             <td><%=tcr.get("input_code")%>
             </td>
             <td>
                 <a href="javascript:transferSingle('<%=tcr.get("dept_code")%>')">Ǩ��</a>
             </td>
         </tr>
         <%
                 }
             }
         %>
       </tbody>
       <tfoot>
           <tr height="20">
               <td colspan="12">
                   <%@ include file="../share/fenye.jsp" %>
               </td>
           </tr>
     	</tfoot>
    </table>
</center>
</form>
</body>
</html>