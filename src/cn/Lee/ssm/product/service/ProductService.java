package cn.Lee.ssm.product.service;


import cn.Lee.ssm.product.mapper.CustomProductMapper;
import cn.Lee.ssm.product.mapper.ProductMapper;
import cn.Lee.ssm.product.pojo.Product;
import cn.Lee.ssm.product.pojo.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CustomProductMapper customProductMapper;

    public List<Product> findByHot() {

        return customProductMapper.findByHot();


    }

    public List<Product> findByNew() {

        return customProductMapper.findByNew();

    }

    public Product findByPid(int pid) {


        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Product> products = productMapper.selectByExample(productExample);

//       List<Product> products = customProductMapper.findByPid(pid);
        if (products != null && products.size() > 0) {

            return products.get(0);
        }

        return null;

    }
}
