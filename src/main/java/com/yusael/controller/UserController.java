package com.yusael.controller;

import com.yusael.entity.User;
import com.yusael.service.UserService;
import com.yusael.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录方法
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password")String password, HttpSession session) {
        User login = userService.login(username, password);
        if (login != null) {
            session.setAttribute("user", login);//将username保存在session中
                                                    //可以通过session.getAttribute("username")的方法获得这个对象
            System.out.println("登录成功");
            return "redirect:/emp/findAll"; // 跳转到查询所有
        } else {
            return "redirect:/index"; // 跳转回到登录
        }
    }
    //防止表单重复提交 使用redirect: 重定向到.....

    // 注册方法
    @PostMapping("/register") //处理post请求  /register位置可以看作在static下的
    public String register(User user, String code, HttpSession session) {
        String sessionCode = (String)session.getAttribute("code"); // 生成的验证码
        // 忽略大小写, 比较用户输入的验证码与生成的验证码
        if (sessionCode.equalsIgnoreCase(code)) { // 输入正确
            userService.register(user); // 注册
            System.out.println("注册成功");
            return "redirect:/index"; // 注册成功跳转到登录界面
        } else { // 输入错误
            return "redirect:/toRegister"; // 注册失败跳转到注册界面
        }
    }

    // 生成验证码
    @GetMapping("/code")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成验证码
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        // 存入session作用域中
        session.setAttribute("code", securityCode);
        // 响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
