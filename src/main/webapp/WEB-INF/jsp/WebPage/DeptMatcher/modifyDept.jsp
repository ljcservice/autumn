<%@page import="com.hitzd.his.Web.Utils.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="com.hitzd.DBUtils.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<title>�����޸�</title>

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
	
	function insert(){
		var flag="true";
		$("[Required]").each(function(i){
			if($("[Required]").eq(i).val()==""){
				alert("����д������Ҫ����");
				flag="false";
				return false;
			}
		});
		
		if(flag=="true"){
			with(document.formPost){
				o.value="updateDept";
				target="submitThisPage";
				submit();
			}
			window.close();
		}
		
	}
	window.name="submitThisPage";
	</script>
</head>

<body leftmargin="0" topmargin="0">
<form name="formPost" style="margin: 0 0 0 0" action="<%=request.getContextPath()%>/control/DeptMatcher" method="post" target="submitThisPage">
	<input name="o" type="hidden" value="query" />
	<table  style="border-collapse:collapse;border:1px solid #ccc;" align="center"  cellpadding="4" width="80%"  cellspacing="1" bgcolor="#CBD8AC">
		<thead>
			<tr  bgcolor="#EEEEEE">
				<th colspan="2">�޸Ŀ���</th>
			</tr>
		</thead>
		<%
			TCommonRecord dept = (TCommonRecord)request.getAttribute("modifyDept");
			if(dept == null){
				dept = new TCommonRecord();
			}
		%>
		<tbody style="font-size:12px">
			<tr   height="22" >
				<td  align="right"  width="50%">���ұ�ţ�</td>
				<td  align="left"><%=dept.get("dept_code")%><input type="hidden" name="a_dept_code" value="<%=dept.get("dept_code")%>" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;"/></td>
			</tr>
			<tr   height="22" >
				<td  align="right"  width="50%">�������ƣ�</td>
				<td  align="left"><input type="text" name="a_dept_name" Required value="<%=dept.get("dept_name")%>" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;"/></td>
			</tr>
			<tr height="22" >
				<td  align="right"  >���ұ�����</td>
				<td  align="left" ><input type="text" name="a_dept_alias" Required value="<%=dept.get("dept_alias")%>" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;"/></td>
			</tr>
			<tr>
				<td  align="right" >�������Ա�ʶ��</td>
				<td  align="left" >
					<select name="a_clinic_attr" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;">
						<option value="0" <%="0".equals(dept.get("clinic_attr"))?"selected":""%>>�ٴ�</option>
						<option value="1" <%="1".equals(dept.get("clinic_attr"))?"selected":""%>>����</option>
						<option value="2" <%="2".equals(dept.get("clinic_attr"))?"selected":""%>>����Ԫ</option>
						<option value="3" <%="3".equals(dept.get("clinic_attr"))?"selected":""%>>����</option>
						<option value="9" <%="9".equals(dept.get("clinic_attr"))?"selected":""%>>����</option>
					</select>
				</td>
			</tr>
			<tr>
				<td  align="right" >����סԺ��ʶ��</td>
				<td  align="left" >
					<select name="a_outp_or_inp" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;">
						<option value="0" <%="0".equals(dept.get("outp_or_inp"))?"selected":""%>>����</option>
						<option value="1" <%="1".equals(dept.get("outp_or_inp"))?"selected":""%>>סԺ</option>
						<option value="2" <%="2".equals(dept.get("outp_or_inp"))?"selected":""%>>����סԺ</option>
						<option value="9" <%="9".equals(dept.get("outp_or_inp"))?"selected":""%>>����</option>
					</select>
				</td>
			</tr>
			<tr>
				<td  align="right" >����Ʊ�ʶ��</td>
				<td  align="left" >
					<select name="a_internal_or_sergery" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;">
						<option value="0" <%="0".equals(dept.get("internal_or_sergery"))?"selected":""%>>�ڿ�</option>
						<option value="1" <%="1".equals(dept.get("internal_or_sergery"))?"selected":""%>>���</option>
						<option value="9" <%="9".equals(dept.get("internal_or_sergery"))?"selected":""%>>����</option>
					</select>
				</td>
			</tr>
			<tr height="22" >
				<td  align="right"  >�������[����]��</td>
				<td  align="left" ><input type="text" name="a_order_no" value="<%=dept.get("order_no")%>" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;"/></td>
			</tr>
			<tr height="22" >
				<td  align="right"  >���������룺</td>
				<td  align="left" ><input type="text" name="a_input_code" value="<%=dept.get("input_code")%>" style="margin-bottom:0px;padding-bottom:0px;width:200px;height:25px;"/></td>
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
</form>
</body>
</html>