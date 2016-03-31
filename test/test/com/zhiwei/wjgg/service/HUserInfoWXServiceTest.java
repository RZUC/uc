package test.com.zhiwei.wjgg.service;

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.service.HUserInfoWXService;

public class HUserInfoWXServiceTest extends ObjectTest
{
    @Resource
    HUserInfoWXService hUserInfoWXService;
    
    @Test
    public void findTest(){
        HUserInfoWX h = hUserInfoWXService.findByName("人民日报");
        System.out.println("h:"+ h);
    }
}
