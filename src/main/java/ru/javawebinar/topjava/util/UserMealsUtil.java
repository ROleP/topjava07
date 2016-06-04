package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredMealsWithExceeded.forEach(System.out::println);
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        //Creating list for filtered result meals with exceed
        List<UserMealWithExceed> result = new ArrayList<>();
        //map to calculate sum of total callories per day, key = day, value = callories
        Map<LocalDate, Integer> dailyCallories = new HashMap<>();
        //calculating total callories per day - for each meal adding its calls to this days total calls
        //complexity of this iteration is O(N)
        mealList.stream().forEach((e) ->
            dailyCallories.put(e.getDateTime().toLocalDate(), e.getCalories() + dailyCallories.getOrDefault(e.getDateTime().toLocalDate(), 0)));
        //Now filtering meals by specified time and creating new meal with exceed and adding it to result list
        //with proper exceed parameter
        //Complexity of this iteration O(N)
        mealList.stream().filter((e) -> TimeUtil.isBetween(e.getDateTime().toLocalTime(), startTime, endTime)
        ).forEach((e) -> {
            result.add(new UserMealWithExceed(e.getDateTime(), e.getDescription(), e.getCalories(), dailyCallories.get(e.getDateTime().toLocalDate()) > caloriesPerDay));
        });
        //Total complexity O(N)
        return result;
    }
}
