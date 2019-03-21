package by.epam.halavin.maintask.bean.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * Usual user class. It defines admin's account.
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class User implements Serializable {
    private int id;
    private String name = "";
    private String surname = "";
    private String email = "";
    private String tel = "";
    private String password = "";

    public User() {
        super();
    }

    /**
     * Function to get field value {@link User#id}
     *
     * @return - user's id
     */
    public int getId() {
        return id;
    }

    /**
     * Id determination {@link User#id}
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Function to get field value {@link User#name}
     *
     * @return - name
     */
    public String getName() {
        return name;
    }

    /**
     * Name determination {@link User#name}
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function to get field value {@link User#surname}
     *
     * @return - surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Surname determination {@link User#surname}
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Function to get field value {@link User#email}
     *
     * @return - user's name
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email determination {@link User#email}
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Function to get field value {@link User#tel}
     *
     * @return - user's phone number
     */
    public String getTel() {
        return tel;
    }

    /**
     * Phone number determination {@link User#tel}
     *
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Function to get field value {@link User#password}
     *
     * @return - user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password determination {@link User#password}
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        boolean bool = false;

        if (this == o) {
            bool = true;
        } else {
            if (o == null || getClass() != o.getClass()) {
                bool = false;
            } else {
                User user = (User) o;
                if (id == user.id &&
                        name.equals(user.name) &&
                        surname.equals(user.surname) &&
                        email.equals(user.email) &&
                        tel.equals(user.tel) &&
                        password.equals(user.password)) {
                    bool = true;
                }
            }
        }

        return bool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, tel, password);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                "}\n";
    }
}
