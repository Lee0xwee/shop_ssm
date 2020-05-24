package cn.Lee.ssm.category.mapper;

import cn.Lee.ssm.category.pojo.CustomCategory;
import cn.Lee.ssm.category.pojo.QueryVo;
import cn.Lee.ssm.product.pojo.Product;

import java.util.List;

public interface CustomCategoryMapper {


    List<CustomCategory> findAll();

    List<Product> findByPageCid(QueryVo queryVo);

    int findCountCid(QueryVo queryVo);

    int findCountCsid(QueryVo queryVo);

    List<Product> findByPageCsid(QueryVo queryVo);
}