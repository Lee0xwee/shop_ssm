package cn.Lee.ssm.product.controller;


import cn.Lee.ssm.product.pojo.Product;
import cn.Lee.ssm.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @RequestMapping("findByPid")
    public String findByPid(Model model, int pid) {

        Product product = productService.findByPid(pid);
        model.addAttribute("product", product);
        return "product";


    }
}
