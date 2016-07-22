package com.uc.system.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Department;
import com.uc.system.model.Industry;
import com.uc.system.model.Location;
import com.uc.system.model.OrganizationType;
import com.uc.system.service.DeparmentService;
import com.uc.system.service.IndustryService;
import com.uc.system.service.LocationService;
import com.uc.system.service.OrganizationTypeService;

/**
 * @author Simple
 * 
 */
/**
 * @Description: 政策信息的维护<br>
 *               增加<br>
 *               删除<br>
 *               修改<br>
 *               推到头条<br>
 *               取消头条<br>
 *               查询：多个查询条件，类型，时间，区域，行业，关键词<br>
 * @ClassName: PolicyInfoController
 * @author 落花流水
 * @date 2016年4月19日 下午9:17:14
 */
@Controller
@RequestMapping(value = "/parameter")
public class SystemParameterController extends GeneralController
{
    @Resource
    LocationService locationService;
    
    @Resource
    IndustryService industryService;
    
    @Resource
    DeparmentService departmentService;
    
    @Resource
    OrganizationTypeService organizationTypeService;
    
    @RequestMapping(value = "/location")
    public void showLocation(@RequestParam(value = "fatherId", required = false, defaultValue = "") int fatherId,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        List<Location> list = locationService.findLocationByFatherId(fatherId);
        getJsonStrDataByList(list, "返回地域信息", 1, 1, true, response);
    }
    
    @RequestMapping(value = "/industry")
    public void showIndustry(@RequestParam(value = "fatherId", required = false, defaultValue = "") int fatherId,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        List<Industry> list = industryService.findByFatherID(fatherId);
        getJsonStrDataByList(list, "返回行业信息", 1, 1, true, response);
    }
    
    @RequestMapping(value = "/department")
    public void showDepartment(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        List<Department> list = departmentService.getDeparmentList();
        getJsonStrDataByList(list, "返回行业信息", 1, 1, true, response);
    }
    
    @RequestMapping(value = "/organizationtype")
    public ResponseEntity<Map<String, Object>> showUserType()
        throws Exception
    {
        List<OrganizationType> list = organizationTypeService.getServiceTypeList();
        Map<String, Object> usertype = new HashMap<String, Object>();
        usertype.put("message", "返回服务机构类型");
        usertype.put("state", true);
        usertype.put("data", list);
        usertype.put("totalPage", 1);
        usertype.put("currentPage", 1);
        return new ResponseEntity<Map<String, Object>>(usertype, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/usertype")
    public ResponseEntity<Map<String, Object>> showServiceType()
        throws Exception
    {
        List<Map> list = getUserType();
        Map<String, Object> usertype = new HashMap<String, Object>();
        usertype.put("message", "返回用户类型");
        usertype.put("state", true);
        usertype.put("data", list);
        usertype.put("totalPage", 1);
        usertype.put("currentPage", 1);
        return new ResponseEntity<Map<String, Object>>(usertype, HttpStatus.OK);
    }
    
    private List<Map> getUserType()
    {
        List<Map> list = new ArrayList<Map>();
        Map map = new HashMap();
        map.put("id", "1");
        map.put("name", "企业账户");
        Map map2 = new HashMap();
        map2.put("id", "2");
        map2.put("name", "服务机构");
        list.add(map);
        list.add(map2);
        return list;
    }
}
