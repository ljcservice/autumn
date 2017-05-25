/*
 * AElements document.formPost.elements
 * У����� 
 * Required��true  �����־ ,RequiredValue ������ʾ��Ϣ 
 */
function checkValue(AElements)
{
	for(var i = 0 ; i < AElements.length;i++)
	{
		var myObj = AElements[i];
		if (myObj)
		{
			if (myObj.tagName == "INPUT")
			{
				if (myObj.type == "radio" && myObj.Required == "true")
				{
					var isNull = true;
					var obj = document.getElementsByName(myObj.name);
					for (var j = 0; j < obj.length; j++)
					{
						if (obj[j].checked)
						{
							isNull = false;
							break;
						}
					}
					if (isNull)
					{
						alert(myObj.RequiredValue);
						myObj.focus();
						return true;
					}
					
				}
				else
				{
					if(myObj.Required=="true" && myObj.value == '')
					{
						alert(myObj.RequiredValue);
						myObj.focus();
						return true;
					}
				}
			}
			else
			{
				if(myObj.Required=="true" && myObj.value == '')
				{
					alert(myObj.RequiredValue);
					myObj.focus();
					return true;
				}
			}
		}
	}
	return false;
}
/*
 * ҳ���ύ
 */
function ThisSubmit(myForm)
{
	var f = myForm || document.formPost;
	if(checkValue(f.elements))
	{
		return ;
	}
	f.o.value = "query";
	showBG();
	f.submit();
}
var bgObj = null;

function showBG()
{
	document.body.style.margin = "0";
	bgObj   = document.createElement("div");
	bgObj.setAttribute('id', 'bgDiv');
	bgObj.style.position   = "absolute";
	bgObj.style.top        = "0";
	bgObj.style.background = "#777";
	bgObj.style.filter     = "progid:DXImageTransform.Microsoft.Alpha(opacity=50)";
	bgObj.style.opacity    = "0.4";
	bgObj.style.left       = "0";
	bgObj.style.width      = "100%";
	bgObj.style.height     = "100%";
	bgObj.style.zIndex     = "1000";
	document.body.appendChild(bgObj);
}
function $(_ObjID)
{
	return document.getElementById(_ObjID);
}

function setOnFocus()
{
	var inps = document.getElementsByTagName("input");
	for(var i = 0 ; i < inps.length ; i++)
	{
		var myObj = inps[i];
		if(myObj.type == "text")
		{
			if(inps[i].className == "Wdate") continue; 
			inps[i].onblur  = function ()
			{
				event.srcElement.className = "";
			};
			inps[i].onfocus = function ()
			{
				event.srcElement.className = "focus";
				event.srcElement.select();
			};
		}
	}
}

var lastRow = null;

function onRow(idx)
{
	document.getElementById(idx).style.cssText = "background-color:#E7F3A5";
	if (lastRow != null)
	{
		if (lastRow != idx)
		{
			document.getElementById(lastRow).style.cssText = "background-color:#FFFFFF";
		}
	}
	lastRow = idx;
}

/**
 * ֻ���������֣��ɰ��˸��ɾ������
 * */
function vaildIntegerNumber(evnt)
{
	evnt = evnt || window.event;
	var keyCode = window.event ? evnt.keyCode : evnt.which;
	return keyCode >= 48 && keyCode <= 57 || keyCode == 8;
}

/**
 * ֻ���������ֺ͵�,�ɰ��˸��ɾ�����ֻ��
 * */
function vaildFloatNumber(evnt)
{
	evnt = evnt || window.event;
	var keyCode = window.event ? evnt.keyCode : evnt.which;
	return keyCode >= 48 && keyCode <= 57 || keyCode == 46 || keyCode == 8;
}

/**
 * ֻ���������ֺ�һ����,�ɰ��˸��ɾ�����ֻ��
 * */
function vaildFloatNumberLimitDecimalPoint(evnt, obj)
{
	evnt = evnt || window.event;
	var keyCode = window.event ? evnt.keyCode : evnt.which;
	if(obj.value.indexOf(".") != -1 && keyCode == 46)
		return false;
	return keyCode >= 48 && keyCode <= 57 || keyCode == 46 || keyCode == 8;
}


/**
 * ֻ���������ֺ�һ���㣬������ĵ�һ���ַ�����Ϊ�㣬�ɰ��˸��ɾ�����ֻ��
 * */
function vaildFloatNumberPerfect(evnt, obj)
{
	evnt = evnt || window.event;
	var keyCode = window.event ? evnt.keyCode : evnt.which;
	if((obj.value.length == 0 || obj.value.indexOf(".") != -1) && keyCode == 46)
		return false;
	return keyCode >= 48 && keyCode <= 57 || keyCode == 46 || keyCode == 8;
}