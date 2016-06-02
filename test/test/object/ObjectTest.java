/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月16日
    * @version 1.00 
*/
package test.object;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uc.system.DBTemp.HttpSolrServerUtil;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: ObjectTest
 * @author 落花流水
 * @date 2016年1月16日 上午11:40:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-config.xml"})
public abstract class ObjectTest extends AbstractJUnit4SpringContextTests
{
    public void ZIPTest(String[] args)
        throws IOException
    {
        File file = new File("E://dic//custom//words-custom.dic");
        
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(file.getPath().replace(".dic", ".zip")));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        FileInputStream in = new FileInputStream(file);
        BufferedInputStream bi = new BufferedInputStream(in);
        out.putNextEntry(new ZipEntry(file.getName()));
        
        int b;
        while ((b = bi.read()) != -1)
        {
            bo.write(b);
        }
        bi.close();
        in.close();
        bo.close();
        out.close();
    }
}
