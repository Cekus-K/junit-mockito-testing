package pl.cekus.testing.order;

import pl.cekus.testing.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private OrderStatus orderStatus;
    private List<Meal> meals = new ArrayList<>();

    public void addMealToOrder(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal) {
        this.meals.remove(meal);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    void cancel() {
        this.meals.clear();
    }

    int totalPrice() {
        int sum = this.meals
                .stream()
                .mapToInt(Meal::getPrice)
                .sum();
        if (sum < 0) {
            throw new IllegalStateException("Price limit exceeded!");
        } else {
            return sum;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
