package cn.Lee.ssm.order.controller;


import cn.Lee.ssm.cart.pojo.Cart;
import cn.Lee.ssm.cart.pojo.CartItem;
import cn.Lee.ssm.category.pojo.QueryVo;
import cn.Lee.ssm.order.pojo.CustomOrder;
import cn.Lee.ssm.order.pojo.CustomOrderItem;
import cn.Lee.ssm.order.service.OrderService;
import cn.Lee.ssm.user.pojo.User;
import cn.Lee.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("toOrderPage")
    public String toOrderPage(Model model,int oid) {

        CustomOrder orders = orderService.findByOid(oid);
        model.addAttribute("orders", orders);
        return "order";

    }


    @RequestMapping("submit")
    public String submit(Model model, HttpSession session) {


        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {

            model.addAttribute("msg", "亲，您还没有购物，请先去购物！");
            return "msg";

        }
        User user = (User) session.getAttribute("sessionUser");

        CustomOrder orders = new CustomOrder();

        orders.setTotal(cart.getTotal());
        orders.setOrdertime(new Date());
        orders.setState(1);
        orders.setUser(user);

        //将订单信息存储到数据库，并将自动生成的的主键oid返回到orders
        orderService.saveOrder(orders);

        for (CartItem cartItem : cart.getCartItems()) {

            CustomOrderItem orderitem = new CustomOrderItem();
            orderitem.setCount(cartItem.getCount());
            orderitem.setSubtotal(cartItem.getSubTotal());
            orderitem.setProduct(cartItem.getProduct());
            orderitem.setOrders(orders);

            //将订单条目添加到订单中
            orders.getOrderitems().add(orderitem);
            //将订单条目信息存储到数据库
            orderService.saveOrderItem(orderitem);

        }

        cart.clearCart();
        model.addAttribute("orders", orders);

        return "order";

    }


    @RequestMapping("orderList")
    public String orderList(Model model, HttpSession session, QueryVo queryVo) {

        User user = (User) session.getAttribute("sessionUser");
        queryVo.setUid(user.getUid());

        //判断页面是否传入分页参数，若未赋值则自定义参数
        if (queryVo.getPageNumber() == 0) {

            queryVo.setPageNumber(1);
        }
        if (queryVo.getPageSize() == 0) {

            queryVo.setPageSize(8);
        }

        queryVo.setStart((queryVo.getPageNumber() - 1) * queryVo.getPageSize());

        PageBean<CustomOrder> pageBean = orderService.findPageByUid(queryVo);

        model.addAttribute("pageBean", pageBean);
        return "orderList";

    }


}
