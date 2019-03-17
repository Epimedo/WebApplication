package by.epam.halavin.maintask.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Car implements Serializable {
    private int id;
    private String number = "";
    private String name = "";
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                Car car = (Car) o;
                if (id == car.id &&
                        Objects.equals(date, car.date) &&
                        number.equals(car.number) &&
                        name.equals(car.name)) {
                    bool = true;
                }
            }
        }

        return bool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, date);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
