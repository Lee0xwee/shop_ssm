package cn.Lee.ssm.order.pojo;

import cn.Lee.ssm.product.pojo.Product;

public class CustomOrderItem extends Orderitem {

    private Product product;

    private Orders orders;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }


}
