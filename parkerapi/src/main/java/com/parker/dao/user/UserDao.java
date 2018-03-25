package com.parker.dao.user;

import com.parker.domain.model.User;

public interface UserDao {
    User find(String email, String password);
}
