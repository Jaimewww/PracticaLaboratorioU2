package util;

/**
 *
 * Utility class for validating input values.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class Validator {
    public static void validateNegatives(double value)throws IllegalArgumentException {
        if(value <= 0.0) {
            throw new IllegalArgumentException("The value cannot be less than zero.");
        }
    }

    public static void validateNegatives(int value)throws IllegalArgumentException {
        if(value <= 0) {
            throw new IllegalArgumentException("The value cannot be less than zero.");
        }
    }
}
