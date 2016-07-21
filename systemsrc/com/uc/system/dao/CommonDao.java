package com.uc.system.dao;

import java.util.List;

import com.uc.system.exception.ZhiWeiException;

/**
 * 公共接口
 * 
 * @ClassName: CommonDao
 * @Description: 定义基础操作方法
 * @author God
 * @date 2015-2-11 上午9:15:20
 * 
 * @param <T>
 */
public interface CommonDao<T>
{
    /*
     * 插入
     */
    public T insert(T ob)
        throws ZhiWeiException;
    /*
     * 按对象查
     */
    public T findOne(T ob)
        throws ZhiWeiException;
    
    /*
     * 查询全部
     */
	public List<T> findAll()
        throws ZhiWeiException;
    
    /*
     * 查询数据量
     */
    public long findCont()
        throws ZhiWeiException;
    
    /*
     * 按照id移除
     */
    public boolean removeOneById(String id)
        throws ZhiWeiException;
    
    /*
     * 移除全部
     */
    public boolean removeAll()
        throws ZhiWeiException;
    
    /*
     * 改
     */
    public boolean findAndModify(T ob)
        throws ZhiWeiException;
}
