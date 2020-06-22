package pl.cekus.testing.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void creatingConstructorWithXLessThan0ShouldThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-10, 1));
    }

    @Test
    void creatingConstructorWithYLessThan0ShouldThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(1, -15));
    }

    @Test
    void creatingConstructorWithXGraterThan100ShouldThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(150, 1));
    }

    @Test
    void creatingConstructorWithYGraterThan100ShouldThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(10, 120));
    }
}
