package com.zhiwei.wjgg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.HUserInfoWXDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.service.HUserInfoWXService;

/**
 * @author cwt
 * @date 2016-03-04
 */

@Service
public class HUserInfoWXServiceImpl implements HUserInfoWXService
{

    @Resource
    private HUserInfoWXDao wxUserInfoDao;

    @Override
    public boolean add(HUserInfoWX ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updata(HUserInfoWX ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(HUserInfoWX ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(String id)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean search(HUserInfoWX ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public HUserInfoWX findOneById(String id)
    {
        try
        {
            return wxUserInfoDao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HUserInfoWX> findAll(Object... ob)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HUserInfoWX> findByIdList(List<String> ids)
    {
        return wxUserInfoDao.findByIdList(ids);
    }

    @Override
    public List<HUserInfoWX> findByIdList(List<String> ids, int num)
    {
        return wxUserInfoDao.findByIdList(ids, num);
    }

    @Override
    public HUserInfoWX findByName(String source)
    {
        return wxUserInfoDao.findByName(source);
    }

}