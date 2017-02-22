package com.hitzd.his.RowMapperBeans.Middle;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hitzd.his.Beans.Middle.TDBUrlConfig;

/**
 * his �����ֶα� 
 * @author jingcong
 *
 */
public class DBUrlConfigMapper implements RowMapper
{
    @Override
    public Object mapRow(ResultSet rs, int arg1) throws SQLException
    {
        TDBUrlConfig entity = new TDBUrlConfig();
        entity.setId(rs.getString("id"));
        entity.setDb_url(rs.getString("Db_url"));
        /* ���ݿ����� */
        entity.setDb_base(rs.getString("db_base"));
        /* ��ע */
        entity.setRemark(rs.getString("remark"));
        /* ���ݿ��û����� */
        entity.setDb_user(rs.getString("db_user"));
        /* ���ݿ����� */
        entity.setDb_pwd(rs.getString("db_pwd")); 
        /* ���ݿ����ӵ�ַ */
        entity.setConn_url(rs.getString("Conn_url"));
        /* �����ļ��Ƿ��������  */
        entity.setFlag(rs.getString("flag"));    
        return entity;
    }

}
