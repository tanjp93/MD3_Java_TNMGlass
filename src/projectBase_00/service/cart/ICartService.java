package projectBase_00.service.cart;

import projectBase_00.model.cart.Cart;
import projectBase_00.model.user.User;
import projectBase_00.service.IGenericService;

public interface ICartService extends IGenericService<Cart> {
    public Cart findByUser(User user);
}
