/** 
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. 
 * FileName: Test.java 
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年1月15日
 * @version 1.00 
 */
package test.com.zhiwei.manager.util;

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.util.Sequence;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: Test
 * @author 落花流水
 * @date 2016年1月15日 上午9:50:43
 */
public class SeqTest extends ObjectTest {

	@Resource
	Sequence service;

	@Test
	public void solrSearchTest() throws ZhiWeiException {

		System.out.println(service.getNextId("policyInfo"));
	}
}
