package Models.Validators;

import Models.Product;

/**
 * Validator that assures positive product stock.
 */
public class ProductStockValidator implements Validator < Product > {

    public ProductStockValidator ( ) {
    }

    public void validate ( Product product ) throws IllegalArgumentException {
        if ( !( product.getStock ( ) >= 0 ) )
            throw new IllegalArgumentException ( "Not a valid amount of stock!" );
    }
}
