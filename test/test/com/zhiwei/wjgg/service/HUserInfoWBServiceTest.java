package test.com.zhiwei.wjgg.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.service.HUserInfoWBService;

import test.object.ObjectTest;

public class HUserInfoWBServiceTest extends ObjectTest
{
    @Resource
    HUserInfoWBService hUserInfoWBService;
    
    @Test
    public void findTest(){
        HUserInfoWB h = hUserInfoWBService.findByName("人民日报");
        System.out.println("h:"+ h);
    }
}
