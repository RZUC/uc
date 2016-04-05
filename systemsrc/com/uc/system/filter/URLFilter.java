package com.uc.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uc.system.model.User;

/**
 * 
 * 项目名称：WeiXinSiXin 类名称：CodeFilter 类描述：过滤器用于过滤乱码问题 创建人：littleFAT 创建时间：2015年5月21日 下午2:34:26 修改人：littleFAT 修改时间：2015年5月21日
 * 下午2:34:26 修改备注：
 * 
 * @version
 * 
 */
public class URLFilter implements Filter
{   
    public static Logger log = LoggerFactory.getLogger(URLFilter.class);
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        String path = servletRequest.getRequestURI();
        HttpServletResponse servletResponse = (HttpServletResponse)response;
        
        log.debug("访问的连接：{}",path);
        String[] paths = path.split("/");
        chain.doFilter(request, response);
        //暂时不做过滤
//        if (goOn(chain, servletRequest, path, servletResponse))
//        {
//            // 如果是登录，或者存在User,那么可以
//            chain.doFilter(request, response);
//        }
//        else
//            
//        {   
//            servletResponse.sendRedirect("/" + paths[1] + "/login.html");
//        }
    }
    
    private boolean goOn(FilterChain chain, HttpServletRequest servletRequest, String path,
        HttpServletResponse servletResponse)
            throws IOException, ServletException
    {
        HttpSession session = servletRequest.getSession();
        User user = (User)session.getAttribute("user");
        
        if (user != null || path.indexOf("login.html") > -1 || path.indexOf("login.do") > -1||path.contains("Public"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public void destroy()
    {
    }
}