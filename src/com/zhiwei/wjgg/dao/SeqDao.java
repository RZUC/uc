package com.zhiwei.wjgg.dao;

import com.zhiwei.wjgg.model.Seq;

/**
 * 
 * @ClassName: seqDao 
 * @Description: TODO(自增键操作) 
 * @author chenweitao 
 * @date 2016年3月3日 下午4:01:01
 */
public interface SeqDao extends CommonDao<Seq>
{
    /**
     * 
     * @Title: getNextId 
     * @Description: TODO(获取自增键) 
     * @param @param seq_name
     * @param @return 设定文件 
     * @return String 返回类型
     */
    public String getNextId(String seq_name);
}
