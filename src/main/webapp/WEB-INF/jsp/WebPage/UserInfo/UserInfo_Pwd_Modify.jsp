<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="com.hitzd.his.Beans.frame.User"%>
<%@ page import="com.hitzd.his.Web.Utils.CommonUtils" %>
<%@ page import="com.hitzd.DBUtils.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	<title>�޸�����</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/base.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css" />
	<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/servlet.js"></script>
	<%
		User user = CommonUtils.getSessionUser(request);
	%>
	<script type="text/javascript">
		function ThisSave()
		{
			with(document.formPost)
			{
				if(PWD.value != PWD2.value)
				{
					alert("���벻һ��,��������д��");
					PWD.focus();
					return ;
				}
				if(PWD2.value == "" )
				{
					alert("�û����벻��Ϊ�գ�");
					return;
				}
				o.value = "updPwd";
				submit();
			}
		}
		function ThisBack()
		{
			window.location.href = "<%=request.getContextPath()%>/WebPage/right.jsp";
		}
	</script>
	<%
		String updateInfo = (String)request.getAttribute("updateInfo");
		if(updateInfo != null && "ok".equals(updateInfo))
		{
			%>
			<script>
				alert("�޸���ϣ�");
				window.location.href = "<%=request.getContextPath()%>/WebPage/right.jsp";
			</script>
			<%
		}
	%>
</head>
<body leftmargin="0">
	<form name="formPost" method="post" action="<%=request.getContextPath() %>/control/UserInfo">
		<input type="hidden" name="o"/>
		<input type="hidden" name="USERID" value="<%=user.getUserID()%>"/>
		<input type="hidden" name="USERUNIT" value="<%=user.getUserUnit()%>">
		<table width="98%" align="center" class="table table-bordered table-thin table-striped">
			<tr bgcolor="#E7E7E7">
				<th height="24" colspan="2">�޸�����</th>
			</tr>
			<tr>
				<td width="30%" style="font-weight: bold; text-align: right; vertical-align: middle;">��&nbsp;��&nbsp;����</td>
				<td width="70%" style="text-align: left; vertical-align: middle;"><%=user.getUserName()%></td>
			</tr>
			<tr>
				<td style="font-weight: bold; text-align: right; vertical-align: middle;">��&nbsp;&nbsp;&nbsp;&nbsp;�룺</td>
				<td style="text-align: left; vertical-align: middle;"><input type="password" name="PWD" size="20"></td>
			</tr>
			<tr>
				<td style="font-weight: bold; text-align: right; vertical-align: middle;">����ȷ�ϣ�</td>
				<td style="text-align: left; vertical-align: middle;"><input type="password" name="PWD2" size="20"></td>
			</tr>
			<tr  bgcolor="#E7E7E7">
				<td height="24" colspan="2">
					<img src="<%=request.getContextPath()%>/images/frame/save.jpg" align="absmiddle" onclick="ThisSave()" style="cursor: hand;">
					<img src="<%=request.getContextPath()%>/images/frame/back.jpg" align="absmiddle" onclick="ThisBack()" style="cursor: hand;">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>