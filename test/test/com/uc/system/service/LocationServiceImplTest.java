/**
 * 
 */
package test.com.uc.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Location;
import com.uc.system.service.LocationService;

import test.object.ObjectTest;

/**
 * @author Simple
 *
 */
public class LocationServiceImplTest extends ObjectTest
{
    
    @Resource
    LocationService service;
    
    @Test
    public void findOneTest()
        throws ZhiWeiException
    {
        List<Location> list = service.findProvince();
        int i = 0;
        for (Location l : list)
        {
            System.out.println(i + "--" + l.getAbbreviation());
            i++;
        }
    }
}
