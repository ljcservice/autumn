package com.hitzd.his.Service.hibernateService;

import java.util.List;

import com.hitzd.DBUtils.TCommonRecord;
/**
 * 
 * @author tyl
 *
 */
public interface IHiberBiz {

	/**
	 * ��ѯ
	 * @param sql
	 * @return
	 */
	public List getList(String sql);
	/**
	 * ����
	 * @param sql
	 */
	public void update(String sql) ;
	/**
	 * ɾ��
	 * @param sql
	 */
	public void delete(String sql);
	/**
	 * ����
	 * @param sql
	 */
	public void insert(String sql);
}
