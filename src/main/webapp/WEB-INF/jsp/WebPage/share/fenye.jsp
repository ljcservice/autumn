<%@page language="java" pageEncoding="gbk"%>
<%@page import="com.hitzd.WebPage.PageView"%>
<script type="text/javascript">
  function topage(page)
  {
    var form = document.formPost;
    form.page.value = page;
    form.submit();
  }
  function turnToPage() 
	{
	  	var pv =  parseInt(document.getElementById("paegeValue").value);
		var maxpv = parseInt(document.getElementById("maxPv").value);
		if(pv <= 0)
			{
			 pv = 1;
			}
		if (pv > maxpv)
			{
			pv = maxpv;
			}
		topage(pv);
	}
</script>
<style type="text/css">
.page_num{ width: 100%; height:26px; margin:1px auto; overflow:hidden; font-size:12px; line-height:26px;}
.page_num span{ margin:0px 10px;}
.page_num em{ margin:0 5px; font-style:normal;}
.page_num .btn_left{ float:left; width:*; margin-left:6px; display:inline;}
.page_num .btn_left a{ margin-right:5px;}
.page_num .btn_right{ float:right; width:300px; margin-right:6px; display:inline;}
.page_num .btn_right span{ float:right;}
.text_1{text-align:center; width:28px;height: 22px;vertical-align: middle;}
/*��ť����*/
a.a_btn{ display:inline-block; *display:inline;*zoom:1; vertical-align:middle; padding-left:10px; cursor:pointer; height:22px; line-height:22px; background:url(/COMMON/hitzd/img/fenye_btn.png) no-repeat 0 0; margin:0px;}
a.a_btn em{ padding-right:10px; display:inline-block; *display:inline;*zoom:1; vertical-align:middle;cursor:pointer; height:22px; line-height:22px;background:url(/COMMON/hitzd/img/fenye_btn.png) no-repeat 100% -22px; margin:0px; }
/*a.a_btn:hover{ background-position:0 -44px !important; }
a.a_btn:hover em{background-position:100% -66px !important;} */
</style>
<input id='maxPv' type="hidden" value='<%=pageView.getTotalpage()%>'/>
<div class="page_num">
	<div class="btn_left">
		<span>
			��<em><font color="#178497"><b><%=pageView.getCurrentpage()%></b></font></em>ҳ/��<em><font color="#178497"><%=pageView.getTotalpage()%></font></em>ҳ
		</span>
		<!--��ҳ ��һҳ-->
		<%
		if(pageView.getCurrentpage()!=1) {
		%>
		  <a href="javascript:topage('1')" style="margin-right:0px;">
		    <font color="#178497">
		     	 ��ҳ
		    </font>
		  </a>
	  	|
		  <a href="javascript:topage('<%=pageView.getCurrentpage() - 1 %>')">
		    <font color="#178497">
		  		 ��һҳ
		    </font>
		  </a>
	<%}%>
		<!--�м�ҳ����ʾ-->
	<%
	for(long i = pageView.getPageindex().getStartindex() ;i <= pageView.getPageindex().getEndindex();i++) {
	  if(i==pageView.getCurrentpage()) {
	%>
	    <font color="#178497">
	      <b>
	        <%=i%>
	      </b>
	    </font>
	 <%
	  }
	  if(i!=pageView.getCurrentpage()) {
	 %>
	    <a href="javascript:topage('<%=i%>')">
	      <font color="#178497">
	        <%=i %>
	      </font>
	    </a>
	<%
	  }
	}
	%>
	<!--��һҳ βҳ-->
	<%
	if(pageView.getCurrentpage()<pageView.getTotalpage())
	{
	%>
	  <a href="javascript:topage('<%=pageView.getCurrentpage() + 1 %>')" style="margin-right:0px;">
	    <font color="#178497">
	      	��һҳ
	    </font>
	  </a>
	  |
	  <a href="javascript:topage('<%=pageView.getTotalpage()%>')">
	    <font color="#178497">
	     	 βҳ
	    </font>
	  </a>
	<%}%>
		<font color="#555555">
			ת����
		</font>
		<input id='paegeValue' type="text" value='' class="text_1"/>
		<a class="a_btn" onclick="turnToPage()"  onmouseover="this.style.cssText='background-position:0 -44px;'" onmouseout="this.style.cssText='background-position:0 0px;'">
			<em onmouseover="this.style.cssText='background-position:100% -66px;'" onmouseout="this.style.cssText='background-position:100% -22px;'">
				<font color="#555555">��ת</font>
			</em>
		</a>
	</div>
	<div class="btn_right">
		<span>�ܼ�¼����
			<em><font color="#178497"><%=pageView.getTotalrecord()%></font></em>��
		</span>
		<span>ÿҳ��ʾ
			<em><font color="#178497"><%=pageView.getMaxresult()%></font></em>��
		</span>
	</div>
</div>

