package org.scy.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {
    @RequestMapping("/login")
    public String login() {
        // 处理登录页面的逻辑
        // 返回登录页面的视图名称
        return null;
    }

    @RequestMapping("/logout")
    public String logout() {
        // 处理注销的逻辑
        // 清除用户的认证信息和相关的会话数据
        // 返回注销后的页面视图名称
        return null;
    }


}








