package Models.Validators;

/**
 * Interface that imposes a method format on data validators.
 */
public interface Validator< T > {

    public void validate ( T t ) throws IllegalArgumentException;

}

