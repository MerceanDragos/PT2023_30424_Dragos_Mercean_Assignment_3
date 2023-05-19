package DAO;

import Connection.ConnectionFactory;
import Models.Product;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object class for Product table.
 * Implements 5 of the most common sql operations.
 */
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger ( ProductDAO.class.getName ( ) );

    private static final String findAllStatementString = "SELECT * FROM product";
    private static final String findStatementString = "SELECT * FROM product where product_id = ?";
    private static final String insertStatementString = "INSERT INTO product ( product_name, product_cost, product_stock ) VALUES ( ?, ?, ? )";
    private static final String updateStatementString = "UPDATE product SET product_name = ?, product_cost = ?, product_stock = ? WHERE product_id = ?";
    private static final String deleteStatementString = "DELETE FROM product WHERE product_id = ?";

    public static Product[] findAll ( ) {
        Product[] found = new Product[ 1 ];

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findAllStatement = null;
        ResultSet resultSet = null;
        try {
            findAllStatement = dataBaseConnection.prepareStatement ( findAllStatementString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
            resultSet = findAllStatement.executeQuery ( );

            int i;
            for ( i = 0; resultSet.next ( ); i++ ) ;
            if ( i > 0 ) {
                found = new Product[ i ];
                i = 0;
                resultSet.beforeFirst ( );

                while ( resultSet.next ( ) ) {
                    found[ i ] = new Product ( resultSet.getInt ( "product_id" ), resultSet.getString ( "product_name" ), resultSet.getFloat ( "product_cost" ), resultSet.getInt ( "product_stock" ) );
                    i++;
                }
            }
        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "ProductDAO:findAll " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findAllStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }

        return found;
    }

    public static Product findByID ( int ID ) {
        Product found = null;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dataBaseConnection.prepareStatement ( findStatementString );
            findStatement.setLong ( 1, ID );
            resultSet = findStatement.executeQuery ( );

            if ( resultSet.next ( ) )
                found = new Product ( resultSet.getInt ( "product_id" ), resultSet.getString ( "product_name" ), resultSet.getFloat ( "product_cost" ), resultSet.getInt ( "product_stock" ) );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "ProductDAO:findByID " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return found;
    }

    public static int insert ( Product product ) {
        int insertedID = -1;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;
        try {
            insertStatement = dataBaseConnection.prepareStatement ( insertStatementString, Statement.RETURN_GENERATED_KEYS );
            insertStatement.setString ( 1, product.getName ( ) );
            insertStatement.setFloat ( 2, product.getCost ( ) );
            insertStatement.setInt ( 3, product.getStock ( ) );
            insertStatement.executeUpdate ( );

            resultSet = insertStatement.getGeneratedKeys ( );
            resultSet.next ();
            insertedID = resultSet.getInt ( "product_id" );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "ProductDAO:insert " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( insertStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return insertedID;
    }

    public static void update ( Product product ) throws NoSuchElementException {
        Product toBeUpdated = ProductDAO.findByID ( product.getID ( ) );

        if ( toBeUpdated != null ) {

            Connection dataBaseConnection = ConnectionFactory.getConnection ( );
            PreparedStatement updateStatement = null;

            try {
                updateStatement = dataBaseConnection.prepareStatement ( updateStatementString );
                updateStatement.setString ( 1, product.getName ( ) );
                updateStatement.setFloat ( 2, product.getCost ( ) );
                updateStatement.setInt ( 3, product.getStock ( ) );
                updateStatement.setInt ( 4, product.getID ( ) );
                updateStatement.executeUpdate ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "ProductDAO:update " + e.getMessage ( ) );
            } finally {
                ConnectionFactory.close ( updateStatement );
                ConnectionFactory.close ( dataBaseConnection );
            }
        } else
            throw new NoSuchElementException ( "Product with id = " + product.getID ( ) + " not found" );
    }

    public static void delete ( Product product ) {
        Product toBeDeleted = ProductDAO.findByID ( product.getID ( ) );

        if ( toBeDeleted != null ) {

            Connection dataBaseConnection = ConnectionFactory.getConnection ( );
            PreparedStatement deleteStatement = null;

            try {
                deleteStatement = dataBaseConnection.prepareStatement ( deleteStatementString );
                deleteStatement.setInt ( 1, product.getID ( ) );
                deleteStatement.executeUpdate ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "ProductDAO:update " + e.getMessage ( ) );
            } finally {
                ConnectionFactory.close ( deleteStatement );
                ConnectionFactory.close ( dataBaseConnection );
            }
        } else
            throw new NoSuchElementException ( "Product with id = " + product.getID ( ) + " not found" );
    }
}
