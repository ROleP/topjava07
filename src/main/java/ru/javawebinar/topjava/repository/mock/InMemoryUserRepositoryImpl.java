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

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        this.save(new User(1, "admin", "_rolep_@mail.ru", "admin", Role.ROLE_ADMIN));
        this.save(new User(2, "user", "user@user.com", "user", Role.ROLE_USER));
        this.save(new User(3, "tester", "tester@mail.ru", "tester", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        if (!repository.containsKey(id))
            return false;
        else
            repository.remove(id);
        return true;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (!(repository.containsKey(user.getId()) && repository.get(user.getId()).getName().equals(user.getName()))) {
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

        List<User> users = new ArrayList(repository.values());

        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));

        return users;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        for (User user : repository.values()) {
            if (user.getEmail().equals(email))
                return user;
        }

        Optional<User> result = repository.values().stream().filter(e -> e.getEmail().equals(email)).findFirst();
        return result.get();
    }
}
