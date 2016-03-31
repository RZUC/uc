/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/

package com.zhiwei.wjgg.dao;

import java.util.List;import com.zhiwei.wjgg.dao.CommonDao;
import com.zhiwei.wjgg.model.Similarity;
/**
 * @author cwt
 * @date 2016-03-08
 */
public interface SimilarityDao extends CommonDao<Similarity>
{    public List<Similarity> insert(List<Similarity> obList);
}