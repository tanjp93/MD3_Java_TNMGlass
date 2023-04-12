package projectBase_00.service.cart;

import projectBase_00.config.Config;
import projectBase_00.controller.ProductController;
import projectBase_00.model.cart.Cart;
import projectBase_00.model.cart.OrderProduct;
import projectBase_00.model.product.Product;
import projectBase_00.model.user.User;
import projectBase_00.service.user.UserServiceIMPL;
import projectBase_00.view.ProductView;

import java.util.List;

public class CartService implements ICartService {
    List<Cart> listCart = new Config<Cart>().readFromFile(Config.PATH_CART);
    User userCurrent = new UserServiceIMPL().getCurrentUser();

    @Override
    public List<Cart> findAll() {
        return listCart;
    }

    @Override
    public void save(Cart cart) {
        if (userCurrent != null) {
            if (!checkUserInListCart(cart.getUser())) {
                //Create a new cart
                listCart.add(cart);
            } else {
                //Update cart by User
                int index = listCart.indexOf(findByUser(cart.getUser()));
                listCart.set(index, cart);
            }
        }
        new Config<Cart>().writeToFile(Config.PATH_CART, listCart);
    }

    @Override
    public Cart findById(int id) {
        if (listCart.size() != 0) {
            return listCart.get(id);
        } else return null;
    }

    @Override
    public void deleteById(int id) {
        if (listCart.size() != 0) {
            listCart.remove(id);
        }
        new Config<Cart>().writeToFile(Config.PATH_CART, listCart);
    }

    public boolean checkUserInListCart(User user) {
        boolean check = false;
        if (listCart.size() != 0) {
            for (Cart cart : listCart) {
                if (cart.getUser().getId() == user.getId()) {
                    check = true;
                } else check = false;
            }
        } else check = false;
        return check;
    }

    @Override
    public Cart findByUser(User user) {
        if (listCart.size() != 0) {
            for (int i = 0; i < listCart.size(); i++) {
                if (listCart.get(i).getUser().getId() == user.getId()) {
                    return listCart.get(i);
                }
            }
        }
        return null;
    }

    public void deleteProductById(User user, int idProduct) {
        ProductController productController=new ProductController();
        int removeQuantity = 0;
        Product product = new ProductView().findProductById(idProduct);
        if (listCart.size() != 0) {
            Cart cart = findByUser(user);
            int index = listCart.indexOf(cart);
            List<OrderProduct> orderProductList = cart.getListProductCart();
            for (OrderProduct orderProduct : orderProductList) {
                if (orderProduct.getProduct().getId() == idProduct) {
                    orderProductList.remove(orderProduct);
                    removeQuantity = orderProduct.getQuantity();
                    break;
                }
                ;
            }
            cart.setListProductCart(orderProductList);
            listCart.set(index, cart);
            //set stoke
            product.setStoke(product.getStoke() + removeQuantity);
            productController.updateProduct(product);
        }
        new Config<Cart>().writeToFile(Config.PATH_CART, listCart);
    }
}