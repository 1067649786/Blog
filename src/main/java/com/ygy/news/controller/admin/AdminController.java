package com.ygy.news.controller.admin;

import com.ygy.news.entity.Admin;
import com.ygy.news.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 跳转到登录页
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    /**
     * 直接输入以下页面都会跳转到登陆页面
     * @param request
     * @return
     */
    @GetMapping({"","/","/index","/index.html"})
    public String index(HttpServletRequest request){
        request.setAttribute("path","index");
        return "admin/index";
    }

    /**
     * 登陆判断
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session){
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            session.setAttribute("errorMsg","用户名或密码不能为空");
            return "admin/login";
        }
        Admin adminUser=adminService.login(userName,password);
        if(adminUser!=null){
            session.setAttribute("loginUser",adminUser.getAdminNickName());
            session.setAttribute("loginUserId",adminUser.getAdminId());
            return "redirect:/admin/index";
        }else{
            session.setAttribute("errorMsg","登陆失败");
            return "admin/login";
        }
    }
}
