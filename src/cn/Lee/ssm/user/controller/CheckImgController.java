package cn.Lee.ssm.user.controller;


import cn.Lee.ssm.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
public class CheckImgController {

    @RequestMapping("checkImg")
    public void checkImg(HttpServletRequest request, HttpServletResponse response) throws Exception {


        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        VerifyCode.output(image, response.getOutputStream());
        request.getSession().setAttribute("vCode", vc.getText());

    }
}
