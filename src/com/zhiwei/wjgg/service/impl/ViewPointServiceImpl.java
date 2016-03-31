/**
 * 
 */
package com.zhiwei.wjgg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.dao.ViewPointDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.ViewPoint;
import com.zhiwei.wjgg.service.ViewPointService;

/**
 * @Description
 * 
 * @author 李自贤
 * @date 2016年2月29日
 */
@Service
public class ViewPointServiceImpl extends GeneralServiceImpl implements
        ViewPointService
{
    @Resource
    private ViewPointDao dao;

    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.service.IGeneralService#add(java.lang.Object)
     */
    @Override
    public boolean add(ViewPoint ob)
    {
        // 观点的百分比
        double total = 0;

        boolean flag = false;

        try
        {
            ViewPoint old_ob = dao.findOne(ob.getId());

            Map<String, Double> pointMap = ob.getPointMap();
            if(pointMap==null){
                throw new ZhiWeiException("观点数据为空");
            }
            for (Double num : pointMap.values())
            {
                total += num;
            }

            // 限定若观点超过6条或百分比超过100则添加失败
            if (pointMap.size() > 6 || total > 100)
            {
                flag = false;
            }
            else
            {
                long cont = dao.findCont();
                dao.insert(ob);
                if (cont - dao.findCont() < 0)
                {
                    flag = true;
                }

            }
        }
        catch (ZhiWeiException e)
        {
            log.error("观点添加出错：{}", e.getMessage());
        }

        return flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.service.IGeneralService#updata(java.lang.Object)
     */
    @Override
    public boolean updata(ViewPoint ob)
    {
        // 观点的百分比
        double total = 0;

        boolean flag = false;

        try
        {

            Map<String, Double> pointMap = ob.getPointMap();
            for (Double num : pointMap.values())
            {
                total += num;
            }

            // 限定若观点超过6条或百分比超过100则添加失败
            if (pointMap.size() > 6 || total > 1)
            {
                flag = false;
            }
            else
            {
                dao.findAndModify(ob);
                flag = true;
            }

        }
        catch (ZhiWeiException e)
        {
            log.error("观点修改出错：{}", e.getMessage());
        }

        return flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.service.IGeneralService#delete(java.lang.Object)
     */
    @Override
    public boolean delete(ViewPoint ob)
    {
        boolean flag = false;
        try
        {
            ViewPoint old_ob = dao.findOne(ob.getId());

            Map<String, Double> pointMap = old_ob.getPointMap();
            for (String key : ob.getPointMap().keySet())
            {
                pointMap.remove(key);
            }

            // 若删除该事件该类型观点后，观点数为零，则删除该观点，否则修改删部分
            if (pointMap.size() > 0)
            {
                dao.findAndModify(old_ob);
                flag = true;
            }
            else
            {
                long cont = dao.findCont();
                dao.removeOneById(ob.getId());
                if (cont - dao.findCont() > 0)
                {
                    flag = true;
                }
            }

        }
        catch (ZhiWeiException e)
        {
            log.error("观点删除出错：{}", e.getMessage());
        }

        return flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.service.IGeneralService#deleteById(java.lang.String)
     */
    @Override
    public boolean deleteById(String id)
    {
        // TODO Auto-generated method stub
        boolean result = false;
        try
        {
            long cont = dao.findCont();
            dao.removeOneById(id);
            if (cont - dao.findCont() > 0)
            {
                result = true;
            }
        }
        catch (ZhiWeiException e)
        {
            log.error("观点删除出错：{}", e.getMessage());
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.service.IGeneralService#search(java.lang.Object)
     */
    @Override
    public boolean search(ViewPoint ob)
    {
        // TODO Auto-generated method stub
        boolean flag = false;
        try
        {
            if (null != dao.findOne(ob))
            {
                flag = true;
            }
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.zhiwei.wjgg.service.IGeneralService#findOneById(java.lang.String)
     */
    @Override
    public ViewPoint findOneById(String id)
    {
        // TODO Auto-generated method stub
        ViewPoint ob = null;
        try
        {
            ob = dao.findOne(id);
        }
        catch (ZhiWeiException e)
        {
            log.error("观点查询出错：{}", e.getMessage());
        }
        return ob;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zhiwei.wjgg.service.IGeneralService#findAll(java.lang.Object[])
     */
    @Override
    public List<ViewPoint> findAll(Object... ob)
    {
        List<ViewPoint> list = null;
        try
        {
            list = dao.findAll();
        }
        catch (ZhiWeiException e)
        {
            log.error("观点查询所有出错：{}", e.getMessage());
        }
        return list;
    }

    @Override
    public ViewPoint findOneByEventIdAndType(String eventId, String type)
    {
        try
        {
            return dao.findOneByEventIdAndType(eventId, type);
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
