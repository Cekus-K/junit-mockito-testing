package pl.cekus.testing.homework;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void coordinatesShouldNotCanMoveWhenXPlusYGraterThanFuel() {
        Unit unit = new Unit(new Coordinates(50, 50), 120, 120);

        //when + then
        assertThrows(IllegalStateException.class, () -> unit.move(60, 95));
        assertThrows(IllegalStateException.class, () -> unit.move(65, 65));
    }

    @Test
    void unitShouldLoseFuelWhenMoving() {
        //given
        Unit unit = new Unit(new Coordinates(10, 10), 30, 40);

        //when
        unit.move(5, 10);

        //then
        assertThat(unit.getFuel(), is(15));
    }

    @Test
    void coordinatesShouldBeIncreaseAfterMove() {
        //given
        Unit unit = new Unit(new Coordinates(50, 50), 120, 120);

        //when
        Coordinates move = unit.move(5, 10);

        //then
        assertThat(move, equalTo(new Coordinates(55, 60)));
    }

    @Test
    void tankUpShouldIncreaseFuelBetween0And10() {
        //given
        Unit unit = new Unit(new Coordinates(50, 50), 120, 120);

        //when
        unit.tankUp();

        //then
        assertThat(unit.getFuel(), lessThanOrEqualTo(120));
        assertThat(unit.getFuel(), greaterThanOrEqualTo(110));
    }

    @Test
    void loadCargoShouldThrowExceptionWhenWeightIsGraterThanMaxWeight() {
        //given
        Unit unit = new Unit(new Coordinates(50, 50), 120, 50);

        //when
        Cargo cargo = new Cargo("heavy cargo", 60);

        //when + then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo));
    }

    @Test
    void unloadCargoShouldDecreaseCurrentCargoWeight() {
        //given
        Unit unit = new Unit(new Coordinates(50, 50), 120, 50);

        Cargo cargo1 = new Cargo("sample cargo", 20);
        Cargo cargo2 = new Cargo("sample cargo", 15);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        //when
        unit.unloadCargo(cargo1);

        //then
        assertThat(unit.getLoad(), is(15));
    }

    @Test
    void unloadAllCargoShouldDecreaseCurrentCargoWeightToZero() {
        //given
        Unit unit = new Unit(new Coordinates(50, 50), 120, 50);

        Cargo cargo1 = new Cargo("sample cargo", 20);
        Cargo cargo2 = new Cargo("sample cargo", 15);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        //when
        unit.unloadAllCargo();

        //then
        assertThat(unit.getLoad(), is(0));
    }
}
