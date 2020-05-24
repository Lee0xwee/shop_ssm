package cn.Lee.ssm.order.service;


import cn.Lee.ssm.category.pojo.QueryVo;
import cn.Lee.ssm.order.mapper.CustomOrdersMapper;
import cn.Lee.ssm.order.mapper.OrdersMapper;
import cn.Lee.ssm.order.pojo.CustomOrder;
import cn.Lee.ssm.order.pojo.CustomOrderItem;
import cn.Lee.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CustomOrdersMapper customOrdersMapper;

    public void saveOrder(CustomOrder orders) {

        customOrdersMapper.saveOrder(orders);


    }

    public void saveOrderItem(CustomOrderItem orderitem) {

        customOrdersMapper.saveOrderItem(orderitem);
    }

    public PageBean<CustomOrder> findPageByUid(QueryVo queryVo) {


        int totalCount = customOrdersMapper.findCountUid(queryVo);

        List<CustomOrder> list = customOrdersMapper.findByPageUid(queryVo);

        int totalPage = 0;
        if (totalCount % queryVo.getPageSize() == 0) {

            totalPage = totalCount / queryVo.getPageSize();

        } else {

            totalPage = totalCount / queryVo.getPageSize() + 1;
        }

        PageBean<CustomOrder> pageBean = new PageBean<CustomOrder>();
        pageBean.setPageNumber(queryVo.getPageNumber());
        pageBean.setPageSize(queryVo.getPageSize());
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);

        return pageBean;

    }

    public CustomOrder findByOid(int oid) {

        return customOrdersMapper.findByOid(oid);
    }
}
