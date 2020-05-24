package cn.Lee.ssm.cart.controller;

import cn.Lee.ssm.cart.pojo.Cart;
import cn.Lee.ssm.cart.pojo.CartItem;
import cn.Lee.ssm.product.pojo.Product;
import cn.Lee.ssm.product.service.ProductService;
import cn.Lee.ssm.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {


    @Autowired
    private ProductService productService;

    @RequestMapping("toCartPage")
    public String toCartPage(HttpSession session) {


        Cart cart = getCart(session);
        return "cart";


    }


    @RequestMapping("addCart")
    public String addCart(Model model, int pid, int count, HttpSession session) {

        User user = (User) session.getAttribute("sessionUser");
        if (user == null) {

            model.addAttribute("msg", "亲，您还没有登录，请先去登录！");
            return "msg";

        }

        Product product = productService.findByPid(pid);
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setProduct(product);
        Cart cart = getCart(session);
        cart.addCart(cartItem);
        model.addAttribute("cartItem", cartItem);
        return "cart";


    }

    @RequestMapping("removeCart")
    public String removeCart(int pid, HttpSession session) {

        Cart cart = getCart(session);
        cart.removeCart(pid);
        return "cart";

    }

    @RequestMapping("clearCart")
    public String clearCart(HttpSession session) {

        Cart cart = getCart(session);
        cart.clearCart();
        return "cart";

    }


    private Cart getCart(HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {

            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;

    }


}
