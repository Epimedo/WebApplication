package by.epam.halavin.maintask.controller.info;

public enum Urls {
    MAIN("/Taxi/main"), ORDER("/Taxi/order"), ADMIN("/Taxi/admin"), DRIVER("/Taxi/driver"),
    MAIN_JSP("index.jsp"), ORDER_JSP("WEB-INF/view/order.jsp"), DRIVER_JSP("WEB-INF/view/driver.jsp"),
    ADMIN_JSP("WEB-INF/view/adminPage.jsp"), INDEX_JSP("index.jsp");

    private String name;

    Urls(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
