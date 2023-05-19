package Models.Validators;

import Models.Order;

/**
 * Validator that assures positive order quantity.
 */
public class OrderQuantityValidator implements Validator < Order > {

    public OrderQuantityValidator ( ) {
    }

    public void validate ( Order order ) throws IllegalArgumentException {
        if ( !( order.getQuantity ( ) > 0 ) )
            throw new IllegalArgumentException ( "Not a valid order quantity!" );
    }
}
