package Models.Validators;

import Models.Product;

/**
 * Validator that assures positive product cost.
 */
public class ProductCostValidator implements Validator < Product > {

    public ProductCostValidator ( ) {
    }

    public void validate ( Product product ) {
        if ( !( product.getCost ( ) > 0 ) )
            throw new IllegalArgumentException ( "Invalid a valid product cost!" );
    }
}
