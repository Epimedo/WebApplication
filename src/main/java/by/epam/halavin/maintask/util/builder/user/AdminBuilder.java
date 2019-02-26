package by.epam.halavin.maintask.util.builder.user;

import by.epam.halavin.maintask.bean.user.User;

public class AdminBuilder implements UserBuilder {
    private User admin = new User();

    public AdminBuilder setId(int id) {
        admin.setId(id);
        return this;
    }

    public AdminBuilder setName(String name) {
        admin.setName(name);
        return this;
    }

    public AdminBuilder setSurname(String surname) {
        admin.setSurname(surname);
        return this;
    }

    public AdminBuilder setEmail(String email) {
        admin.setEmail(email);
        return this;
    }

    public AdminBuilder setPassword(String password) {
        admin.setPassword(password);
        return this;
    }

    public AdminBuilder setTel(String tel) {
        admin.setTel(tel);
        return this;
    }

    @Override
    public User getObject() {
        return admin;
    }

    @Override
    public void reset() {
        admin = new User();
    }
}
