package com.zhiwei.wjgg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.HUserInfoWBDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.service.HUserInfoWBService;

/**
 * @author cwt
 * @date 2016-03-04
 */
@Service
public class HUserInfoWBServiceImpl extends GeneralServiceImpl implements
        HUserInfoWBService
{

    @Resource
    private HUserInfoWBDao dao;

    @Override
    public boolean add(HUserInfoWB ob)
    {
        return false;
    }

    @Override
    public boolean updata(HUserInfoWB ob)
    {
        return false;
    }

    @Override
    public boolean delete(HUserInfoWB ob)
    {
        return false;
    }

    @Override
    public boolean deleteById(String id)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean search(HUserInfoWB ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public HUserInfoWB findOneById(String id)
    {
        try
        {
            return dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HUserInfoWB> findAll(Object... ob)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HUserInfoWB> findByIdList(List<String> ids)
    {
        return dao.findByIdList(ids);
    }

    @Override
    public List<HUserInfoWB> findByIdList(List<String> ids, int num)
    {
        return dao.findByIdList(ids, num);
    }

    @Override
    public HUserInfoWB findByName(String source)
    {
        return dao.findByName(source);
    }

}