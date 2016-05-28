/**
 * 
 */
package test.com.uc.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.uc.system.dao.impl.PolicyInfoDaoImpl;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.PolicyInfo;
import com.uc.system.util.TimeUtil;

import test.object.ObjectTest;

/**
 * @author Simple
 *
 */
public class PolicTypeDaoTest extends ObjectTest
{
    
    @Resource
    PolicyInfoDaoImpl dao;
    
    @Test
    public void addTest()
        throws ZhiWeiException
    {
        String title = "奉化科技局发布内容";// 政策标题
        
        String sourceUrl = "奉化科技局";// 发布来源ID
        
        String department = "信息处";// 发布部门
        
        String policyType = "政策要闻";// 政策列表
        
        String industry = "科技";// 行业（技术领域）
        
        String location = "浙江 宁波 鄞州";// 区域
        
        int topState = 1;// 置顶字段
        
        int order = 0;// 排序字段
        
        int topStateEndTime = 1;// 信息有效期，有效期到后自动消除置顶
        
        String province = "浙江";// 省
        
        String city = "宁波";// 市
        
        String downtown = "鄞州";// 区
        
        String content = "科技信息梳理";// 内容
        
        String releaseTime = TimeUtil.getCurrentTimeStr();// 发布时间
        
        List<com.uc.system.model.Resource> resourceList = new ArrayList<com.uc.system.model.Resource>();// 资源文件
        
        Date createTime = TimeUtil.getCurrentTime();
        
        Date lastUpdateTime = TimeUtil.getCurrentTime();
//        PolicyInfo info = new PolicyInfo(title, sourceUrl, department, policyType, industry, location, topState, order,
//            topStateEndTime, province, city, downtown, content, releaseTime, resourceList, createTime, lastUpdateTime);
//        dao.insert(info);
        System.out.println("test dao");
    }
    
    @Test
    public void findAllByTITLETest()
    {
        List<PolicyInfo> list = dao.findAllByTITLE();
        for (PolicyInfo policyInfo : list)
        {
            System.out.println(policyInfo.toString());
        }
    }
    
}
