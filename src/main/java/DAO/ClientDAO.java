package DAO;

import Connection.ConnectionFactory;
import Models.Client;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object class for Client table.
 * Implements 5 of the most common sql operations.
 */
public class ClientDAO {
    protected static final Logger LOGGER = Logger.getLogger ( ClientDAO.class.getName ( ) );

    private static final String findAllStatementString = "SELECT * FROM client";
    private static final String findStatementString = "SELECT * FROM client where client_id = ?";
    private static final String insertStatementString = "INSERT INTO client ( client_name, client_email ) VALUES ( ?, ? )";
    private static final String updateStatementString = "UPDATE client SET client_name = ?, client_email = ? WHERE client_id = ?";
    private static final String deleteStatementString = "DELETE FROM client WHERE client_id = ?";

    public static Client[] findAll ( ) {
        Client[] found = new Client[ 1 ];

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findAllStatement = null;
        ResultSet resultSet = null;
        try {
            findAllStatement = dataBaseConnection.prepareStatement ( findAllStatementString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
            resultSet = findAllStatement.executeQuery ( );

            int i;
            for ( i = 0; resultSet.next ( ); i++ ) ;
            if ( i > 0 ) {
                found = new Client[ i ];
                i = 0;
                resultSet.beforeFirst ( );

                while ( resultSet.next ( ) ) {
                    found[ i ] = new Client ( resultSet.getInt ( "client_id" ), resultSet.getString ( "client_name" ), resultSet.getString ( "client_email" ) );
                    i++;
                }
            }
        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "ClientDAO:findAll " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findAllStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }

        return found;
    }

    public static Client findByID ( int ID ) {
        Client found = null;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = dataBaseConnection.prepareStatement ( findStatementString );
            findStatement.setLong ( 1, ID );
            resultSet = findStatement.executeQuery ( );

            if ( resultSet.next ( ) )
                found = new Client ( resultSet.getInt ( "client_id" ), resultSet.getString ( "client_name" ), resultSet.getString ( "client_email" ) );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "ClientDAO:findByID " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( findStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return found;
    }

    public static int insert ( Client client ) {
        int insertedID = -1;

        Connection dataBaseConnection = ConnectionFactory.getConnection ( );
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;
        try {
            insertStatement = dataBaseConnection.prepareStatement ( insertStatementString, Statement.RETURN_GENERATED_KEYS );
            insertStatement.setString ( 1, client.getName ( ) );
            insertStatement.setString ( 2, client.getEmail ( ) );
            insertStatement.executeUpdate ( );

            resultSet = insertStatement.getGeneratedKeys ( );
            resultSet.next ( );
            insertedID = resultSet.getInt ( "client_id" );

        } catch ( SQLException e ) {
            LOGGER.log ( Level.WARNING, "ClientDAO:insert " + e.getMessage ( ) );
        } finally {
            ConnectionFactory.close ( resultSet );
            ConnectionFactory.close ( insertStatement );
            ConnectionFactory.close ( dataBaseConnection );
        }
        return insertedID;
    }

    public static void update ( Client client ) throws NoSuchElementException {
        Client toBeUpdated = ClientDAO.findByID ( client.getID ( ) );

        if ( toBeUpdated != null ) {

            Connection dataBaseConnection = ConnectionFactory.getConnection ( );
            PreparedStatement updateStatement = null;

            try {
                updateStatement = dataBaseConnection.prepareStatement ( updateStatementString );
                updateStatement.setString ( 1, client.getName ( ) );
                updateStatement.setString ( 2, client.getEmail ( ) );
                updateStatement.setInt ( 3, client.getID ( ) );
                updateStatement.executeUpdate ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "ClientDAO:update " + e.getMessage ( ) );
            } finally {
                ConnectionFactory.close ( updateStatement );
                ConnectionFactory.close ( dataBaseConnection );
            }
        } else
            throw new NoSuchElementException ( "Client with id = " + client.getID ( ) + " not found" );
    }

    public static void delete ( Client client ) {
        Client toBeDeleted = ClientDAO.findByID ( client.getID ( ) );

        if ( toBeDeleted != null ) {

            Connection dataBaseConnection = ConnectionFactory.getConnection ( );
            PreparedStatement deleteStatement = null;

            try {
                deleteStatement = dataBaseConnection.prepareStatement ( deleteStatementString );
                deleteStatement.setInt ( 1, client.getID ( ) );
                deleteStatement.executeUpdate ( );
            } catch ( SQLException e ) {
                LOGGER.log ( Level.WARNING, "ClientDAO:update " + e.getMessage ( ) );
            } finally {
                ConnectionFactory.close ( deleteStatement );
                ConnectionFactory.close ( dataBaseConnection );
            }
        } else
            throw new NoSuchElementException ( "Client with id = " + client.getID ( ) + " not found" );
    }
}
