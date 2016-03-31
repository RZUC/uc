/**
 * 
 */
package com.zhiwei.wjgg.dao;

import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.ViewPoint;

/**@Description 
 * 
 * @author 李自贤
 * @date 2016年2月29日
 */
public interface ViewPointDao extends CommonDao<ViewPoint>
{
    /**
     * 
     * @Title: findOneByEventIdAndType 
     * @Description: TODO(根据事件id和type查找观点) 
     * @param @param eventId
     * @param @param type
     * @param @return
     * @param @throws ZhiWeiException 设定文件 
     * @return ViewPoint 返回类型
     */
    public ViewPoint findOneByEventIdAndType(String eventId,String type) throws ZhiWeiException;
}
