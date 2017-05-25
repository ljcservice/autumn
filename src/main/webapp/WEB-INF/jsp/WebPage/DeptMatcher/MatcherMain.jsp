<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk" %>
<%@page import="com.hitzd.WebPage.PageView" %>
<%@page import="com.hitzd.his.Web.Utils.CommonUtils" %>
<%@page import="com.hitzd.DBUtils.*" %>
<%@page import="java.util.*" %>
<%@page import="com.hitzd.mi.Utils.DeptUtils" %>
<%!
    String getRequestAttribute(HttpServletRequest request, String name, String replace) {
        String result = (String) request.getAttribute(name);
        if (null == result || "".equals(result)) {
            result = replace;
        }
        return result;
    }
%>
<%
    String path = request.getContextPath();
    PageView<TCommonRecord> pageView = (PageView<TCommonRecord>) request.getAttribute("pageView");
    //Ϊ�մ���
    if (null == pageView) {
        pageView = new PageView<TCommonRecord>();
    }
    List<TCommonRecord> deptList = (List<TCommonRecord>)request.getAttribute("allDept");
    String allParent = ",";
	for(TCommonRecord parent:deptList){
		if(!parent.get("parent_dept_code").equals(parent.get("dept_code"))){
			allParent += parent.get("parent_dept_code") + ",";
		}
	}
    
    
%>
<!DOCTYPE html>
<html>
<head>
    <title>���ҹ�ϵά��</title>
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
		function thisAdd(){
			with(formPost){
				o.value = "addDeptPre";
				o.targer = "_self";
				submit();
			}
		}
	    function MatchIt(deptCode) {
	        var url = "<%=path%>/control/DeptMatcher?p_dept_code=" + deptCode + "&o=matcherit";
	       // var obj = new Object();
	        var returnVal = window.showModalDialog(url, obj, "dialogHeight=430px;dialogWidth=950px;resizable=0;scroll=no");
	        with (formPost){
	        	o.value = "query";
	        	submit();
	        }
	    }
	    
	    function DeleteIt(deptCode,deleteFlag) {
	    	if(deleteFlag == false){
	    		alert("ϵͳ��ʾ�������Ҿ����ӿ��ң���Ҫɾ�������ң���ȥ�������ӿ���!");
	    		return ;
	    	}
	        if (confirm("ϵͳ��ʾ��ȷ��ɾ����")) {
	            with(document.formPost){
	            	p_dept_code.value = deptCode;
	                o.value="deleteIt";
	                submit();
	            }
	        }
	    }
	    function ModifyIt(deptCode) {
	    	var url = "<%=path%>/control/DeptMatcher?p_dept_code=" + deptCode + "&o=modifyItPre";
	    	var obj = new Object();
	        var returnVal = window.showModalDialog(url, obj, "dialogHeight=430px;dialogWidth=950px;resizable=0;scroll=no");
	        with (formPost){
	        	o.value = "query";
	        	submit();
	        }
	    }
	    
	</script>
	 <script type="text/javascript"  src="<%=request.getContextPath() %>/WebPage/public/Newjs.js" ></script>
	</head>
<body  background="<%=path%>/skin/images/allbg.gif" style="background-color: white;margin: 0 0 0 0;overflow: hidden;">
<form name="formPost" style="margin: 0 0 0 0" action="<%=path%>/control/DeptMatcher" method="post" target="_self">
    <div class="search-bar-container search-bar-top">
        <input name="o" type="hidden" value="query"/>
        <input type="hidden" name="page" value="<%=pageView.getCurrentpage()%>"/>
        <input type="Hidden" name="p_dept_code">
        <!-- !clinic-attr popover-->
		<div id="popover-dept-name-box" class="popover-box" data-field="field-clinic-attr" style="width: 400px;">
			<div class="close-container">
				<a href="#">���رա�</a>
			</div>
			<input type="hidden" class="JUST_FOR_FIREFOX_DONT_REMOVE"/>
			<div class="popover-body" style="width:258px; height:120px;">
				<label class="chk-all" style="width:240px; ">
					<input type="checkbox" class="chk-all"/>
					ȫ��
				</label>
					<label style="width:118px;">
						<input type="checkbox" value='�ٴ�'/>
						�ٴ�
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='����'/>
						����
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='����Ԫ'/>
						����Ԫ
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='����'/>
						����
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='����'/>
						����
					</label>
			</div>
		</div>
		<!-- !field-outp-or-inp popover-->
		<div id="popover-dept-name-box" class="popover-box" data-field="field-outp-or-inp" style="width: 400px;">
			<div class="close-container">
				<a href="#">���رա�</a>
			</div>
			<input type="hidden" class="JUST_FOR_FIREFOX_DONT_REMOVE"/>
			<div class="popover-body" style="width:258px; height:120px;">
				<label class="chk-all" style="width:240px;">
					<input type="checkbox" class="chk-all"/>
					ȫ��
				</label>
					<label style="width:118px;">
						<input type="checkbox" value='����'/>
						����
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='סԺ'/>
						סԺ
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='����סԺ'/>
						����סԺ
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='����'/>
						����
					</label>
			</div>
		</div>
		<!-- !clinic-attr popover-->
		<div id="popover-dept-name-box" class="popover-box" data-field="field-internal-or-sergery" style="width: 400px; ">
			<div class="close-container">
				<a href="#">���رա�</a>
			</div>
			<input type="hidden" class="JUST_FOR_FIREFOX_DONT_REMOVE"/>
			<div class="popover-body" style="width:258px; height:100px;">
				<label class="chk-all" style="width:240px;">
					<input type="checkbox" class="chk-all"/>
					ȫ��
				</label>
					<label style="width:118px;">
						<input type="checkbox" value='�ڿ�'/>
						�ڿ�
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='���'/>
						���
					</label>
					<label style="width:118px;">
						<input type="checkbox" value='����'/>
						����
					</label>
			</div>
		</div>

        <div class="form-inline search-bar-top search-bar-container">
              ���Ҵ���:<input name="dept_code" value="<%=getRequestAttribute(request,"dept_code","")%>"
                        style="width:70px">
            ��������:<input name="dept_name" value="<%=getRequestAttribute(request,"dept_name","")%>"
                        style="width:80px">
	 <label class="select" >
	 	<a id="popover-dept-name" data-field="field-clinic-attr" class="popover-toggle" href="#">�������Ա�ʶ��</a>
	 </label>
	 <input type="text" id="deptNameValue" value="<%=getRequestAttribute(request,"clinic_attr","")%>" data-field="field-clinic-attr" style="width: 100px;"  name="clinic_attr" readonly/>                        
	 <label class="select" >
	 	<a id="popover-dept-name" data-field="field-outp-or-inp" class="popover-toggle" href="#">����סԺ��ʶ:</a>
	 </label>
	 <input type="text" id="deptNameValue" value="<%=getRequestAttribute(request,"outp_or_inp","")%>" data-field="field-outp-or-inp" style="width: 100px;"  name="outp_or_inp" readonly/>
	 <label class="select" >
	 	<a id="popover-dept-name" data-field="field-internal-or-sergery" class="popover-toggle" href="#">����Ʊ�ʶ:</a>
	 </label>
	 <input type="text" id="deptNameValue" value="<%=getRequestAttribute(request,"internal_or_sergery","")%>" data-field="field-internal-or-sergery" style="width: 100px;"  name="internal_or_sergery" readonly/>
	 
            ������:<input name="input_code" value="<%=getRequestAttribute(request,"input_code","")%>"
                        style="width:80px">
            <!-- <a href="<%=path%>/WebPage/DrugMatcher/MatchHelp.doc" target="_blank">ʹ��˵��</a> -->
        </div>
	    <table width="100%">
	        <tr height="20">
	            <td background="<%=request.getContextPath() %>/images/tbg.gif" align="right">
	            	<img alt="ͳ�Ƽ���"  src="<%=request.getContextPath() %>/images/query.jpg" onclick="javascript:thisSubmit()" style="cursor: pointer;">
		            <input type="button" value=" ��������" onclick="thisAdd()"/>
	            </td>
	        </tr>
	    </table>
    </div>
<center>
    <div style="font-family:����;font-size:20pt;font-color:black;line-height:40px;text-align:center;">
		���ҹ�ϵά��
	</div>
    <table class="table table-bordered header-fixed table-striped table-hover"> 
          <thead>
           <th>No</th>
           <th>���Ҵ���</th>
           <th>��������</th>
           <th>�������Ա�ʶ</th>
           <th>����סԺ��ʶ</th>
           <th>����Ʊ�ʶ</th>
           <th>������</th>
           <th>�����Ҵ���</th>
           <th>����������</th>
           <th id="last_th">����</th>
          </thead>
          <tbody>
          <%
              if (null != pageView) {

                  List<TCommonRecord> list = pageView.getRecords();
                  if(list != null)
                  for (int i = 0; i < list.size(); i++) {
                      TCommonRecord cr = list.get(i);
          %>
          <tr>
              <td><%=cr.get("order_no")%>
              </td>
              <td><%=cr.get("dept_code")%>
              </td>
              <td><%=cr.get("dept_name")%>
              </td>
              <td><%=DeptUtils.deptClinicAttr(cr.get("clinic_attr"))%>
              </td>
              <td><%=DeptUtils.deptOutpOrInp(cr.get("outp_or_inp"))%>
              </td>
              <td><%=DeptUtils.deptInternalOrSergery(cr.get("internal_or_sergery"))%>
              </td>
              <td><%=cr.get("input_code")%>
              </td>
              <% if(cr.get("parent_dept_code").equals(cr.get("dept_code"))){%>
              	<td>��</td>
              	<td>��</td>
              <% }else{ %>
               <td>
               <%=cr.get("parent_dept_code")%>
               </td>
               <td><%=cr.get("parent_dept_name")%>
               </td>
              <% } %>
              <td>
              	   <a href="javascript:DeleteIt('<%=cr.get("dept_code")%>',<%=allParent.contains("," + cr.get("dept_code") + ",")?"false":"true"%>)">ɾ��</a>
              	   <a href="javascript:ModifyIt('<%=cr.get("dept_code")%>')">�޸�</a>
              	   <a href="javascript:MatchIt('<%=cr.get("dept_code")%>')">ά��</a>
              </td>
          </tr>
          <%
                  }
              }
          %>
          </tbody>
          <tfoot>
          <tr height="20">
              <td colspan="10">
                  <%@ include file="../share/fenye.jsp" %>
              </td>
          </tr>
          </tfoot>

      </table>
</center>
</form>
</body>
</html>