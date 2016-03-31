package com.zhiwei.wjgg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.HUserInfoBDDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.service.HUserInfoBDService;

/**
 * @author cwt
 * @date 2016-03-04
 */

@Service
public class HUserInfoBDServiceImpl implements HUserInfoBDService
{

    @Resource
    private HUserInfoBDDao dao;

    @Override
    public boolean add(HUserInfoBD ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updata(HUserInfoBD ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(HUserInfoBD ob)
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
    public boolean search(HUserInfoBD ob)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public HUserInfoBD findOneById(String id)
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
    public List<HUserInfoBD> findAll(Object... ob)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HUserInfoBD> findByIdList(List<String> ids)
    {
        return dao.findByIdList(ids);
    }

    @Override
    public List<HUserInfoBD> findByIdList(List<String> ids, int num)
    {
        return dao.findByIdList(ids, num);
    }

    @Override
    public HUserInfoBD findByName(String source)
    {
        return dao.findByName(source);

    }

}