<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk" %>
<%@page import="com.hitzd.WebPage.PageView" %>
<%@page import="com.hitzd.his.Web.Utils.CommonUtils" %>
<%@page import="com.hitzd.DBUtils.*" %>
<%@page import="java.util.*" %>
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
    //�����ع�
    String iPage = getRequestAttribute(request, "page", "");
    String drugCode = getRequestAttribute(request, "drugCode", "");
    String drugName = getRequestAttribute(request, "drugName", "");
    String whereValue = getRequestAttribute(request, "whereValue", "");
    String whereField = getRequestAttribute(request, "whereField", "");
    String q_is_anti = getRequestAttribute(request, "q_is_anti", "");
    String q_is_psychotic = getRequestAttribute(request, "q_is_psychotic", "");
    String q_is_danger = getRequestAttribute(request, "q_is_danger", "");
    String matched = getRequestAttribute(request, "matched", "");

    //�Զ����������
    String updCnt = (String) request.getAttribute("updCnt");
    String noUpdCnt = (String) request.getAttribute("noUpdCnt");

%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}">
    <title>ҩƷ����</title>
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>  
<link type="text/css" rel="stylesheet" href="plugins/zTree/v3/zTreeStyle.css"/>
	<script type="text/javascript">
	$(top.hangge());
</script>
<%@include file="../reportView/Common.jsp" %>
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
	        with (formPost) {
	            o.value = "transferAll";
	            submit();
	        }
	    }
	    function transferSingle(drugCode, drugSpec) {
	        with (formPost) {
	            o.value = "transferSingle";
	            drug_code.value = drugCode;
	            drug_spec.value = drugSpec;
	            submit();
	        }
	    }
	    function showBG() {
	        document.body.style.margin = "0";
	        bgObj = document.createElement("div");
	        bgObj.setAttribute('id', 'bgDiv');
	        bgObj.style.position = "absolute";
	        bgObj.style.top = "0";
	        bgObj.style.background = "#777";
	        bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=50)";
	        bgObj.style.opacity = "0.4";
	        bgObj.style.left = "0";
	        bgObj.style.width = "100%";
	        bgObj.style.height = "100%";
	        bgObj.style.zIndex = "1000";
	        document.body.appendChild(bgObj);
	    }
	    function MatchIt(drug_map_id) {
	        var url = "<%=path%>/control/DrugMatcher?drug_map_id=" + drug_map_id + "&o=matcherit";
	        var obj = new Object();
	        var returnVal = window.showModalDialog(url, obj, "dialogHeight=750px;dialogWidth=950px;resizable=0;scroll=no");
	        document.formPost.submit();
	//        alert(returnVal);
	//        if (returnVal != "" && returnVal != "undefined" && returnVal != undefined)
	//        {
	//            with (document.formPost)
	//            {
	//                submit();
	//            }
	//        }
	    }
	    function MatchItAuto() {
	        with (document.formPost) {
	            if (params.value == "") {
	                if (confirm("���˲���Ϊ�գ�Ĭ�Ͻ����˲�������Ϊ����,��,��,��,��,��,��,��,��,#,��,��,��,��,��,ע����,ע���,ע��Һ����ȷ���Դ˲�����ʼ�Զ�������")) {
	                    showBG();
	                    params.value = "��,��,��,��,��,��,��,��,��,#,��,��,��,��,��,ע����,ע���,ע��Һ";
	                    submit();
	                }
	            }
	            else {
	                showBG();
	                submit();
	            }
	        }
	    }
	    function DeleteIt(drug_map_id) {
	        if (confirm("ϵͳ��ʾ��ȷ��ɾ����")) {
	            with(document.formPost){
	                dmi.value = drug_map_id;
	                o.value="deleteIt";
	                submit();
	            }
	        }
	    }
	    var prevColor;
	    function MouseOver(obj) {
	        prevColor = obj.style.backgroundColor;
	        obj.style.backgroundColor = "yellow";
	    }
	    function MouseOut(obj) {
	        obj.style.backgroundColor = prevColor;
	    }
	    function Print() {
	        with (document.formPost) {
	            o.value = "excelPrint";
	            submit();
	        }
	    }
	</script>
	<script type="text/javascript"  src="js/public/Newjs.js" ></script>
</head>

<body  background="<%=path%>/skin/images/allbg.gif" style="background-color: white;margin: 0 0 0 0;overflow: hidden;">
<div class="search-bar-container">
<form name="formPost" style="margin: 0 0 0 0" action="<%=path%>/control/DrugMatcher" method="post" target="_self">
        <input name="o" type="hidden" value="query"/>
        <input type="hidden" name="page" value="<%=pageView.getCurrentpage()%>"/>
        <input type="Hidden" name="dmi">

        <div class="form-inline search-bar-top search-bar-container">
	            <label>ҩƷ����: <input name="drugCode" value="<%=drugCode%>" size="10"></label>
	            <label>ҩƷ���ƣ�<input name="drugName" value="<%=drugName %>" size="10"/></label>
	           	<label> ��������:
	            <select name="whereField">
	                <option value=""></option>
	                <option value="is_basedrug" <%="is_basedrug".equals(whereField) ? "selected='selected'" : ""%>>���һ���ҩ��
	                </option>
	                <option value="is_exhilarant" <%="is_exhilarant".equals(whereField) ? "selected='selected'" : ""%>>�˷ܼ�
	                </option>
	                <option value="is_injection" <%="is_injection".equals(whereField) ? "selected='selected'" : ""%>>ע���
	                </option>
	                <option value="is_oral" <%="is_oral".equals(whereField) ? "selected='selected'" : ""%>>�ڷ��Ƽ�</option>
	                <option value="is_impregnant" <%="is_impregnant".equals(whereField) ? "selected='selected'" : ""%>>�ܼ�
	                </option>
	                <option value="is_external" <%="is_external".equals(whereField) ? "selected='selected'" : ""%>>����
	                </option>
	                <option value="is_chinesedrug" <%="is_chinesedrug".equals(whereField) ? "selected='selected'" : ""%>>
	                    		��ҩ
	                </option>
	                <option value="is_allergy" <%="is_allergy".equals(whereField) ? "selected='selected'" : ""%>>������
	                </option>
	                <option value="is_patentdrug" <%="is_patentdrug".equals(whereField) ? "selected='selected'" : ""%>>�г�ҩ
	                </option>
	                <option value="is_tumor" <%="is_tumor".equals(whereField) ? "selected='selected'" : ""%>>������</option>
	                <option value="is_poison" <%="is_poison".equals(whereField) ? "selected='selected'" : ""%>>��ҩ</option>
	                <option value="is_psychotic" <%="is_psychotic".equals(whereField) ? "selected='selected'" : ""%>>����ҩ
	                </option>
	                <option value="is_habitforming" <%="is_habitforming".equals(whereField) ? "selected='selected'" : ""%>>
	                    ��ҩ
	                </option>
	                <option value="is_radiation" <%="is_radiation".equals(whereField) ? "selected='selected'" : ""%>>����
	                </option>
	                <option value="is_precious" <%="is_precious".equals(whereField) ? "selected='selected'" : ""%>>����ҩ
	                </option>
	                <option value="is_danger" <%="is_danger".equals(whereField) ? "selected='selected'" : ""%>>��Σҩ</option>
	                <option value="is_otc" <%="is_otc".equals(whereField) ? "selected='selected'" : ""%>>OTC</option>
	                <option value="is_hormone" <%="is_hormone".equals(whereField) ? "selected='selected'" : ""%>>������
	                </option>
	                <option value="is_cardiovascular" <%="is_cardiovascular".equals(whereField) ? "selected='selected'" : ""%>>
	                    ����Ѫ����
	                </option>
	                <option value="is_digestive" <%="is_digestive".equals(whereField) ? "selected='selected'" : ""%>>����ϵͳ��
	                </option>
	                <option value="is_biological" <%="is_biological".equals(whereField) ? "selected='selected'" : ""%>>
	                    ������Ʒ��
	                </option>
	            </select>=
	            <select name="whereValue">
	                <option></option>
	                <option value="1" <%="1".equals(whereValue) ? "selected='selected'" : ""%>>��</option>
	                <option value="0" <%="0".equals(whereValue) ? "selected='selected'" : ""%>>��</option>
	            </select>
	            </label>
	          	<label>����ҩ:
	            <select name="q_is_anti">
	                <option></option>
	                <option value="0" <%="0".equals(q_is_anti) ? "selected='selected'" : ""%>>���п���ҩ</option>
	                <option value="1" <%="1".equals(q_is_anti) ? "selected='selected'" : ""%>>�����Ƽ�����ҩ</option>
	                <option value="2" <%="2".equals(q_is_anti) ? "selected='selected'" : ""%>>���Ƽ�����ҩ</option>
	                <option value="3" <%="3".equals(q_is_anti) ? "selected='selected'" : ""%>>���⼶����ҩ</option>
	                <option value="4" <%="4".equals(q_is_anti) ? "selected='selected'" : ""%>>�ֲ�����ҩ</option>
	                <option value="5" <%="5".equals(q_is_anti) ? "selected='selected'" : ""%>>ȫ����ҩ</option>
	            </select>
	            </label>
	            <label>������ҩ:
	            <select name="q_is_psychotic">
	                <option></option>
	                <option value="0" <%="0".equals(q_is_psychotic) ? "selected='selected'" : ""%>>���о�����ҩ��</option>
	                <option value="1" <%="1".equals(q_is_psychotic) ? "selected='selected'" : ""%>>I�ྫ����ҩ</option>
	                <option value="2" <%="2".equals(q_is_psychotic) ? "selected='selected'" : ""%>>II�ྫ����ҩ</option>
	            </select>
	            </label>
	            <label>��Σҩ:
	            <select name="q_is_danger">
	                <option></option>
	                <option value="0" <%="0".equals(q_is_danger) ? "selected='selected'" : ""%>>���и�Σҩ</option>
	                <option value="1" <%="1".equals(q_is_danger) ? "selected='selected'" : ""%>>A��</option>
	                <option value="2" <%="2".equals(q_is_danger) ? "selected='selected'" : ""%>>B��</option>
	                <option value="3" <%="3".equals(q_is_danger) ? "selected='selected'" : ""%>>C��</option>
	            </select>
	            </label>
	
	         	<label>���״̬:
	            <select name="matched">
	                <option value="">ȫ��</option>
	                <option value="is not null" <%="is not null".equals(matched) ? "selected='selected'" : ""%>>�����</option>
	                <option value="is null" <%="is null".equals(matched) ? "selected='selected'" : ""%>>δ���</option>
	            </select>
	            </label>
	            <label><a href="<%=request.getContextPath() %>/js/MatchHelp.doc" target="_blank">ʹ��˵��</a></label>
	            <!--<br/>
			            �Զ������������(Ӣ�Ķ��ŷָ�)��<input type="input" name="params" value="" style="width:150px"/>
			            ���뷶Χ: <input type="radio" name="scale" value="1" >ȫ��ҩƷ&nbsp;&nbsp;<input type="radio" name="scale" value="2" checked>δ���ҩƷ
			            &nbsp;&nbsp;
	            <input type="button" value="  �Զ�����  " onclick="MatchItAuto()" />
	            -->
	     </div>
	    <table width="100%">
	        <tr height="20">
	            <td background="<%=request.getContextPath() %>/images/tbg.gif" align="right">
	                <img alt="ͳ�Ƽ���"  src="<%=request.getContextPath() %>/images/query.jpg" onclick="javascript:thisSubmit()" style="cursor: pointer;">
	                <img alt="����excel" src="<%=request.getContextPath() %>/images/excel.gif" onclick="javascript:Print()" style="cursor: pointer;">
	            </td>
	        </tr>
	    </table>
	 </form>
 </div>

 <div style="font-family:����;font-size:20pt;font-color:black;line-height:40px;text-align:center;">ҩƷ����</div>
   <table class="table table-bordered header-fixed table-striped table-hover">   
      <thead>
         <tr>
             <th colspan="2">��ԺҩƷ��Ϣ</th>
             <th colspan="6">������Ϣ</th>
             <th rowspan="2" id="last_th">����</th>
         </tr>
         <tr>
             <th>ҩƷ��</th>
             <th>ҩƷ����</th>
             <th>ҩƷ����</th>
             <th>���</th>
             <th>��λ</th>
             <th>����</th>
             <th>��С��λ����</th>
             <th>������λ</th>
         </tr>
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
              <td><%=cr.get("Drug_No_Local")%>
              </td>
              <td><%=cr.get("Drug_Name_Local")%>
              </td>
              <td><%=cr.get("drug_name")%>
              </td>
              <td><%=cr.get("drug_spec_pdss")%>
              </td>
              <td><%=cr.get("units_pdss")%>
              </td>
              <td><%=cr.get("drug_form_pdss")%>
              </td>
              <td><%=cr.get("dose_per_unit_pdss")%>
              </td>
              <td><%=cr.get("dose_units_pdss")%>
              </td>
              <td>
                  <a href="javascript:DeleteIt('<%=cr.get("drug_map_id")%>')">ɾ��</a>
                  <a href="javascript:MatchIt('<%=cr.get("drug_map_id")%>')">���</a>
              </td>
          </tr>
          <%
             }
             if(pageView.getTotalpage() > 1) {
          %>
     </tbody>
     <tfoot>
         <%
             if (null != updCnt && !"".equals(updCnt)) {
         %>
         <tr height="20">
             <td colspan="9">
             	<font color="red">�Զ�������ɣ������������<%=updCnt %>�����ݣ�ʣ��δ�������<%=noUpdCnt %> ����</font>
             </td>
         </tr>
         <%
             }
         %>
         <tr height="20">
             <td colspan="9">
                 <%@ include file="../share/fenye.jsp" %>
             </td>
         </tr>
   	</tfoot>
   	<%}
             }%>
  </table>
</body>
</html>