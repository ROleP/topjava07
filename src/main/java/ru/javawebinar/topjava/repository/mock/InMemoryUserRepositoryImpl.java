package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getName);

    {
        this.save(new User(1, "admin", "_rolep_@mail.ru", "admin", Role.ROLE_ADMIN));
        this.save(new User(2, "user", "user@user.com", "user", Role.ROLE_USER));
        this.save(new User(3, "tester", "tester@mail.ru", "tester", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        Objects.requireNonNull(user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return repository.values().stream().sorted(USER_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        Objects.requireNonNull(email);
        LOG.info("getByEmail " + email);
        return repository.values().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);

    }
}