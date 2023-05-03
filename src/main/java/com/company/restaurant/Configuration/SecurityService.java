package com.company.restaurant.Configuration;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}