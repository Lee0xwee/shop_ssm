package cn.Lee.ssm.user.controller;


import cn.Lee.ssm.index.controller.exception.CustomException;
import cn.Lee.ssm.user.pojo.User;
import cn.Lee.ssm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("toLoginPage")
    public String toLoginPage() {

        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password, String verifyCode, HttpSession session) throws Exception {


        //校验验证码
        String vCode = (String) session.getAttribute("vCode");
        if (!vCode.equalsIgnoreCase(verifyCode)) {

            throw new CustomException("验证码错误！");
        }

        User user = userService.findByUsername(username);

        //校验用户
        if (user == null) {

            throw new CustomException("用户不存在！");

        }

        //校验密码
        if (!user.getPassword().equals(password)) {

            throw new CustomException("用户或密码错误！");
        }

        session.setAttribute("sessionUser", user);

        return "redirect:/index.action";
    }

    @RequestMapping("toRegistPage")
    public String toRegistPage() throws Exception {

        return "regist";

    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public String checkUsername(User user) throws Exception {

        if (user.getUsername() == null || user.getUsername() == "") {

            return "null";
        }

        User exsitUser = userService.findByUsername(user.getUsername());

        if (exsitUser != null) {

            return "fail";

        } else {

            return "success";
        }


    }

    @RequestMapping("checkEmail")
    @ResponseBody
    public String checkEmail(User user) throws Exception {

        if (user.getEmail() == null || user.getEmail() == "") {

            return "null";
        }

        User exsitUser = userService.findByEmail(user.getEmail());


        if (exsitUser != null) {

            return "fail";
        }

        return "success";

    }

    @RequestMapping("regist")
    public String regist(Model model, String verifyCode, User user, HttpSession session) throws Exception {

        //校验验证码
        String vCode = (String) session.getAttribute("vCode");
        if (!vCode.equalsIgnoreCase(verifyCode)) {

            throw new CustomException("验证码错误！");
        }

        userService.regist(user);
        String msg = "注册成功，请去邮箱激活账号！";
        model.addAttribute("msg", msg);
        return "msg";

    }

    @RequestMapping("active")
    public String active(Model model, String code) throws Exception {

        User user = userService.findByCode(code);
        String msg = null;
        if (user != null) {

            userService.updateUser(user);
            msg = "账号激活成功，请前往登录！";
            model.addAttribute("msg", msg);

        } else {

            msg = "激活码失效或账号已激活！";
            model.addAttribute("msg", msg);

        }

        return "msg";

    }

    @RequestMapping("logout")
    public String logout(HttpSession session) throws Exception {

        session.invalidate();
        return "redirect:/index.action";

    }


}
