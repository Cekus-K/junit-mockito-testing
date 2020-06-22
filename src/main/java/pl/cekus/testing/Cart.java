package pl.cekus.testing;

import java.util.ArrayList;
import java.util.List;

class Cart {
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    void addOrderToCart(Order order) {
        this.orders.add(order);
    }

    void clearCart() {
        this.orders.clear();
    }

    void simulateLargeOrder() {
        for(int i = 0; i<1000; i++) {
            Meal meal = new Meal("Hamburger nr. " + i, i%10);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }

        System.out.println("Cart size: " + orders.size());
        clearCart();
    }
}