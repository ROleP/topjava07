package ru.javawebinar.topjava.repository.mock;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;

/**
 * Created by rolep on 18/06/16.
 */
@Repository
public class MockUserRepository implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(MockUserRepository.class);

    @Override
    public User save(User user) {
        LOG.info("Save " + user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("Delete " + id);
        return true;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
