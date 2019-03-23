package by.epam.halavin.maintask.bean.user;

import java.util.Objects;

/**
 * Passenger account object
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class Passenger extends User {
    private static final long serialVersionUID = 131;
    private String status = "";
    private double discount;
    private double bonus;

    public Passenger() {
        super();
    }

    /**
     * Function to get field value {@link Passenger#status}
     *
     * @return - status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status determination {@link Passenger#status}
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Function to get field value {@link Passenger#discount}
     *
     * @return - discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Discount determination {@link Passenger#discount}
     *
     * @param discount
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Function to get field value {@link Passenger#bonus}
     *
     * @return - bonus
     */
    public double getBonus() {
        return bonus;
    }

    /**
     * Bonus determination {@link Passenger#bonus}
     *
     * @param bonus
     */
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
