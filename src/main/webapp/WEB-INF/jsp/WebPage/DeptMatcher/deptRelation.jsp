<%@page import="com.hitzd.his.Web.Utils.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="com.hitzd.DBUtils.*" %>
<%@ page import="com.hitzd.his.Utils.DictCache" %>
<%@ page import="java.util.*" %>
<%@page import="com.hitzd.mi.Utils.DeptUtils" %>
<html>
<head>
<title>���ҹ�ϵά��</title>

<style>
.text {
	layout-flow: vertical-ideographic;
	height: 110px;
	line-height: 100%;
}
td{
	border-left:solid 1px #eee;
	border-top:solid 1px #eee;
}
label{
	float:left;
}
<%
	TCommonRecord dept = (TCommonRecord)request.getAttribute("matchingDept");
	if(dept == null)dept = new TCommonRecord();
	List<TCommonRecord> deptList = (List<TCommonRecord>)request.getAttribute("allDept");
	List<TCommonRecord> childDeptList = (List<TCommonRecord>)request.getAttribute("allChildDept");
	if(deptList == null){deptList = new ArrayList<TCommonRecord>();}
	if(childDeptList == null){childDeptList = new ArrayList<TCommonRecord>();}
	String childDeptString = "";
	String childDeptCode = ",";
	for(TCommonRecord child:childDeptList){
		childDeptString += child.get("dept_name")+",";
		childDeptCode += child.get("dept_code") + ",";
	}
	if(childDeptList.size()>0){
		childDeptString = childDeptString.substring(0,childDeptString.length()-1);
	}
	String allParent = ",";
	for(TCommonRecord parent:deptList){
		if(!parent.get("parent_dept_code").equals(parent.get("dept_code"))){
			allParent += parent.get("parent_dept_code") + ",";
		}
	}
%>
</style>
	<script type="text/javascript" src="/COMMON/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/COMMON/jquery/jquery-migrate.min.js"></script>
	<script type="text/javascript" src="/COMMON/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lte IE 6]>
	<script type="text/javascript" src="/COMMON/bootstrap/js/bootstrap-ie.js"></script>
	<![endif]-->
	<script type="text/javascript" src="/COMMON/hitzd/js/common.js"></script>
	<link type="text/css" rel="stylesheet" href="/COMMON/bootstrap/css/bootstrap.min.css" />
	<!--[if lte IE 6]>
	<link rel="stylesheet" type="text/css" href="/COMMON/bootstrap/css/bootstrap-ie6.css">
	<![endif]-->
	<!--[if lte IE 7]>
	<link rel="stylesheet" type="text/css" href="/COMMON/bootstrap/css/ie.css">
	<![endif]-->
	<link type="text/css" rel="stylesheet" href="/COMMON/hitzd/css/common.css" />
	<script type="text/javascript" src="/COMMON/hitzd/js/nav2.1.js"></script>
	<script type="text/javascript"  src="/COMMON/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/COMMON/hitzd/js/tableSortTool.js"></script>
	
	<script type="text/javascript">
	function returnBack(){
		if(confirm("��δ���棬ȷ����һҳ��")){
			window.close();
		}
	}
	/**
	������ϵ�ı��¼�
	*/
	function changeDeptInput(obj){
		var value = obj.value;
		if(value == '1'){
			//������
			$("#childDept").hide();
			$("#parentDept").show();
		}else if(value == '2'){
			//�ӿ���
			$("#childDept").show();
			$("#parentDept").hide();
		}else{
			//��
			$("#childDept").hide();
			$("#parentDept").hide();
		}
	}
	
	
	function insert(){
		var pattern = $("select[name='a_dept_relation']").val();
		if('0' == pattern){
			alert("����д������Ҫ����");
			return;
		}else if('1' == pattern){
			if('<%=childDeptString.trim()%>' != ''){
				alert("ϵͳ��ʾ���ÿ��Һ����ӿ��ң��������Ӹ�����");
				return;
			}
			if($("select[name='a_parent_dept_name']").val() == ''){
				if(confirm("ϵͳ��ʾ����ȷ�Ͻ��ÿ��Ҹ������ÿգ�") == false){
					return;
				};
			}
		}else if('2' == pattern){
			if('<%=dept.get("parent_dept_name").equals(dept.get("dept_name"))?"":dept.get("parent_dept_name")%>' != ''){
				alert("ϵͳ��ʾ���ÿ��Һ��и����ң����������ӿ���");
				return;
			}
			if($("input[name='a_child_dept_names']").val() == ''){
				if(confirm("ϵͳ��ʾ����ȷ�Ͻ��ÿ����ӿ����ÿգ�") == false){
					return;
				};
			}
		}
		with(document.formPost){	
			submit();
		}
		window.returnValue = 'OK';
		window.close();
	}
	$(document).ready(function(){
		$("#childDept").hide();
		$("#parentDept").hide();
	});
	window.name="submitSelf";
	</script>
</head>

<body leftmargin="0" topmargin="0">
<form name="formPost" style="margin: 0 0 0 0" action="<%=request.getContextPath()%>/control/DeptMatcher" method="post" target="submitSelf">
	<input name="o" type="hidden" value="saveMatch" />
	<input name="a_dept_code" type="hidden" value="<%=dept.get("dept_code") %>" />
	<input name="a_dept_name" type="hidden" value="<%=dept.get("dept_name") %>" />
	
	<!-- !����-->
		<div id="popover-charge-type-box" class="popover-box" data-field="field-dept-name" data-field-code="a_child_dept_codes">
			<div class="close-container">
				<a href="#">���رա�</a>
			</div>
			<input type="hidden" class="JUST_FOR_FIREFOX_DONT_REMOVE"/>
			<div class="popover-body" style="width:250px;height:220px;">
				<label class="chk-all" style="width:200px;">
					<input type="checkbox" class="chk-all"/>
					ȫ��
				</label>
					
					<%
						String aclinicAttrFlag = "";
						String aoutpOrInpFlag = "";
						String ainternalOrSergeryFlag = "";
						for(int i=0; i<deptList.size(); i++){
							TCommonRecord department = deptList.get(i);
							int width = 16*(department.get("dept_name").length());
							if(width < 110)width=110;
							if(width > 250)width=250;
							if(!department.get("clinic_attr").equals(aclinicAttrFlag)){
								aclinicAttrFlag = department.get("clinic_attr"); 
								out.append("<label style='width:200px;clear:both'><strong>"+DeptUtils.deptClinicAttr(department.get("clinic_attr"))+"</strong></label>");
							}
							if(!department.get("outp_or_inp").equals(aoutpOrInpFlag)){
								aoutpOrInpFlag = department.get("outp_or_inp"); 
								out.append("<label style='width:200px;clear:both'>|--><strong>"+DeptUtils.deptOutpOrInp(department.get("outp_or_inp"))+"</strong></label>");
							}
							if(!department.get("internal_or_sergery").equals(ainternalOrSergeryFlag)){
								ainternalOrSergeryFlag = department.get("internal_or_sergery"); 
								out.append("<label style='width:200px;clear:both'>|--|--><strong>"+DeptUtils.deptInternalOrSergery(department.get("internal_or_sergery"))+"</strong></label>");
							}
					%>
					
					<label style="width:<%=width%>px;<%=width>140?"clear:both":""%>">
						<%
							boolean isSelected = (","+childDeptCode+",").contains(","+department.get("dept_code")+",");
							boolean isSelectedByOther = 
								(department.get("dept_code").equals(department.get("parent_dept_code")) || (department.get("parent_dept_code")).equals(dept.get("dept_code")) )
									&& !allParent.contains(","+department.get("dept_code")+",");
							String deptColor = "black";
							if(isSelected){
								deptColor="red";
							}else if(!isSelectedByOther){
								deptColor="green";
							}
							
							if(isSelectedByOther && !dept.get("dept_code").equals(department.get("dept_code"))){
						%>
						<input type="checkbox" value='<%=department.get("dept_name")%>' name="ORG_NAME" <%=isSelected?"checked":""%> text="<%=department.get("dept_code")%>"/>
						<%
							}
						%>
							<font color='<%=deptColor %>'>
								<%=department.get("dept_name")%>
							</font>
					</label>
					<%
						}
					%>
			</div>
		</div>
	<div class="search-bar-top">
	<table  style="border-collapse:collapse;border:1px solid #ccc;" align="center"  cellpadding="4" width="80%"  cellspacing="1" bgcolor="#CBD8AC">
		<thead>
			<tr  bgcolor="#EEEEEE">
				<th colspan="2">���ҹ�ϵά��</th>
			</tr>
		</thead>
		<tbody style="font-size:12px">
			<tr   height="22" >
				<td  align="right"  width="40%">�������ƣ�</td>
				<td  align="left">
					<%=dept.get("dept_name") %>
				</td>
			</tr>
			<tr>
				<td  align="right" >���ҹ�ϵ��</td>
				<td  align="left" >
					<select name="a_dept_relation" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;" onchange="changeDeptInput(this)">
						<option value="0" >--��ѡ��--</option>
						<option value="1" >���ø�����</option>
						<option value="2" >�����ӿ���</option>
					</select>
				</td>
			</tr>
			<tr id="parentDept">
				<td  align="right" >�����ң�</td>
				<td  align="left" >
					<select name="a_parent_dept_code" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;">
						<option value="" >---��---</option>
						<%
						String clinicAttrFlag = "";
						String outpOrInpFlag = "";
						String internalOrSergeryFlag = "";
						for(int i=0; i<deptList.size(); i++){
							TCommonRecord department = deptList.get(i);
							if(!department.get("clinic_attr").equals(clinicAttrFlag)){
								clinicAttrFlag = department.get("clinic_attr"); 
								out.append( "<option >|-"+DeptUtils.deptClinicAttr(department.get("clinic_attr"))+"</option>");
							}
							if(!department.get("outp_or_inp").equals(outpOrInpFlag)){
								outpOrInpFlag = department.get("outp_or_inp"); 
								out.append("<option >|-----|-"+DeptUtils.deptOutpOrInp(department.get("outp_or_inp"))+"</option>");
							}
							if(!department.get("internal_or_sergery").equals(internalOrSergeryFlag)){
								internalOrSergeryFlag = department.get("internal_or_sergery"); 
								out.append("<option >|-----|-----|"+DeptUtils.deptInternalOrSergery(department.get("internal_or_sergery"))+"</option>");
							}
							boolean deptFlag = dept.get("parent_dept_code").equals(department.get("dept_code")) && !dept.get("dept_code").equals(dept.get("parent_dept_code"));
						%>
						<option value="<%=department.get("dept_code")%>" <%=deptFlag?"selected":""%>>|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|--<%=department.get("dept_name")%><%=deptFlag?"[ѡ]":""%></option>
						<%	
							}
						%>
					</select>
				</td>
			</tr>
			<tr id="childDept">
				<td  align="right" >�ӿ��ң�</td>
				<td  align="left" >
					<input type="text" id="deptNameValue" data-field="field-dept-name"  style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;float:left;" name="a_child_dept_names" readonly value="<%=childDeptString%>"/>
					<label class="select" style="float:left" >
						<a id="popover-dept-name" data-field="field-dept-name" data-field-code='a_child_dept_codes' text="field-dept-code" class="popover-toggle" href="#">ѡ��</a></label>
            		</label>
            		<input name="a_child_dept_codes" data-field="a_child_dept_codes" type="hidden" value="<%=childDeptCode%>" />
				</td>
			</tr>
			<tr>
				<td align="right">
				��ǰ�����ң�
				</td>
				<td align="left">
				<% if(dept.get("parent_dept_code").equals(dept.get("dept_code"))){%>
                         	��
				<% }else{ %>
                          <%=dept.get("parent_dept_name")%>
				<% } %>
				</td>
			</tr>
			<tr>
				<td align="right"  style="height: 130px;">˵����</td>
				<td>
					1.һ������ֻ�ܾ߱�һ�������ҡ�������һ�������Ѿ��и����ҵ�����£���������Ϊ�ÿ��ҵ��ӿ��ҡ�<br/>
					2.ͨ�������ҹ�ϵ�������б���ѡ������Ҫά���Ŀ��ҹ�ϵ��<br/>
					3.�ӿ�����ɫ˵������ɫ->�ÿ����Ѿ�ѡ����ӿ���;��ɫ->��ɫ��ʶ�����Ѿ���ѡ��Ϊ���������ӿ���;��ɫ->��ѡ����</br>
					4.���ҷ��ࣺ�Ȱ��տ������Է��࣬�ٰ�������סԺ��ʶ���࣬���������Ʒ��ࣻ</br>
					 &nbsp;&nbsp;�������ԣ�0-�ٴ� 1-���� 2-����Ԫ 3-���� 9-����</br>
   					 &nbsp;&nbsp;����סԺ��ʶ��0-���� 1-סԺ 2-����סԺ 9����</br>
   					 &nbsp;&nbsp;����Ʊ�ʶ��0-�ڿ� 1-��� 9-����</br>
				</td>
			</tr>
		</tbody>
		<tfoot style="font-size:14px">
			<tr bgcolor="#EEEEEE">
				<td colspan="4" align="center">
					<a href="javascript:insert()">����</a>&nbsp;|&nbsp;<a href="javascript:returnBack()">����</a>
				</td>
			</tr>
		
		</tfoot>
 	</table>
 	</div>
</form>
</body>
</html>