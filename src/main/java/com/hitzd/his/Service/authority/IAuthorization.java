package com.hitzd.his.Service.authority;

import com.hitzd.his.Beans.frame.User;

/**
 * ��½��֤ 
 * @author liujc
 *
 */
public interface IAuthorization
{
    /**
     *  ƽ̨����½���
     * @param UserName
     * @param Password
     * @param ProgramID
     * @param is_pf
     * @return
     */
    public User VerifyLogin(String UserName, String Password, String ProgramID,boolean is_pf);
    /**
     * ƽ̨������½���
     * @param UserName
     * @param Password
     * @param ProgramID
     * @return
     */
    public User VerifyLogin(String UserName, String Password, String ProgramID);
    /**
     * ����״̬code 
     * @return
     */
    public int getErrorCode();
}
