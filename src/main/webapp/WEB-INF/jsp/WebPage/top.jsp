<%@page import="com.hitzd.his.Web.Utils.CommonUtils"%>
<%@ page import="com.hitzd.his.Utils.Browser"%>
<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	<title>top</title>
	<link type="text/css" rel="stylesheet" href="/COMMON/bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="/COMMON/hitzd/css/common.css" />
	<script type="text/javascript" src="/COMMON/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/COMMON/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/COMMON/hitzd/js/common.js"></script>
</head>
<body class="<%=Browser.getBrowserName(request)%>" >
	<div class="navbar navbar-static-top">
      <div class="navbar-inner" >
        <div class="pull-right links">
	        ��ã�<%=CommonUtils.getSessionUser(request).getUserName()%>��
	        <!-- <div class="btn-group"> -->
	        	<a href="javascript:ChangeMenu(0);" class="toggle-side-nav">������˵�</a>&nbsp;|&nbsp;
				<a href="javascript:showDesktop()">��ʾ����</a>&nbsp;|&nbsp;
				<a id="show-sys-switcher" href="###">�л�ƽ̨</a>&nbsp;|&nbsp;
	          	<!--  <a href="<%=request.getContextPath()%>/WebPage/user/userCusUpdate.jsp" target="main">�޸�����</a>&nbsp;|&nbsp;-->
			<!-- </div>
			<div class="btn-group"> -->
				<a href="javascript:Logout()">ע��</a>
	        <!-- </div> -->
	        
		</div>
      </div>
    </div>
</body>
</html>