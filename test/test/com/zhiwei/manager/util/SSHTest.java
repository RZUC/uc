/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年2月17日
    * @version 1.00 
*/
package test.com.zhiwei.manager.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zhiwei.uc.exception.ZhiWeiException;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: SSHTest
 * @author 落花流水
 * @date 2016年2月17日 上午10:19:08
 */
public class SSHTest
{
    Channel channel = null;
    
    private static final JSch jsch = new JSch();
    
    @Test
    public void connectionTest()
        throws Exception
    {
        String name = "root";
        String password = "SNS252powerstudio";
        String ip = "192.168.0.27";
        
        Session session = getSSHSession(name, password, ip);
        
        ChannelExec channel = (ChannelExec)session.openChannel("exec");
        channel.setCommand("service iptables restart");
        channel.connect(1000);
        
        Print(channel);
        
        Close(session, channel);
    }
    
    private void Print(ChannelExec channel)
        throws IOException, Exception, UnsupportedEncodingException
    {
        InputStream instream = channel.getInputStream();
        int i =0;
        do
        {   
            i++;
            if (instream.available() > 0)
            {
                byte[] data = new byte[instream.available()];
                
                int nLen = instream.read(data);
                
                if (nLen < 0)
                {
                    throw new Exception("network error.");
                }
                
                String temp = new String(data, 0, nLen, "iso8859-1");
                System.out.println(temp);
                
                if (null != temp && !"".equals(temp))
                {
                    instream.close();
                    instream = null;
                }
               
                System.out.println(channel.getExitStatus());
                
            }
        } while (instream != null);
    }
    
    private void Close(Session session, ChannelExec channel)
    {   
        channel.disconnect();
        session.disconnect();
    }
    
    public Session getSSHSession(String name, String password, String ip)
        throws ZhiWeiException
    {
        Session session = null;
        try
        {
            session = jsch.getSession(name, ip);
            
            if (null == session)
            {
                throw new ZhiWeiException("创建session失败");
            }
            
            session.setPassword(password.getBytes());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        }
        catch (JSchException e)
        {
            e.printStackTrace();
        }
        return session;
    }
    
}
