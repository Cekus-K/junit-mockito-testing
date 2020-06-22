package pl.cekus.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Test cases for Cart")
class CartTest {

    @Test
    @DisplayName("Cart is able to process 1000 orders in 100 ms.")
    void simulateLargeOrder() {
        //given
        Cart cart = new Cart();

        //when + then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(), anyOf(
                not(nullValue()),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertThat(cart.getOrders(), allOf(
                not(nullValue()),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertAll("This is a group of assertions for Cart",
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(empty()))),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                () -> assertThat(cart.getOrders().get(0).getMeals(), empty())
        );
    }
}
