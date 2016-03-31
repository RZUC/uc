/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/

package com.zhiwei.wjgg.dao;

import java.util.List;import com.zhiwei.wjgg.dao.CommonDao;
import com.zhiwei.wjgg.model.HUserInfoWB;
/**
 * @author cwt
 * @date 2016-03-04
 */
public interface HUserInfoWBDao extends CommonDao<HUserInfoWB>,HInfoDao<HUserInfoWB>
{    /**     *      * @Title: findByIdList      * @Description: TODO(获取多id的对象)      * @param @param ids     * @param @return 设定文件      * @return List<HUserInfo> 返回类型     */    public List<HUserInfoWB> findByIdList(List<String> ids);
}