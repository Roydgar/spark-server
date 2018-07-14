package tk.roydgar.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashUtil {

    public static String hash(String passwordPlaintext) {
        String salt = BCrypt.gensalt(12);

        return BCrypt.hashpw(passwordPlaintext, salt);
    }

    public static boolean check(String passwordPlaintext, String storedHash) {
        if(null == storedHash || !storedHash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        return BCrypt.checkpw(passwordPlaintext, storedHash);
    }

}
