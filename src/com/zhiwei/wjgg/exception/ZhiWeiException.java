package com.zhiwei.wjgg.exception;

/**
 * 自定义异常类
 * 
 * @ClassName: ZhiWeiException
 * @Description: 知微自定义异常
 * @author God
 * @date 2015-2-11 上午9:23:12
 *       
 */
public class ZhiWeiException extends Exception
{
    
    private static final long serialVersionUID = 1L;
    
    public ZhiWeiException()
    {
        super();
    }
    
    public ZhiWeiException(String message)
    {
        super(message);
    }
    
}
