/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/

package com.uc.system.dao;

import java.util.List;import com.uc.system.exception.ZhiWeiException;import com.uc.system.model.PolicyType;
/** * @author Simple
 * @date 2016-04-18
 */
public interface PolicyTypeDao extends CommonDao<PolicyType>
{	public List<PolicyType> findListWithLimitAndSkip(int skip,int limit) throws ZhiWeiException;
}