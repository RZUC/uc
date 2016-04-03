package com.uc.system.util;

/**
 * 
 * @ClassName: UrlUtil
 * @Description: TODO(url相关工具类)
 * @author chenweitao
 * @date 2016年3月14日 上午9:23:53
 */
public class UrlUtil
{
    /**
     * 
     * @Title: getMainUrl
     * @Description: TODO(切取主域名)
     * @param @param url
     * @param @return 设定文件
     * @return String 返回类型
     */
    public static String getMainUrl(String url)
    {
        if (null != url && !"".equals(url) && url.contains("http://"))
            ;
        url = url.split(",")[0].split("http://")[1].split("/")[0];
        return url;
    }

    public static String getOpenid(String url)
    {
        String openid = url.toString().split(",")[1];
        openid = "http://weixin.sogou.com/gzh?openid=" + openid;

        return openid;
    }

    public static void main(String[] args)
    {
        String str = getMainUrl("http://toutiao.com/group/6256720759504535809/");
        System.out.println(str);
    }
}
