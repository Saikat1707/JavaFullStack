package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.model.Cart;
import com.ecom.ecomApp.model.CartItems;
import com.ecom.ecomApp.model.Customers;
import com.ecom.ecomApp.model.Products;
import com.ecom.ecomApp.repo.CartItemRepo;
import com.ecom.ecomApp.repo.CartRepo;
import com.ecom.ecomApp.repo.CustomerRepo;
import com.ecom.ecomApp.repo.ProductRepo;
import com.ecom.ecomApp.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    public CartServiceImpl(CartRepo cartRepo,CartItemRepo cartItemRepo
                           , CustomerRepo customerRepo, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }
    @Override
    public Cart getCartByCustomerId(Long customerId) {
        Customers customer = getCustomer(customerId);
        return customer.getCart();
    }


    @Override
    @Transactional
    public Cart addProductToCart(Long customerId, Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity should be greater than 0"
            );
        }

        Products product = getProduct(productId);
        Customers customer = getCustomer(customerId);

        Cart cart = customer.getCart();

        if (cart == null) {
            cart = cartRepo.save(new Cart());
            customer.setCart(cart);
            customerRepo.save(customer);
        }

        List<CartItems> cartItemsList = cart.getCartItemsList();
        CartItems exists = null;

        for (CartItems item : cartItemsList) {
            if (item.getProduct().getId().equals(product.getId())) {
                exists = item;
                break;
            }
        }

        if (exists != null) {
            int newQuantity = exists.getQuantity() + quantity;

            if (product.getTotalStock() < newQuantity) {
                throw new RuntimeException("Insufficient stock");
            }

            exists.setQuantity(newQuantity);
            exists.setProductAvailable(true);

        } else {
            if (product.getTotalStock() < quantity) {
                throw new RuntimeException("Insufficient stock");
            }

            CartItems newItem = new CartItems();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setProductAvailable(true);

            cartItemsList.add(newItem);
        }

        return cartRepo.save(cart);
    }

    @Override
    @Transactional
    public Cart updateCartItemQuantity(
            Long customerId,
            Long cartItemId,
            int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity should be greater than 0"
            );
        }

        Customers customer = getCustomer(customerId);

        Cart cart = customer.getCart();

        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        CartItems cartItem = null;

        for (CartItems item : cart.getCartItemsList()) {
            if (item.getId().equals(cartItemId)) {
                cartItem = item;
                break;
            }
        }

        if (cartItem == null) {
            throw new RuntimeException(
                    "Cart item not found with id: " + cartItemId
            );
        }

        Products product = cartItem.getProduct();

        if (product.getTotalStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        cartItem.setQuantity(quantity);
        cartItem.setProductAvailable(true);

        return cartRepo.save(cart);
    }

    @Override
    @Transactional
    public void removeProductFromCart(Long customerId, Long cartItemId) {
        Customers customer = getCustomer(customerId);
        Cart cart = customer.getCart();

        if(cart == null){
            throw new RuntimeException("Cart not found");
        }

        CartItems cartItem = null;
        for(CartItems item : cart.getCartItemsList()){
            if(item.getId().equals(cartItemId)){
                cartItem = item;
            }
        }

        if(cartItem == null){
            throw new RuntimeException("Cart item not found with id: " + cartItemId);
        }

        cartItemRepo.delete(cartItem);
        cart.getCartItemsList().remove(cartItem);
        cartRepo.save(cart);
    }

    @Override
    public void clearCart(Long customerId) {
        Customers customer = getCustomer(customerId);
        Cart cart = customer.getCart();

        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        cartItemRepo.deleteAll(cart.getCartItemsList());
        cart.getCartItemsList().clear();

        cartRepo.save(cart);
    }

    @Override
    public boolean isProductInCart(Long customerId, Long productId) {
        Customers customer = getCustomer(customerId);
        Cart cart = customer.getCart();

        if(cart == null){
            throw new RuntimeException("Cart not found");
        }

        for(CartItems item : cart.getCartItemsList()){
            if(item.getProduct() != null
                && item.getId().equals(productId)){
                return true;
            }
        }
        return false;
    }

    private Customers getCustomer(Long id){
        Customers customer = customerRepo.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Customer not found!")
                );
        return customer;
    }
    private Products getProduct(Long id){
        Products product = productRepo.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Product not found!")
                );
        return product;
    }
}
