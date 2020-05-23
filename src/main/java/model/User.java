package model;

import model.enums.UserRole;

import java.util.ArrayList;

public class User {

    private Integer id;
    private UserRole userRole;
    private String email;
    private String password;
    private String company;
    private Integer age;
    private ArrayList<Action> actions;
    private Subscription subscription;
    private Configuration configuration;

    public User(Integer id, UserRole userRole, String email, String password, String company, Integer age) {
        this.id = id;
        this.userRole = userRole;
        this.email = email;
        this.password = password;
        this.company = company;
        this.age = age;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
