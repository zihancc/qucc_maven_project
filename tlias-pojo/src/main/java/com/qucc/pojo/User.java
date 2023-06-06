package com.qucc.pojo;

public class User {
    private String name;
    private Short age;
    private String username;
    private Short sex;
    private String address;
    private String password;
    private String email;
    private int order;

    public User(String name, Short age, String username, Short sex, String address, String password, String email) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.sex = sex;
        this.address = address;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
