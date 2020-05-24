package cn.Lee.ssm.category.controller;

import cn.Lee.ssm.category.pojo.QueryVo;
import cn.Lee.ssm.category.service.CategoryService;
import cn.Lee.ssm.product.pojo.Product;
import cn.Lee.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("findByCid")
    public String findByCid(Model model, QueryVo queryVo) {


        //判断页面是否传入分页参数，若未赋值则自定义参数
        if (queryVo.getPageNumber() == 0) {

            queryVo.setPageNumber(1);
        }
        if (queryVo.getPageSize() == 0) {

            queryVo.setPageSize(12);
        }
        queryVo.setStart((queryVo.getPageNumber() - 1) * queryVo.getPageSize());

        PageBean<Product> pageBean = categoryService.findByPageCid(queryVo);

        model.addAttribute("pageBean", pageBean);
        model.addAttribute("cid", queryVo.getCid());

        return "categoryList";
    }

    @RequestMapping("findByCsid")
    public String findByCsid(Model model, QueryVo queryVo) {

        //判断页面是否传入分页参数，若未赋值则自定义参数
        if (queryVo.getPageNumber() == 0) {

            queryVo.setPageNumber(1);
        }
        if (queryVo.getPageSize() == 0) {

            queryVo.setPageSize(8);
        }
        queryVo.setStart((queryVo.getPageNumber() - 1) * queryVo.getPageSize());

        PageBean<Product> pageBean = categoryService.findByPageCsid(queryVo);

        model.addAttribute("pageBean", pageBean);
        model.addAttribute("csid", queryVo.getCsid());

        return "categoryList";
    }

}
