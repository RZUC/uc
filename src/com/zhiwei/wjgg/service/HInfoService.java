package com.zhiwei.wjgg.service;

import java.util.List;

public interface HInfoService<T>
{
    /**
     * 
     * @Title: findByIdList 
     * @Description: TODO(批量查询对象) 
     * @param @param ids
     * @param @param num
     * @param @return 设定文件 
     * @return List<T> 返回类型
     */
    public List<T> findByIdList(List<String> ids,int num);
    
    /**
     * 
     * @Title: findByName 
     * @Description: TODO(根据source获取对象) 
     * @param @param source
     * @param @return 设定文件 
     * @return T 返回类型
     */
    public T findByName(String source);
    
}
