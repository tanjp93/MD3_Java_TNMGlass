package projectBase_00.controller;

import projectBase_00.model.cart.Cart;
import projectBase_00.model.user.User;
import projectBase_00.service.cart.CartService;
import projectBase_00.service.cart.ICartService;

import java.util.List;

public class CartController extends CartService {
    ICartService cartService=new CartService();

    public CartController() {
    }
    public List<Cart> getListCart(){
        return cartService.findAll();
    }
    public void addToCart(Cart cart){
        cartService.save(cart);
    }
    public void update(Cart cart){
        cartService.save(cart);
    }
    public void deleteCartById(int id){
        cartService.deleteById(id);
    }
    public Cart getUserCart(User user){
      return   cartService.findByUser(user);
    }

    public void deleteProductById(User user, int id){
        cartService.deleteProductById(user, id);
    }
}
