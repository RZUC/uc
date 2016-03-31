/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/

package  com.zhiwei.wjgg.service;
import com.zhiwei.wjgg.model.Similarity;
/**
 * @author cwt
 * @date 2016-03-08
 */
public interface SimilarityService extends IGeneralService<Similarity>
{    /**     *      * @Title: addInfoByOb      * @Description: TODO(若有则添加，没有则创建)      * @param @param ob     * @param @return 设定文件      * @return boolean 返回类型     */    public boolean addInfoByOb(Similarity ob);
}