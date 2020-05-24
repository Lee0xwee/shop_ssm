package cn.Lee.ssm.index.controller;


import cn.Lee.ssm.category.pojo.Category;
import cn.Lee.ssm.category.pojo.Categorysecond;
import cn.Lee.ssm.category.pojo.CustomCategory;
import cn.Lee.ssm.category.service.CategoryService;
import cn.Lee.ssm.product.pojo.Product;
import cn.Lee.ssm.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping("index")
    public String index(Model model, HttpSession session) {


        //查询热门商品
        List<Product> hotList = productService.findByHot();
        //查询最新商品
        List<Product> newList = productService.findByNew();
        //查询一级目录，保存在session域中
        List<CustomCategory> cList = categoryService.findAll();


        session.setAttribute("cList", cList);

        //将数据保存到model中，用于jsp获取数据
        model.addAttribute("hotList", hotList);
        model.addAttribute("newList", newList);

        return "index";

    }
}
