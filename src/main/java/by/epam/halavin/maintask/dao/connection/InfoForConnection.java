package by.epam.halavin.maintask.dao.connection;

public enum InfoForConnection {
    USER("root"), PASSWROD("1111"), URL("jdbc:mysql://localhost:3306/mydb?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"),
    DRIVER("com.mysql.jdbc.Driver"), CONNECTION_COUNT("20"), ERRO_INFO("The connection is from other pool.");

    private String str;

    InfoForConnection(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
