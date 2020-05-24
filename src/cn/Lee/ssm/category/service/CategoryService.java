package cn.Lee.ssm.category.service;


import cn.Lee.ssm.category.mapper.CategoryMapper;
import cn.Lee.ssm.category.mapper.CustomCategoryMapper;
import cn.Lee.ssm.category.pojo.CustomCategory;
import cn.Lee.ssm.category.pojo.QueryVo;
import cn.Lee.ssm.product.pojo.Product;
import cn.Lee.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CustomCategoryMapper customCategoryMapper;


    public List<CustomCategory> findAll() {

        return customCategoryMapper.findAll();

    }

    public PageBean<Product> findByPageCid(QueryVo queryVo) {

        int totalCount = customCategoryMapper.findCountCid(queryVo);
        List<Product> list = customCategoryMapper.findByPageCid(queryVo);

        int totalPage = 0;
        if (totalCount % queryVo.getPageSize() == 0) {

            totalPage = totalCount / queryVo.getPageSize();

        } else {

            totalPage = totalCount / queryVo.getPageSize() + 1;
        }

        PageBean<Product> pageBean = new PageBean<Product>();
        pageBean.setPageNumber(queryVo.getPageNumber());
        pageBean.setPageSize(queryVo.getPageSize());
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);

        return pageBean;
    }


    public PageBean<Product> findByPageCsid(QueryVo queryVo) {

        int totalCount = customCategoryMapper.findCountCsid(queryVo);
        List<Product> list = customCategoryMapper.findByPageCsid(queryVo);

        int totalPage = 0;
        if (totalCount % queryVo.getPageSize() == 0) {

            totalPage = totalCount / queryVo.getPageSize();

        } else {

            totalPage = totalCount / queryVo.getPageSize() + 1;
        }

        PageBean<Product> pageBean = new PageBean<Product>();
        pageBean.setPageNumber(queryVo.getPageNumber());
        pageBean.setPageSize(queryVo.getPageSize());
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);

        return pageBean;


    }
}
