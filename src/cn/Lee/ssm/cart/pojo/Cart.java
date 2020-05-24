package cn.Lee.ssm.cart.pojo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {


    //商品id为键，商品条目为值，构建map集合
    Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();
    private double total;   //总计

    //添加购物车
    public void addCart(CartItem cartItem) {


        int pid = cartItem.getProduct().getPid();

        //判断购物车中是否有该商品
        if (map.containsKey(pid)) {

            //若存在该商品，将设置原商品数量=原数量+新数量
            CartItem _cartItem = map.get(pid);
            _cartItem.setCount(cartItem.getCount() + _cartItem.getCount());

        } else {

            //若不存在该商品则添加到map集合
            map.put(pid, cartItem);
        }

        total += cartItem.getSubTotal();


    }


    //移除购物车
    public void removeCart(int pid) {

        CartItem cartItem = map.remove(pid);
        total -= cartItem.getSubTotal();


    }

    //清空购物车
    public void clearCart() {

        map.clear();
        total = 0;
    }

    public Collection<CartItem> getCartItems() {

        return map.values();
    }


    //总计方法
    public double getTotal() {

        //总计=所有商品的小计之和
        BigDecimal total = new BigDecimal("0");
        for (CartItem cartItem : map.values()) {

            BigDecimal subTotal = new BigDecimal(cartItem.getSubTotal() + "");
            total = total.add(subTotal);
        }
        return total.doubleValue();
    }
}
