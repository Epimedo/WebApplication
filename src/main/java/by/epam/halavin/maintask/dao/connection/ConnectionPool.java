package by.epam.halavin.maintask.dao.connection;

import by.epam.halavin.maintask.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
    private static ConnectionPool instance = new ConnectionPool(InfoForConnection.URL.getStr(),
            InfoForConnection.USER.getStr(), InfoForConnection.PASSWROD.getStr(),
            InfoForConnection.DRIVER.getStr(), Integer.parseInt(InfoForConnection.CONNECTION_COUNT.getStr()));

    private Vector<Connection> availableConnections = new Vector<Connection>();
    private Vector<Connection> usedConnections = new Vector<Connection>();
    private String url;
    private String password;
    private String user;

    private ConnectionPool(String url, String user, String password, String driver, int connectionCount) {
        this.url = url;
        this.user = user;
        this.password = password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        for (int i = 0; i < connectionCount; i++) {
            availableConnections.addElement(makeConnection());
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private Connection makeConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;

        if (availableConnections.isEmpty()) {
            connection = makeConnection();
        } else {
            connection = availableConnections.lastElement();
            availableConnections.remove(connection);
        }

        usedConnections.addElement(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) throws DAOException {
        if (connection != null) {
            if (usedConnections.remove(connection)) {
                availableConnections.addElement(connection);
            } else {
                throw new DAOException(InfoForConnection.ERRO_INFO.getStr());
            }
        }
    }
}
