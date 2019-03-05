package by.epam.halavin.maintask.dao.connection;

import by.epam.halavin.maintask.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool {
    public static final Logger log = LogManager.getLogger(ConnectionPool.class);
    private static final String propPath = "sqlConnection.properties";
    private static final String errorInfo = "The connection is from other pool.";
    private static ConnectionPool instance;

    static {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream(propPath));
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            String password = properties.getProperty("password");
            String user = properties.getProperty("user");
            String count = properties.getProperty("connectionCount");

            instance = new ConnectionPool(url, user, password, driver, Integer.parseInt(count));
        } catch (IOException e) {
            log.error(e);
        }
    }

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
            log.info(e);
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
            log.info(e);
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
                throw new DAOException(errorInfo);
            }
        }
    }
}
