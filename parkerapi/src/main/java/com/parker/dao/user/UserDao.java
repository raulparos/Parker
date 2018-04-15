package com.parker.dao.user;

import com.parker.domain.model.User;

public interface UserDao {
    void update (User user);

    User find(String email, String password);

    User find(String email);
}
