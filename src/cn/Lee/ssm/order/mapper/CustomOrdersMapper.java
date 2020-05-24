package cn.Lee.ssm.order.mapper;


import cn.Lee.ssm.category.pojo.QueryVo;
import cn.Lee.ssm.order.pojo.CustomOrder;
import cn.Lee.ssm.order.pojo.CustomOrderItem;

import java.util.List;

public interface CustomOrdersMapper {


    void saveOrder(CustomOrder orders);

    void saveOrderItem(CustomOrderItem orderitem);

    int findCountUid(QueryVo queryVo);

    List<CustomOrder> findByPageUid(QueryVo queryVo);

    CustomOrder findByOid(int oid);
}