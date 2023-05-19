package DAO;

import Connection.ConnectionFactory;
import Models.Order;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object class for Order table.
 * Implements 5 of the most common sql operations.
 */
public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger ( OrderDAO.class.getName ( ) );

    private static final String findAllStatementString = "SELECT * FROM \"order\"";
    private static final String findStatementString = "SELECT * FROM \"order\" where order_id = ?";
    private static final String insertStatementString = "INSERT INTO \"order\" ( order_quantity, order_address, order_product_id, order_client_id ) VALUES ( ?, ?, ?, ? )";
    private static final String updateStatementString = "UPDATE \"order\" SET order_quantity = ?, order_address = ?, order_product_id = ?, order_client_id = ? WHERE order_id = ?";
    private static final String deleteStatementString = "DELETE FROM \"order\" WHERE order_id = ?";

    public static Order[] findAll ( ) {
        Order[] found = new Order[ 1 ];

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findAllStatement = null;
        ResultSet resultSet = null;
        try {
            findAllStatement = dataBaseConnection.prepareStatement ( findAllStatementString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
            resultSet = findAllStatement.executeQuery ( );

            int i;
            for ( i = 0; resultSet.next ( ); i++ ) ;
            if ( i > 0 ) {
                found = new Order[ i ];
                i = 0;
                resultSet.beforeFirst ( );

                while ( resultSet.next ( ) ) {
                    found[ i ] = new Order ( resultSet.getInt ( "order_id" ), resultSet.getInt ( "order_quantity" ), resultSet.getString ( "order_address" ), resultSet.getInt ( "order_product_id" ), resultSet.getInt ( "order_client_id" ) );
                    i++;
                }
            }
        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "OrderDAO:findAll " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findAllStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }

        return found;
    }

    public static Order findByID ( int ID ) {
        Order found = null;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dataBaseConnection.prepareStatement ( findStatementString );
            findStatement.setLong ( 1, ID );
            resultSet = findStatement.executeQuery ( );

            if ( resultSet.next ( ) )
                found = new Order ( resultSet.getInt ( "order_id" ), resultSet.getInt ( "order_quantity" ), resultSet.getString ( "order_address" ), resultSet.getInt ( "order_product_id" ), resultSet.getInt ( "order_client_id" ) );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "OrderDAO:findByID " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return found;
    }

    public static int insert ( Order order ) {
        int insertedID = -1;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;
        try {
            insertStatement = dataBaseConnection.prepareStatement ( insertStatementString, Statement.RETURN_GENERATED_KEYS );
            insertStatement.setInt ( 1, order.getQuantity ( ) );
            insertStatement.setString ( 2, order.getAddress ( ) );
            insertStatement.setInt ( 3, order.getProductID ( ) );
            insertStatement.setInt ( 4, order.getClientID ( ) );
            insertStatement.executeUpdate ( );

            resultSet = insertStatement.getGeneratedKeys ( );
            resultSet.next ( );
            insertedID = resultSet.getInt ( "order_id" );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "OrderDAO:insert " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( insertStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return insertedID;
    }

    public static void update ( Order order ) throws NoSuchElementException {
        Order toBeUpdated = OrderDAO.findByID ( order.getID ( ) );

        if ( toBeUpdated != null ) {

            Connection dataBaseConnection = ConnectionFactory.getConnection ( );
            PreparedStatement updateStatement = null;

            try {
                updateStatement = dataBaseConnection.prepareStatement ( updateStatementString );
                updateStatement.setInt ( 1, order.getQuantity ( ) );
                updateStatement.setString ( 2, order.getAddress ( ) );
                updateStatement.setInt ( 3, order.getProductID ( ) );
                updateStatement.setInt ( 4, order.getClientID ( ) );
                updateStatement.setInt ( 5, order.getID ( ) );
                updateStatement.executeUpdate ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "OrderDAO:update " + e.getMessage ( ) );
            } finally {
                ConnectionFactory.close ( updateStatement );
                ConnectionFactory.close ( dataBaseConnection );
            }
        } else
            throw new NoSuchElementException ( "Order with id = " + order.getID ( ) + " not found" );
    }

    public static void delete ( Order order ) {
        Order toBeDeleted = OrderDAO.findByID ( order.getID ( ) );

        if ( toBeDeleted != null ) {

            Connection dataBaseConnection = ConnectionFactory.getConnection ( );
            PreparedStatement deleteStatement = null;

            try {
                deleteStatement = dataBaseConnection.prepareStatement ( deleteStatementString );
                deleteStatement.setInt ( 1, order.getID ( ) );
                deleteStatement.executeUpdate ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "OrderDAO:update " + e.getMessage ( ) );
            } finally {
                ConnectionFactory.close ( deleteStatement );
                ConnectionFactory.close ( dataBaseConnection );
            }
        } else
            throw new NoSuchElementException ( "Order with id = " + order.getID ( ) + " not found" );
    }
}
