import java.util.ArrayList;
import java.util.List;

interface MealPlan {
    String label();
}

class VegetarianMeal implements MealPlan {
    public String label() {
        return "Vegetarian";
    }
}

class VeganMeal implements MealPlan {
    public String label() {
        return "Vegan";
    }
}

class KetoMeal implements MealPlan {
    public String label() {
        return "Keto";
    }
}

class HighProteinMeal implements MealPlan {
    public String label() {
        return "High Protein";
    }
}

class Meal<T extends MealPlan> {
    private T plan;

    public Meal(T plan) {
        this.plan = plan;
    }

    public T getPlan() {
        return plan;
    }
}

class MealGenerator {
    public static <T extends MealPlan> Meal<T> generate(T plan) {
        return new Meal<>(plan);
    }
}

public class PersonalizedMealPlanGenerator {
    public static void main(String[] args) {

        Meal<VegetarianMeal> vegMeal =
                MealGenerator.generate(new VegetarianMeal());

        Meal<VeganMeal> veganMeal =
                MealGenerator.generate(new VeganMeal());

        Meal<KetoMeal> ketoMeal =
                MealGenerator.generate(new KetoMeal());

        Meal<HighProteinMeal> proteinMeal =
                MealGenerator.generate(new HighProteinMeal());

        List<Meal<? extends MealPlan>> meals = new ArrayList<>();
        meals.add(vegMeal);
        meals.add(veganMeal);
        meals.add(ketoMeal);
        meals.add(proteinMeal);

        for (Meal<? extends MealPlan> meal : meals) {
            System.out.println(meal.getPlan().label());
        }
    }
}