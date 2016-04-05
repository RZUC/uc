package com.uc.system.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uc.system.service.UserService;

/**
 * 
 * @ClassName: ManagerController
 * @Description: TODO(后台管理界面)
 * @author chenweitao
 * @date 2016年2月29日 下午5:30:34
 */
@Controller
@RequestMapping(value = "/systemUser")
public class UserController extends GeneralController
{
    @Resource
    UserService userService;
    
    @RequestMapping(value = "/show")
    public void show(HttpServletResponse response, HttpServletRequest request)
        throws Exception
    {
        getJsonStrByList(userService.findAllUsers(), response);
    }
}
