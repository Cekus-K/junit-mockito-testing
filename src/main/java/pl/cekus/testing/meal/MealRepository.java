package pl.cekus.testing.meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MealRepository {

    private List<Meal> meals = new ArrayList<>();

    void add(Meal meal) {
        meals.add(meal);
    }

    List<Meal> getAllMeals() {
        return meals;
    }

    void delete(Meal meal) {
        meals.remove(meal);
    }

    List<Meal> findByPrice(int mealPrice) {
        return meals.stream()
                .filter(meal -> meal.getPrice() == mealPrice)
                .collect(Collectors.toList());
    }

    List<Meal> findByName(String mealName, boolean exactMatch) {

        List<Meal> result;

        if (exactMatch) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .collect(Collectors.toList());
        } else {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .collect(Collectors.toList());
        }

        return result;
    }

    List<Meal> findByPrice(int mealPrice, PriceCondition condition) {
        List<Meal> result;

        if (condition.equals(PriceCondition.HIGHER)) {
            result = meals.stream()
                    .filter(meal -> meal.getPrice() > mealPrice)
                    .collect(Collectors.toList());
        } else if (condition.equals(PriceCondition.LOWER)) {
            result = meals.stream()
                    .filter(meal -> meal.getPrice() < mealPrice)
                    .collect(Collectors.toList());
        } else {
            result = meals.stream()
                    .filter(meal -> meal.getPrice() == mealPrice)
                    .collect(Collectors.toList());
        }

        return result;
    }

    List<Meal> findByNameAndPrice(String mealName, boolean exactMatch, int mealPrice, PriceCondition condition) {
        List<Meal> result;

        if (exactMatch && condition.equals(PriceCondition.LOWER)) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .filter(meal -> meal.getPrice() < mealPrice)
                    .collect(Collectors.toList());
        } else if (!exactMatch && condition.equals(PriceCondition.LOWER)) {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .filter(meal -> meal.getPrice() < mealPrice)
                    .collect(Collectors.toList());
        } else if (exactMatch && condition.equals(PriceCondition.HIGHER)) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .filter(meal -> meal.getPrice() > mealPrice)
                    .collect(Collectors.toList());
        } else if (!exactMatch && condition.equals(PriceCondition.HIGHER)) {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .filter(meal -> meal.getPrice() > mealPrice)
                    .collect(Collectors.toList());
        } else if (exactMatch && condition.equals(PriceCondition.EQUAL_TO)) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .filter(meal -> meal.getPrice() == mealPrice)
                    .collect(Collectors.toList());
        } else {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .filter(meal -> meal.getPrice() == mealPrice)
                    .collect(Collectors.toList());
        }

        return result;
    }
}
