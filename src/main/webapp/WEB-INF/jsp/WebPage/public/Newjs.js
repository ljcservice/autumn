/*ʱ��ؼ��Ĺ���*/
function CurDate(){
	var myDate = new Date();
	var str;
	var mon=myDate.getMonth()+1,date=myDate.getDate();
	
	if(mon<10){
		mon="0"+mon;
	}
	if(date<10){
		date="0"+date;
	}
	str=myDate.getFullYear()+"-"+mon+"-"+date;
	
	return str;

}

function endAutoFill(){
	var str="";
	var beginDate=$("[name='beginDate']").val();
	switch(beginDate.substr(5,2)){
		case "01":
			str+=beginDate.substr(0,8)+"31"
			break;
		case "02":
			if(0==beginDate.substr(0,4)%4){
				str+=beginDate.substr(0,8)+"28";
			}else{
				str+=beginDate.substr(0,8)+"28";
			}
			break;
		case "03":
			str+=beginDate.substr(0,8)+"31";
			break;
		case "04":
			str+=beginDate.substr(0,8)+"30";
			break;
		case "05":
			str+=beginDate.substr(0,8)+"31";
			break;
		case "06":
			str+=beginDate.substr(0,8)+"30";
			break;
		case "07":
			str+=beginDate.substr(0,8)+"31";
			break;
		case "08":
			str+=beginDate.substr(0,8)+"31";
			break;
		case "09":
			str+=beginDate.substr(0,8)+"30";
			break;
		case "10":
			str+=beginDate.substr(0,8)+"31";
			break;
		case "11":
			str+=beginDate.substr(0,8)+"30";
			break;
		case "12":
			str+=beginDate.substr(0,8)+"31";
			break;
		default:	
		 	break;
	}
	if(str>CurDate()){
		str=CurDate();
	}
	return str;
}

function DateRight(){
	var Cur="";
	Cur=CurDate();
	
	var beginDate=$("[name='beginDate']").val();
	var endDate=$("[name='endDate']").val();
	
	if(endDate>Cur){
		alert("�������ڲ��ܴ���"+Cur+",������ѡ�����ڣ�");
		$("[name='endDate']").val("");
	}else{
		if(beginDate>endDate)
		{
			alert("�������ڲ���С�ڿ�ʼ����,������ѡ�����ڣ�");
			$("[name='endDate']").val("");
		}
	}
	/*if(beginDate==""){
		$("[name='beginDate']").val(beginAutoFill());
	}*/
}
function DateLeft(){
	
	var Cur="";
	Cur=CurDate();
	var beginDate=$("[name='beginDate']").val();
	var endDate=$("[name='endDate']").val();
	if(beginDate>Cur){
		alert("��ʼ���ڲ��ܴ���"+Cur+",������ѡ�����ڣ�");
		$("[name='beginDate']").val("");
	}else{
		/*if(beginDate>endDate && endDate !="")
		{
			//alert("��ʼ���ڲ��ܴ��ڽ�������,������ѡ�����ڣ�");
			$("[name='beginDate']").val("");
		}else{*/
		if(beginDate>endDate)
			$("[name='endDate']").val(endAutoFill());
		//}
	}
}

$().ready(function(){
	 //���µ������ĸ߶�
	function resizeTableHeight(){
		//���ı������߶�=���ڵĸ߶�-����Ϸ���������ĸ߶ȣ�������Ϊ90��
		var scrollBodyHeight = SCROLL_TABLE_HIEGHT;
		//���ʵ�ʸ߶�
		var scrollBodyTableHeight = $(".dataTables_scrollBody table").height();
		//���ñ��Ĺ����߶�
		$(".dataTables_scrollBody").css("height",scrollBodyTableHeight>scrollBodyHeight?scrollBodyHeight:scrollBodyTableHeight);
	}

	//������Ĵ�С�����仯��ʱ����Ҫ���µ������ĸ߶�
	$(window).resize(function(){
		resizeTableHeight();
	});
	
	$(".header-fixed").ready(function(){
		var scrollBodyHeight = SCROLL_TABLE_HIEGHT;
		//���ʵ�ʸ߶�
		var scrollBodyTableHeight=$(".dataTables_scrollBody table").height();
		if(scrollBodyTableHeight>scrollBodyHeight) {
			//���ӱ�ͷ���һ�еĿ������Ӧ�ұ߹�����������Ŀ��
			$(".dataTables_scrollHeadInner").addClass("all_width");
			$(".dataTables_scrollHeadInner .header-fixed").addClass("all_width");
			$("#last_th").addClass("add_border");
		}else{
			$(".dataTables_scrollHeadInner").addClass("all_width");
			$(".dataTables_scrollHeadInner .header-fixed").addClass("all_width");
		}
		//�����������ʱ�����������еĵ�һ������first��class
		$(".dataTables_scrollBody .header-fixed tbody tr:first").addClass("first");
		//�����������ʱ����Ҫ���µ������ĸ߶�
		resizeTableHeight();
	}).dataTable( {
		"sScrollY": document.documentElement.clientHeight, //���������ĳ������õĸ߶ȣ������������������
		"bPaginate": false, //�Ƿ���ʾ���ķ�ҳ�����
		"bFilter": false, //�Ƿ���ʾ���Ĳ�ѯ�����
		"bScrollCollapse": false, //
		"bInfo":false, //�Ƿ���ʾ����¼����Ϣ�����繲��������¼�ȡ�
		"bAutoWidth": true, //�Ƿ��Զ����ñ���ȡ�
		"bSort": false, //�Ƿ����Զ�����
		oLanguage: {
			sEmptyTable: "��������"
		}
	});
});