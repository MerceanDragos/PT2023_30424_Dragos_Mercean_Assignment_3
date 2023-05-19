package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class for managing database connections.
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger ( ConnectionFactory.class.getName ( ) );
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/orderpt";
    private static final String USER = "orderpt";
    private static final String PASS = "1234";

    private static final ConnectionFactory singleInstance = new ConnectionFactory ( );

    private ConnectionFactory ( ) {
        try {
            Class.forName ( DRIVER );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    private Connection createConnection ( ) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection ( URL, USER, PASS );
        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "An error occurred while trying to connect to the database" );
            e.printStackTrace ( );
        }
        return connection;
    }

    public static Connection getConnection ( ) {
        return singleInstance.createConnection ( );
    }

    public static void close ( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "An error occurred while trying to close the connection" );
            }
        }
    }

    public static void close ( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "An error occurred while trying to close the statement" );
            }
        }
    }

    public static void close ( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "An error occurred while trying to close the ResultSet" );
            }
        }
    }

}
