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
    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        Admin adminUser = adminService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getAdminNickName());
            session.setAttribute("loginUserId", adminUser.getAdminId());
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登陆失败");
            return "admin/login";
        }
    }

    /**
     * 登出就是将session中的值全部移除，这样在访问时拦截器就会进行拦截
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){

        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");

        return "admin/login";
    }

    /**
     * 跳转到修改信息页面
     * @param request
     * @return
     */
    @GetMapping("/profile")
    public String profile(HttpServletRequest request){

        Long loginUserId=(Long) request.getSession().getAttribute("loginUserId");
        Admin admin=adminService.getUserDetailById(loginUserId);
        if(admin==null)
            return "admin/login";

        request.setAttribute("path","profile");
        request.setAttribute("loginUserName",admin.getLoginName());
        request.setAttribute("nickName",admin.getAdminNickName());

        return "admin/profile";
    }

    @PostMapping("/profile/password")
    public String passwordUpdate(HttpServletRequest request,
                                 @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword){

        if(StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)){
            request.setAttribute("errorMsgPwd", "不能为空");
            return "admin/profile";
        }

        Long loginUserId=(Long)request.getSession().getAttribute("loginUserId");
        if(adminService.updatePassword(loginUserId,originalPassword,newPassword)){
            //修改成功后清空session中的数据，跳转到登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");

            return "admin/login";
        } else {
            request.setAttribute("errorMsgPwd", "修改失败");
            return "admin/profile";
        }
    }

    @RequestMapping("/profile/name")
    public String nameUpdate(HttpServletRequest request,
                             @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName){

        if(StringUtils.isEmpty(loginUserName) && StringUtils.isEmpty(nickName)){
            request.setAttribute("errorMsgName", "不能为空");
            return "admin/profile";
        }

        Long loginUserId=(Long) request.getSession().getAttribute("loginUserId");

        if(adminService.updateName(loginUserId,loginUserName,nickName)){
            request.setAttribute("loginUserName",loginUserName);
            request.setAttribute("nickName",nickName);
            return "admin/index";
        } else {
            request.setAttribute("errorMsgName", "修改失败");
            return "admin/profile";
        }
    }

}
