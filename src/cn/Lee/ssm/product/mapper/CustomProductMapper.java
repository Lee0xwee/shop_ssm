package cn.Lee.ssm.product.mapper;


import cn.Lee.ssm.product.pojo.Product;

import java.util.List;

public interface CustomProductMapper {

    public List<Product> findByHot();

    public List<Product> findByNew();

    List<Product> findByPid(int pid);
}