package DAO;

import Connection.ConnectionFactory;
import Models.Bill;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object class for Bill table.
 * Implements 5 of the most common sql operations.
 */
public class BillDAO {
    protected static final Logger LOGGER = Logger.getLogger ( BillDAO.class.getName ( ) );

    private static final String findAllStatementString = "SELECT * FROM bill";
    private static final String findStatementString = "SELECT * FROM bill where bill_id = ?";
    private static final String insertStatementString = "INSERT INTO bill ( bill_id, bill_product_id, bill_client_id, bill_quantity, bill_cost ) VALUES ( ?, ?, ?, ?, ? )";
    private static final String updateStatementString = "UPDATE bill SET bill_product_id = ?, bill_client_id = ?, bill_quantity = ?, bill_cost = ? WHERE bill_id = ?";
    private static final String deleteStatementString = "DELETE FROM bill WHERE bill_id = ?";

    public static Bill[] findAll ( ) {
        Bill[] found = new Bill[ 1 ];

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findAllStatement = null;
        ResultSet resultSet = null;
        try {
            findAllStatement = dataBaseConnection.prepareStatement ( findAllStatementString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
            resultSet = findAllStatement.executeQuery ( );

            int i;
            for ( i = 0; resultSet.next ( ); i++ ) ;
            if ( i > 0 ) {
                found = new Bill[ i ];
                i = 0;
                resultSet.beforeFirst ( );

                while ( resultSet.next ( ) ) {
                    found[ i ] = new Bill ( resultSet.getInt ( "bill_id" ), resultSet.getInt ( "bill_product_id" ), resultSet.getInt ( "bill_client_id" ), resultSet.getInt ( "bill_quantity" ), resultSet.getDate ( "bill_date" ), resultSet.getFloat ( "bill_cost" ) );
                    i++;
                }
            }
        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "BillDAO:findAll " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findAllStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }

        return found;
    }

    public static Bill findByID ( int ID ) {
        Bill found = null;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dataBaseConnection.prepareStatement ( findStatementString );
            findStatement.setLong ( 1, ID );
            resultSet = findStatement.executeQuery ( );

            if ( resultSet.next ( ) )
                found = new Bill ( resultSet.getInt ( "bill_id" ), resultSet.getInt ( "bill_product_id" ), resultSet.getInt ( "bill_client_id" ), resultSet.getInt ( "bill_quantity" ), resultSet.getDate ( "bill_date" ), resultSet.getFloat ( "bill_cost" ) );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "BillDAO:findByID " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return found;
    }

    public static int insert ( Bill bill ) {
        int insertedID = -1;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;
        try {
            insertStatement = dataBaseConnection.prepareStatement ( insertStatementString, Statement.RETURN_GENERATED_KEYS );
            insertStatement.setInt ( 1, bill.getID ( ) );
            insertStatement.setInt ( 2, bill.getProductID ( ) );
            insertStatement.setInt ( 3, bill.getClientID ( ) );
            insertStatement.setInt ( 4, bill.getQuantity ( ) );
            insertStatement.setFloat ( 5, bill.getCost ( ) );
            insertStatement.executeUpdate ( );

            resultSet = insertStatement.getGeneratedKeys ( );
            resultSet.next ( );
            insertedID = resultSet.getInt ( "bill_id" );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "BillDAO:insert " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( insertStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return insertedID;
    }

}
