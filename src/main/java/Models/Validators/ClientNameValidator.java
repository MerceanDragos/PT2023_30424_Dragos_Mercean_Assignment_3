package Models.Validators;

import Models.Client;

/**
 * Validators that assures valid client names.
 */
import java.util.regex.Pattern;

public class ClientNameValidator implements Validator < Client > {
    private static final String NAME_PATTERN = "^[a-z ,.'-]+$";

    public ClientNameValidator ( ) {
    }

    public void validate ( Client client ) {
        Pattern pattern = Pattern.compile ( NAME_PATTERN );
        if ( !pattern.matcher ( client.getName ( ) ).matches ( ) )
            throw new IllegalArgumentException ( "Not a valid client name!" );
    }
}
