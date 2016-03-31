package test.com.zhiwei.wjgg.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.service.HUserInfoBDService;

import test.object.ObjectTest;

public class HUserInfoBDServiceTest extends ObjectTest
{
    @Resource
    HUserInfoBDService hUserInfoBDService;
    
    @Test
    public void findTest(){
        HUserInfoBD h = hUserInfoBDService.findByName("今日头条");
        System.out.println("h:"+ h);
    }
    
    @Test
    public void findByIdTest(){
        List<String> ids = new ArrayList<String>();
        ids.add("expo.people.com.cn");
        
        List<HUserInfoBD> h = hUserInfoBDService.findByIdList(ids);
        for (HUserInfoBD hUserInfoBD : h)
        {
            System.out.println("ChannelIndex:"+hUserInfoBD.getChannelIndex());
        }
    }
}
