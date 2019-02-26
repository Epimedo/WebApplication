package by.epam.halavin.maintask.bean.user;

import java.util.Objects;

public class Passenger extends User {
    private String status = "";
    private double discount;
    private double bonus;

    public Passenger() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
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
                Passenger passenger = (Passenger) o;

                if (super.equals(o) && status.equals(passenger.status) && discount == passenger.discount
                        && bonus == passenger.bonus) {
                    bool = true;
                }
            }
        }

        return bool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "status='" + status + '\'' +
                ", discount=" + discount +
                ", bonus='" + bonus + '\'' +
                "} \n";
    }
}
