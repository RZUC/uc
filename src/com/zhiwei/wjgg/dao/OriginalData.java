/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月14日
    * @version 1.00 
*/
package com.zhiwei.wjgg.dao;

import java.util.List;

/**
 * @Description: 查询事件的原始数据
 * @ClassName: SolrData
 * @author 落花流水
 * @date 2016年3月14日 上午10:12:22
 */
public interface OriginalData
{
    /**
     * 
     * @Title: findByIdList
     * @Description: TODO(获取多id的对象)
     * @param @param ids
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public List<String> findByIdList(List<String> ids);
}
