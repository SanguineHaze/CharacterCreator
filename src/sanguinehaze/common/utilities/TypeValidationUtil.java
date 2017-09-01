package sanguinehaze.common.utilities;

public class TypeValidationUtil {

    public static boolean isValidNumber(String val) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(val);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
