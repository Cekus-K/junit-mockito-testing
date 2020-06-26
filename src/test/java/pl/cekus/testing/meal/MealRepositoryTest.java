package pl.cekus.testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MealRepositoryTest {

    private MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp() {
        mealRepository.getAllMeals().clear();
    }

    @Test
    void shouldBeAbleToAddMealToRepository() {
        //given
        Meal meal = new Meal("Pizza", 12);

        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository() {
        //given
        Meal meal = new Meal("Pizza", 12);
        mealRepository.add(meal);

        //when
        mealRepository.delete(meal);

        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));
    }

    @Test
    void shouldBeAbleToFindMealByPrice() {
        //given
        Meal meal = new Meal("Pizza", 12);
        mealRepository.add(meal);

        //when
        List<Meal> results = mealRepository.findByPrice(12);

        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeAbleToFindMealByExactName() {
        //given
        Meal meal1 = new Meal("Pizza", 12);
        Meal meal2 = new Meal("Pi", 12);
        mealRepository.add(meal1);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("Pizza", true);

        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void ShouldBeAbleToFindMealByStarringLetters() {
        //given
        Meal meal1 = new Meal("Pizza", 12);
        Meal meal2 = new Meal("Pi", 12);
        mealRepository.add(meal1);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("P", false);

        //then
        assertThat(results.size(), is(2));
    }

    @Test
    void ShouldBeAbleToFindMealByPriceWithPriceCondition() {
        //given
        Meal meal1 = new Meal("Pizza", 12);
        Meal meal2 = new Meal("Burrito", 8);
        Meal meal3 = new Meal("Pasta", 6);
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when + then
        assertThat(mealRepository.findByPrice(7, PriceCondition.HIGHER).size(), is(2));
        assertThat(mealRepository.findByPrice(13, PriceCondition.LOWER).size(), is(3));
        assertThat(mealRepository.findByPrice(8, PriceCondition.EQUAL_TO).size(), is(1));
    }

    @Test
    void ShouldBeAbleToFindMealByLowerThanPrice() {
        //given
        Meal meal1 = new Meal("Pizza", 12);
        Meal meal2 = new Meal("Burrito", 8);
        Meal meal3 = new Meal("Pasta", 6);
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.findByPrice(8, PriceCondition.LOWER);

        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void ShouldBeAbleToFindMealByEqualToPrice() {
        //given
        Meal meal1 = new Meal("Pizza", 12);
        Meal meal2 = new Meal("Burrito", 8);
        Meal meal3 = new Meal("Pasta", 6);
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.findByPrice(12, PriceCondition.EQUAL_TO);

        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeAbleToFindMealByPriceConditionAndStarringLetter() {
        //given
        Meal meal1 = new Meal("Pizza", 12);
        Meal meal2 = new Meal("Burrito", 8);
        Meal meal3 = new Meal("Pasta", 6);
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> result1 = mealRepository.findByNameAndPrice("P", false, 10, PriceCondition.LOWER);
        List<Meal> result2 = mealRepository.findByNameAndPrice("Pizza", true, 10, PriceCondition.LOWER);
        List<Meal> result3 = mealRepository.findByNameAndPrice("P", false, 5, PriceCondition.HIGHER);
        List<Meal> result4 = mealRepository.findByNameAndPrice("Burrito", true, 8, PriceCondition.EQUAL_TO);
        List<Meal> result5 = mealRepository.findByNameAndPrice("", false, 0, PriceCondition.HIGHER);

        //then
        assertThat(result1.size(), is(1));
        assertThat(result2.size(), is(0));
        assertThat(result3.size(), is(2));
        assertThat(result4.size(), is(1));
        assertThat(result5.size(), is(3));
        assertThat(result5, contains(meal1, meal2, meal3));
    }
}
