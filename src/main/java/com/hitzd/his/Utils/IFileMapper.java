package com.hitzd.his.Utils;

import com.hitzd.DBUtils.JDBCQueryImpl;
import com.hitzd.DBUtils.TCommonRecord;

/**
 * ��������Ѿ������ļ� 
 * @author Administrator
 *
 */
public interface IFileMapper
{
	public boolean InsertDataFile(TCommonRecord param , JDBCQueryImpl query);
	
	public TCommonRecord SelectById(TCommonRecord parm , JDBCQueryImpl query);
	 
}
