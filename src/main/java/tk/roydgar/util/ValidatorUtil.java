package tk.roydgar.util;

public class ValidatorUtil {

    private ValidatorUtil() {}

    public static boolean stringIsEmptyOrNull(String ... params) {
        for (String s : params) {
            if (s == null || s.isEmpty()) {
                return true;
            }
        }

        return false;
    }
}
