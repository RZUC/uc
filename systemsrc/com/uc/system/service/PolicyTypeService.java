/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/

package  com.uc.system.service;import java.util.List;import com.uc.system.model.Message;import com.uc.system.model.PolicyType;/**
 * @author Simple
 * @date 2016-04-18
 */
public interface PolicyTypeService
{		public PolicyType addPolicyType(PolicyType policyType);	public Message delPolicyType(String id);	public PolicyType modiyfPolicyType(PolicyType policyType);	public List<PolicyType> findAll();
}