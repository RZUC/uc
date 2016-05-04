/**
 * 
 */
package test.com.uc.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.PolicyInfo;
import com.uc.system.service.PolicyService;
import com.uc.system.util.TimeUtil;

import test.object.ObjectTest;

/**
 * @author Simple
 *
 */
public class PolicyInfoServiceTest extends ObjectTest
{
    
    @Resource
    PolicyService service;
    
    @Test
    public void add()
        throws ZhiWeiException
    {
         
        List<Resource> resourceList = new ArrayList<Resource>();
        
        PolicyInfo info = new PolicyInfo();
        
        info.setTitle("政策标题");
        info.setSourceUrl("发不来的url");
        info.setPolicyType("政策类型ID");
        info.setIndustry("行业ID");
        info.setLocation("地域ID");
        info.setTopState(1);//是否制定，是
        info.setOrder(1);
        info.setTopStateEndTime(10);//设置置顶有效的时间
        info.setProvince("省份ID");
        info.setCity("城市ID");
        info.setDowntown("地区ID");
        info.setReleaseTime(TimeUtil.formatDate(TimeUtil.getSkipTime(1)));
        info.setContent("测试政策数据");
        info.setCreateTime(TimeUtil.getCurrentTime());
        info.setDepartment("部门ID");
        info.setLastUpdateTime(TimeUtil.getCurrentTime());
//        info.setResourceList(resourceList);
        
        service.add(info);
    }
}
