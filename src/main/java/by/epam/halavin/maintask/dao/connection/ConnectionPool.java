package by.epam.halavin.maintask.dao.connection;

import by.epam.halavin.maintask.dao.exception.DAOException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final String propPath = "sqlConnection.properties";
    private static final String errorInfo = "The connection is from other pool.";
    private static final String URL = "url";
    private static final String DRIVER = "driver";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String CONNECTION_COUNT = "connectionCount";
    private static ConnectionPool instance;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<Connection> availableConnections = new ArrayDeque<>();
    private Queue<Connection> usedConnections = new ArrayDeque<>();
    private String url;
    private String password;
    private String user;
    private int count;

    /**
     * Connection pool constructor. Initializes pool depending on property {@link ConnectionPool#propPath}
     *
     * @throws DAOException
     */
    private ConnectionPool() throws DAOException {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            properties.load(classLoader.getResourceAsStream(propPath));
            url = properties.getProperty(URL);
            String driver = properties.getProperty(DRIVER);
            password = properties.getProperty(PASSWORD);
            user = properties.getProperty(USER);
            count = Integer.parseInt(properties.getProperty(CONNECTION_COUNT));

            Class.forName(driver);
        } catch (ClassNotFoundException | IOException e) {
            throw new DAOException(e);
        }

        for (int i = 0; i < count; i++) {
            availableConnections.add(makeConnection());
        }
    }

    /**
     * Function to get connection pool reference
     *
     * @return
     * @throws DAOException
     */
    public static synchronized ConnectionPool getInstance() throws DAOException {
        try {
            if (instance == null) {
                instance = new ConnectionPool();
            }
        } catch (DAOException e) {
            throw new DAOException(e);
        }

        return instance;
    }

    /**
     * Function create connection to database
     *
     * @return connection
     * @throws DAOException
     */
    private Connection makeConnection() throws DAOException {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return connection;
    }

    /**
     * Thread-safe method to get free connection. If there isn't free connection function will be blocked and awaited
     * for free connection.
     *
     * @return
     * @throws DAOException
     */
    public Connection getConnection() throws DAOException {
        lock.lock();
        Connection connection = null;

        try {
            if (availableConnections.isEmpty()) {
                condition.await();
                connection = availableConnections.poll();
                availableConnections.remove(connection);
            } else {
                connection = availableConnections.poll();
                availableConnections.remove(connection);
            }

            usedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new DAOException(e);
        } finally {
            lock.unlock();
        }

        return connection;
    }

    /**
     * Thread-safe method to release connection which was taken before
     *
     * @param connection
     * @throws DAOException
     */
    public void releaseConnection(Connection connection) throws DAOException {
        lock.lock();

        if (connection != null) {
            if (usedConnections.remove(connection)) {
                availableConnections.add(connection);
                condition.signal();
            } else {
                lock.unlock();
                throw new DAOException(errorInfo);
            }
        }

        lock.unlock();
    }
}
