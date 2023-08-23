package com.yerin.book.springbootwebservice.config.auth;

import com.yerin.book.springbootwebservice.domain.user.User;

import java.io.Serializable;

public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {

        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}