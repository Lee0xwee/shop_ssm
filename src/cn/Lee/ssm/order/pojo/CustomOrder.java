package cn.Lee.ssm.order.pojo;

import cn.Lee.ssm.user.pojo.User;

import java.util.LinkedList;
import java.util.List;

public class CustomOrder extends Orders {

    private List<CustomOrderItem> orderitems = new LinkedList<CustomOrderItem>();

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CustomOrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<CustomOrderItem> orderitems) {
        this.orderitems = orderitems;
    }
}
