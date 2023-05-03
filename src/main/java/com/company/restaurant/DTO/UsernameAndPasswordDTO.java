package com.company.restaurant.DTO;

public class UsernameAndPasswordDTO {
    private final String username;
    private final String password;

    public UsernameAndPasswordDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
