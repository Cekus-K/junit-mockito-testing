package pl.cekus.testing;

import java.util.Objects;

public class Meal {
    private String name;
    private int quantity;
    private int price;

    public Meal() {
    }

    public Meal(int price) {
        this.price = price;
    }

    public Meal(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Meal(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscountedPrice(int discount) {
        if (discount > this.price) {
            throw new IllegalArgumentException("Discount cannot be higher than the price!");
        }
        return this.price - discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return price == meal.price &&
                Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int sumPrice() {
        return getPrice() * getQuantity();
    }
}
