package com.zhiwei.wjgg.service.impl;

import javax.annotation.Resource;

import com.zhiwei.wjgg.dao.WeiBoDao ;
import com.zhiwei.wjgg.service.WeiBoService ;
/**
 * @author cwt
 * @date 2016-03-01
 */


public class WeiBoServiceImpl implements WeiBoService{

    @Resource
    private WeiBoDao weiBoDao;

}