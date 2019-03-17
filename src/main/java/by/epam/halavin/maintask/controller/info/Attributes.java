package by.epam.halavin.maintask.controller.info;

public enum Attributes {
    ACCOUNT("account"), COMMAND("command"), LOCAL("local"), ORDER("order"),
    ADMIN_MANAGER("adminManager"), ACCOUNT_TYPE("accountType"), EMAIL("email"),
    PASSWORD("password"), RESPONSE_STATUS("responseStatus"), SIGNED("signed"),
    SIGNED_DRIVER("signedDriver"), SIGNED_ADMIN("signedAdmin"), DRIVER_STATUS("driverStatus"),
    ACTIVATE("activate"), CARS("cars"), PASSENGERS("passengers"), DRIVERS("drivers"),
    INCORRECT_PASSWORD("incorrectPassword"), INCORRECT_EMAIL("incorrectEmail"), SUCCESS("success"),
    NAME("name"), SURNAME("surname"), TEL("tel"), BAN("ban"), UNBAN("unban"),
    BLOCK_NUMBER("blockNumber"), REGISTER_STATUS("registerStatus"), ACCOUNT_BAN("accountBan"),
    CAR_CHECKUP_END("carChekupEnd"), ORDER_STATUS("orderStatus"),
    NO_AVAILABLE_DRIVERS("noAvailableDrivers"), CURRENT_POSITION("currentPosition"), WAIT("wait"),
    ACCEPT("accept"), ON_WAY("onWay"), RESET("reset"), DECLINE("decline"),
    NEXT_POSITION("nextPosition"), SECOND_PASSWORD("secondPassword"), ACCOUNT_STATUS("accountStatus"),
    SIGNED_PASSENGER("signedPassenger"), PREV_REQUEST("prev_request"), ORDERS("orders"), ID("id"),
    STATUS("status"), DISCOUNT("discount"), BONUS("bonus"), CUR_PASS_POSITION("currentPassPos"),
    CUR_DRIVER_POSITION("currentDriverPos"), ORDER_BLOCKS("orderBlocks"), FOCUS_TABLE("focusTable"),
    DRIVER_TABLE("driverTable"), USER_TABLE("userTable"),ORDER_TABLE("orderTable"),CAR("car"),
    CAR_NUMBER("carNumber"),CHECKUP_END("checkupEnd"),SUCCESS_SIGNED("successSigned");

    private String name;

    Attributes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
