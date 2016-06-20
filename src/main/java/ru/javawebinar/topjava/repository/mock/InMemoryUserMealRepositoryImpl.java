package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Set<Integer>> userMealRelation = new ConcurrentHashMap<>();

    {
        userMealRelation.put(1, new HashSet<>());
        userMealRelation.put(2, new HashSet<>());
        userMealRelation.put(3, new HashSet<>());
        UserMealsUtil.MEAL_LIST.forEach(e -> save(e, 1));
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        userMealRelation.get(userId).add(userMeal.getId());
        return userMeal;
    }

    @Override
    public void delete(int id, int userId) {
        if (userMealRelation.get(userId).remove(id))
            repository.remove(id);
    }

    @Override
    public UserMeal get(int id, int userId) {
        if (userMealRelation.getOrDefault(userId, new HashSet<Integer>()).contains(id))
            return repository.get(id);
        return null;
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        List<UserMeal> result = new ArrayList<>();
        userMealRelation.getOrDefault(userId, new HashSet<>()).forEach(e -> result.add(repository.get(e)));
        result.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        return result;
    }
}

