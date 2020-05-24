package cn.Lee.ssm.cart.pojo;

import cn.Lee.ssm.product.pojo.Product;

import java.math.BigDecimal;

public class CartItem {

    private Product product;   //购买商品
    private int count;          //购买数量
    private double subTotal;    //小计


    //小计方法,使用BigDecimal解决精度丢失问题
    public double getSubTotal() {

        BigDecimal price = new BigDecimal(product.getShopPrice() + "");
        BigDecimal pCount = new BigDecimal(count + "");
        return price.multiply(pCount).doubleValue();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
