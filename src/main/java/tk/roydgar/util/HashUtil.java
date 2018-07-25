package tk.roydgar.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashUtil {

    public static String hash(String passwordPlaintext) {
        String salt = BCrypt.gensalt(12);

        return BCrypt.hashpw(passwordPlaintext, salt);
    }

    public static boolean check(String passwordPlaintext, String storedHash) {
        return BCrypt.checkpw(passwordPlaintext, storedHash);
    }

    public static void main(String[] args) {
        System.out.println(hash("vit@mail.ru0000"));
    }

}
