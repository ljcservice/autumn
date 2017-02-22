package com.hitzd.his.Beans.frame;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hitzd.DBUtils.TCommonRecord;

/**
 * �û���Ϣ�ֶ�
 * @author jingcong
 *
 */
public class User
{
    /* session������ */
    public final static String UserSessionCode = "Customer";
    /* �û�id */
    private String             UserID          = "";
    /* �û�����*/
    private String             UserName        = "";
    /* ����Ȩ��  */
    private String             UserPower       = "";
    /* ģ����幦�ܷ���Ȩ��  */
    private HashMap<String, List<String>>  userPowerFunc = new HashMap<String, List<String>>();
    /* �û����� */
    private String             UserDesc        = "";
    /* �û���λ */
    private String             UserUnit        = "";
    /* */
    private String             UserDuty        = "";
    /* �û���������ƽ̨  */
    private String             UserProgram     = "";
    /* �û���ɫ Ŀǰ 000 ����ҩʦ  �� 001 ��ҩʦ */
    private String             UserRole        = "";
    /* �û�token ��ע��һ���û���¼ƽ̨���ʹ��һ�� Token����  */
    private String             UserToken       = "";
    /* �û��ܿ������� */
    private TCommonRecord UserDept = new TCommonRecord();
    
    public String getUserRole()
    {
        return UserRole;
    }

    public void setUserRole(String userRole)
    {
        UserRole = userRole;
    }

    public String getUserProgram()
    {
        return UserProgram;
    }

    public void setUserProgram(String userProgram)
    {
        UserProgram = userProgram;
    }

    public String getUserUnit()
    {
        return UserUnit;
    }

    public void setUserUnit(String userUnit)
    {
        UserUnit = userUnit;
    }

    public String getUserDuty()
    {
        return UserDuty;
    }

    public void setUserDuty(String userDuty)
    {
        UserDuty = userDuty;
    }

    public TCommonRecord getUserDept()
    {
        return UserDept;
    }

    public void setUserDept(TCommonRecord userDept)
    {
        UserDept = userDept;
    }

    public String getUserID()
    {
        return UserID;
    }

    public void setUserID(String userID)
    {
        UserID = userID;
    }

    public String getUserName()
    {
        return UserName;
    }

    public void setUserName(String userName)
    {
        UserName = userName;
    }

    public void setUserPower(List<UserRolePower> userPowers)
    {
        UserPower = "";
        if(userPowers != null && userPowers.size() > 0)
        {
            StringBuffer sb = new StringBuffer();
            for(UserRolePower e : userPowers)
            {
                /* �û���ģ�����Ȩ��     ģ������[���ܵ�1,���ܵ�2,���ܵ�3] */
                String roleFunc = e.getProgfunc(); //tcr.get("progfunc");
                if(roleFunc != null && !"".equals(roleFunc))
                {
                    /* ������  */
                    Pattern pt = Pattern.compile("(\\[)(.*?)(\\])");
                    Matcher mr = pt.matcher(roleFunc); 
                    while(mr.find())
                    {
                        String key   = roleFunc.replace(mr.group(0), "");
                        String value = mr.group(2);
                        /* ��ģ�鹦��Ȩ�� */
                        addUserPowerFunc(e.getProgid() + key, "".equals(value) ? null : Arrays.asList(value.split(",")));
                    }
                }
                //��֯ģ�����Ȩ�� 
                sb.append("{").append(e.getProgid()).append("}");
            }
            UserPower = sb.toString();
        }
    }

    private void addUserPowerFunc(String key , List<String> value)
    {
        this.userPowerFunc.put(key, value);
    }
    
    public String getUserDesc()
    {
        return UserDesc;
    }

    public void setUserDesc(String userDesc)
    {
        UserDesc = userDesc;
    }

    public String getUserPower()
    {
        return UserPower;
    }
    
    /**
     * ���ظ��û��Ƿ���ָ��ģ��ķ���Ȩ��
     * 
     * @param modelName
     *            ָ����ģ��
     * @return
     */
    public boolean hasPower(String modelName)
    {
        if (null == UserPower || "".equals(UserPower))
            return false;
        return UserPower.contains("{" + modelName + "}");
    }
    
    /**
     * ���ظ��û��Ƿ����ĳģ��ĳ�����ܵ�ʹ��Ȩ��
     * @param menuid
     * @param mFunc
     * @return
     */
    public boolean hasPowerFunc(String menuid , String mFunc)
    {
        if(menuid == null || "".equals(menuid) || mFunc == null || "".equals(mFunc))
            return false;
        List<String> mFuncs = this.userPowerFunc.get(menuid);
        if(mFuncs != null)
        {
            return mFuncs.contains(mFunc);
        }
        return  false;
    }

    public String getUserToken()
    {
        return UserToken;
    }

    public void setUserToken(String userToken)
    {
        UserToken = userToken;
    }
    
}