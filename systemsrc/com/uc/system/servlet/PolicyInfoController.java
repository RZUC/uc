package com.uc.system.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.PolicyInfoView;
import com.uc.system.service.PolicyService;

import net.sf.json.JSONObject;

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
    
    @RequestMapping(value = "/showType")
    public void showType(@RequestParam(value = "type", required = false, defaultValue = "") String type,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        List<PolicyInfoView> view;
        try
        {
            List<PolicyInfo> list = new ArrayList<PolicyInfo>();
            if ("top".equals(type))
            {
                list = service.findListByTop(10);
            }
            else
            {
                Page page = new Page();
                page.setPageNum(1);
                page.setPageSize(10);
                list = service.findList(Integer.valueOf(type), page);
            }
            // Map<String, String> map = (Map<String, String>) request
            // .getSession().getServletContext().getAttribute("locaiton");
            // System.out.println("map:" + map.size());
            view = service.getViewList(list);
            getJsonStrDataByList(view, "显示数据：" + type, 1, 1, true, response);
        }
        catch (Exception e)
        {
            getJsonStrDataByList(null, "显示数据失败：" + type, 1, 1, false, response);
        }
        
    }
    
    @RequestMapping(value = "/show")
    public void show(@RequestParam(value = "type", required = false, defaultValue = "") String type,
        @RequestParam(value = "pageNum", required = false, defaultValue = "") int pageNum,
        @RequestParam(value = "pageSize", required = false, defaultValue = "") int pageSize,
        HttpServletResponse response)
        throws Exception
    {
        int totalPage = 0;
        int policyType = 0;
        try
        {
            Page page = new Page(pageSize, pageNum);
            if (!"top".equals(type))
            {
                policyType = Integer.valueOf(type);
            }
            List<PolicyInfo> list = service.findList(policyType, page);
            List<PolicyInfoView> view = service.getViewList(list);
            
            totalPage = getTotalPage(pageSize, service.getTotalCount(policyType, page));
            
            getJsonStrDataByList(view, "显示数据：" + type, totalPage, pageNum, true, response);
        }
        catch (Exception e)
        {
            getJsonStrDataByList(null, "数据失败：" + type, totalPage, pageNum, false, response);
        }
    }
    
    private int getTotalPage(int pageSize, int total)
    {
        return (total % pageSize == 0) ? total / pageSize : (total / pageSize + 1);
    }
    
    @RequestMapping(value = "/showDetail")
    public void showDetail(@RequestParam(value = "id", required = false, defaultValue = "") String id,
        HttpServletResponse response)
        throws Exception
    {
        
        PolicyInfo info = service.findById(id);
        try
        {
            getJsonStrDataByObject(info, "获取：" + id + "成功", true, response);
        }
        catch (Exception e)
        {
            getJsonStrDataByObject(info, "获取：" + id + "失败", false, response);
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
    public String addPolicyInfo(PolicyInfo info, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        info = service.add(info);
        try
        {
            map.put("message", "添加政策信息失败");
            map.put("state", true);
            map.put("data", info);
            // getJsonStrDataByObject(info, "添加政策信息成功：", true, response);
        }
        catch (Exception e)
        {
            map.put("message", "添加政策信息失败");
            map.put("state", false);
            map.put("data", info);
            // getJsonStrDataByObject(null, "添加政策信息失败", false, response);
        }
        
        return JSONObject.fromObject(map).toString();
    }
    
    // @ResponseBody
    // @RequestMapping(value = "/addOne", produces = "text/html;charset=UTF-8")
    // public String addInfo(PolicyInfo info, HttpServletRequest request, HttpServletResponse response)
    // throws Exception
    // {
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put("message", "添加政策信息失败");
    // map.put("state", true);
    // map.put("data", info);
    // System.out.println(info.toString());
    // return JSONObject.fromObject(map).toString();
    // }
    
    @RequestMapping(value = "/del")
    public void delPolicyInfo(@RequestParam(value = "id", required = false, defaultValue = "") String id,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (!"".equals(id))
        {
            Message message = service.del(id);
            getJsonStrDataByObject(null, message.getMessage(), message.isState(), response);
        }
        
    }
    
    @RequestMapping(value = "/update")
    public void updatePolicyInfo(@RequestBody PolicyInfo info, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Message message = service.update(info);
        getJsonStrDataByObject(null, message.getMessage(), message.isState(), response);
    }
    
    @RequestMapping(value = "/top")
    public void topolicyInfo(@RequestParam(value = "id", required = false, defaultValue = "") String id,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Message message = service.top(id);
    }
    
    @RequestMapping(value = "/untop")
    public void unTopolicyInfo(@RequestParam(value = "id", required = false, defaultValue = "") String id,
        
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Message message = service.unTop(id);
    }
}
