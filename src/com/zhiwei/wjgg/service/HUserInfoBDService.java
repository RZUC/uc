/*** *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  **/

package  com.zhiwei.wjgg.service;
import java.util.List;import com.zhiwei.wjgg.model.HUserInfoBD;
/**
 * @author cwt
 * @date 2016-03-04
 */
public interface HUserInfoBDService extends IGeneralService<HUserInfoBD>,HInfoService<HUserInfoBD>
{    /**     *      * @Title: findByIdList      * @Description: TODO(获取多id的对象)      * @param @param ids      * @param @return 设定文件      * @return List<DbUserInfo> 返回类型     */    public List<HUserInfoBD> findByIdList(List<String> ids);
}