package com.uc.system.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Location;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.Query;
import com.uc.system.service.PolicyService;

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
@RequestMapping(value = "/policyInfo")
public class PolicyInfoController extends GeneralController
{
    @Resource
    PolicyService service;
    
    @RequestMapping(value = "/show")
    public void show(@RequestBody Query query, @RequestBody Page page, HttpServletRequest request,
        HttpServletResponse response)
            throws Exception
    {
        List<PolicyInfo> list = service.findList(query,page);
        
        getJsonStrDataByList(list, response);
    }
    
    @RequestMapping(value = "/add")
    public void addPolicyInfo(@RequestBody PolicyInfo info,
        HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Message message = service.add(info);
        
    }
    
    @RequestMapping(value = "/del")
    public void delPolicyInfo(@RequestParam(value = "id", required = false, defaultValue = "") String id,
        HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {   
        if(!"".equals(id))
        {
            Message message = service.del(id);   
        }
         
    }
    
    @RequestMapping(value = "/update")
    public void updatePolicyInfo(@RequestBody PolicyInfo info,
        HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Message message  = service.update(info);
    }
    
    @RequestMapping(value = "/top")
    public void topolicyInfo(@RequestParam(value = "id", required = false, defaultValue = "") String id,
        HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Message message  = service.top(id);
    }
    
    @RequestMapping(value = "/untop")
    public void unTopolicyInfo( @RequestParam(value = "id", required = false, defaultValue = "") String id,
        
        HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Message message  = service.unTop(id);
    }
}
