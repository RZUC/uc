package com.uc.system.service.impl;

import javax.annotation.Resource;

import com.uc.system.dao.LocationDao ;
import com.uc.system.service.LocationService ;
/**
 * @author cwt
 * @date 2016-04-06
 */


public class LocationServiceImpl implements LocationService{

    @Resource
    private LocationDao locationDao;

}