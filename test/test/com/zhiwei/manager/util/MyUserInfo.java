/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年2月18日
    * @version 1.00 
*/ 
package test.com.zhiwei.manager.util;

import com.jcraft.jsch.UserInfo;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @ClassName: MyUserInfo 
 * @author 落花流水 
 * @date 2016年2月18日 下午2:39:15  
 */
public class MyUserInfo implements UserInfo 
{
    private String password;  
    
    private String passphrase;  
  
    @Override  
    public String getPassphrase() {  
        System.out.println("MyUserInfo.getPassphrase()");  
        return null;  
    }  
  
    @Override  
    public String getPassword() {  
        System.out.println("MyUserInfo.getPassword()");  
        return null;  
    }  
  
    @Override  
    public boolean promptPassphrase(final String arg0) {  
        System.out.println("MyUserInfo.promptPassphrase()");  
        System.out.println(arg0);  
        return false;  
    }  
  
    @Override  
    public boolean promptPassword(final String arg0) {  
        System.out.println("MyUserInfo.promptPassword()");  
        System.out.println(arg0);  
        return false;  
    }  
  
    @Override  
    public boolean promptYesNo(final String arg0) {  
        System.out.println("MyUserInfo.promptYesNo()");  
        System.out.println(arg0);  
        if (arg0.contains("The authenticity of host")) {  
            return true;  
        }  
        return false;  
    }  
  
    @Override  
    public void showMessage(final String arg0) {  
        System.out.println("MyUserInfo.showMessage()");  
    } 
}
