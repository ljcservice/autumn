package com.hitzd.his.Utils;

import java.util.List;
import java.util.UUID;

import com.hitzd.DBUtils.CommonMapper;
import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;
import com.hitzd.Factory.DBQueryFactory;

public class MsgUtils 
{
	// ��Ϣ����
	public static String TextMsg  = "Text";
	public static String ImageMsg = "Image";

	// ��Ϣ����
	public static String CommonMsg = "CommonMsg";
	public static String SystemMsg = "SystemMsg";

	/**
	 * ������Ϣ��ָ����ҽ��
	 * @param FromDeptName �����ߵĲ�������
	 * @param FromDeptCode �����ߵĲ��Ŵ���
	 * @param FromUserName �����ߵ��û�����
	 * @param FromUserCode �����ߵ��û�����
	 * @param ToDeptName   �����ߵĲ�������
	 * @param ToDeptCode   �����ߵĲ��Ŵ���
	 * @param ToUserName   �����ߵ��û�����
	 * @param ToUserCode   �����ߵ��û�����
	 * @param Content      ��Ϣ����
	 * @param MsgType      ��Ϣ����
	 * @param LinkUrl      ������url��ַ
	 * @param Resverd      ������������ʱΪ���ַ���
	 * @return ���ش�����Ϣ��û�д��󷵻ؿ��ַ���
	 */
	public static String sendMsg(String FromDeptName, String FromDeptCode, String FromUserName, String FromUserCode,
			String ToDeptName, String ToDeptCode, String ToUserName, String ToUserCode,
			String Content, String MsgType, String LinkUrl, String Resverd)
	{
		return sendMsg(FromDeptName, FromDeptCode, FromUserName, FromUserCode,
			ToDeptName, ToDeptCode, ToUserName, ToUserCode,
			Content, MsgType, CommonMsg, LinkUrl, Resverd);
	}
	
	/**
	 * 
	 * @param FromDeptName
	 * @param FromDeptCode
	 * @param FromUserName
	 * @param FromUserCode
	 * @param ToDeptName
	 * @param ToDeptCode
	 * @param ToUserName
	 * @param ToUserCode
	 * @param Content
	 * @param MsgType
	 * @param MsgProp
	 * @param LinkUrl
	 * @param Resverd
	 * @return
	 */
	public static String sendMsg(String FromDeptName, String FromDeptCode, String FromUserName, String FromUserCode,
			String ToDeptName, String ToDeptCode, String ToUserName, String ToUserCode,
			String Content, String MsgType, String MsgProp, String LinkUrl, String Resverd)
	{
		/*
		TableName: HisMessages
		MsgID        int
		FromDeptName varchar2(20)
		FromDeptCode varchar2(20)
		FromUserName varchar2(20)
		FromUserCode varchar2(20)
		ToDeptName   varchar2(20)
		ToDeptCode   varchar2(20)
		ToUserName   varchar2(20)
		ToUserCode   varchar2(20)
		Content      varchar2(2000)
		MsgType      varchar2(20)
		MsgProp      varchar2(20)
		LinkUrl      varchar2(300)
		SendTime     Date

		RecvTime     Date
		*/
		String ID = UUID.randomUUID().toString();
		JDBCQueryImpl query = DBQueryFactory.getQuery("PEAAS");
		String sql = "insert into HisMessages (MsgID, FromDeptName, FromDeptCode, FromUserName, FromUserCode, " +
			"ToDeptName, ToDeptCode, ToUserName, ToUserCode, Content, MsgType, MsgProp, LinkUrl, SendTime) values (" +
			"'" + ID           + "', " +
			"'" + FromDeptName + "', " +
			"'" + FromDeptCode + "', " +
			"'" + FromUserName + "', " +
			"'" + FromUserCode + "', " +
			"'" + ToDeptName   + "', " +
			"'" + ToDeptCode   + "', " +
			"'" + ToUserName   + "', " +
			"'" + ToUserCode   + "', " +
			"'" + Content.replaceAll("'", "\"")      + "', " +
			"'" + MsgType      + "', " +
			"'" + MsgProp      + "', " +
			"'" + LinkUrl.replaceAll("'", "\"")      + "', " +
			"sysdate" +
			")";
		try
		{
			query.execute(sql);
			query = null;
		}
		catch (Exception ex)
		{
			query = null;
			ex.printStackTrace();
			return ex.getMessage();
		}
		return "";
	}
	
	public List<TCommonRecord> recvMsg(String DeptName, String DeptCode, String UserName, String UserCode)
	{
		JDBCQueryImpl query = DBQueryFactory.getQuery("PEAAS");
		String sql = " select * from HisMessages where 1=1 ";
		String where = "";
		if (DeptName.length() > 0)
			where += " and ToDeptName = '" + DeptName + "' ";
		if (DeptCode.length() > 0)
			where += " and ToDeptCode = '" + DeptCode + "' ";
		if (UserName.length() > 0)
			where += " and ToUserName = '" + UserName + "' ";
		if (UserCode.length() > 0)
			where += " and ToUserCode = '" + UserCode + "' ";
		
		sql += where;
		@SuppressWarnings("unchecked")
		List<TCommonRecord> list = query.query(sql, new CommonMapper());
		query = null;
		return list;
	}
}