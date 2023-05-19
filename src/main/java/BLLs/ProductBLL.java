package BLLs;

import DAO.ProductDAO;
import Models.Product;
import Models.Validators.ProductCostValidator;
import Models.Validators.ProductStockValidator;
import Models.Validators.Validator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Business-logic layer class for Product table.
 * Handles data validity.
 */
public class ProductBLL {
    private final ArrayList < Validator < Product > > validators;

    public ProductBLL ( ) {
        validators = new ArrayList < Validator < Product > > ( );
        validators.add ( new ProductCostValidator ( ) );
        validators.add ( new ProductStockValidator ( ) );
    }

    public Product[] findAll ( ) {
        return ProductDAO.findAll ( );
    }

    public Product find ( int ID ) throws NoSuchElementException {
        Product client = ProductDAO.findByID ( ID );
        if ( client == null )
            throw new NoSuchElementException ( "Product with id = " + ID + " not found" );

        return client;
    }

    public Product insert ( Product product ) throws IllegalArgumentException {
        for ( Validator < Product > v : validators ) {
            v.validate ( product );
        }

        return new Product ( ProductDAO.insert ( product ), product.getName ( ), product.getCost ( ), product.getStock ( ) );
    }

    public void update ( Product product ) throws IllegalArgumentException, NoSuchElementException {
        for ( Validator < Product > v : validators ) {
            v.validate ( product );
        }

        ProductDAO.update ( product );
    }

    public void delete ( Product product ) throws NoSuchElementException {
        ProductDAO.delete ( product );
    }

}