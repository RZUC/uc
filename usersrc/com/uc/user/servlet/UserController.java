package com.uc.user.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uc.system.service.UserService;
import com.uc.system.servlet.GeneralController;

/**
 * 
 * @ClassName: ManagerController
 * 用户信息维护
 * 增加<br>
 * 删除<br>
 * 修改<br>
 * 查询：用户类型 区域 行业时间 名称<br>
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
//        getJsonStrByList(userService.findAllUsers(), response);
    }
}
