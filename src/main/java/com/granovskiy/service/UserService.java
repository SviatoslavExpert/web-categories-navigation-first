package com.granovskiy.service;

import com.granovskiy.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> authorize(User user);

    /*  Optional - a container object which may or may not contain a non-null value. Can contain null.
     * If a value is present, isPresent() will return true and get() will return the value.
     */
}
