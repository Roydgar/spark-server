package tk.roydgar.util;

import java.security.spec.KeySpec;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;

public class DbPasswordHasher {

    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private Cipher cipher;
    private SecretKey key;

    @SneakyThrows
    public DbPasswordHasher(){
        String myEncryptionKey = "ThisIsSpartaThisIsSparta";
        String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        byte[] arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }


    @SneakyThrows
    public String encrypt(String unencryptedString) {
        String encryptedString = null;

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
        byte[] encryptedText = cipher.doFinal(plainText);
        encryptedString = new String(Base64.encodeBase64(encryptedText));

        return encryptedString;
    }

    @SneakyThrows
    public String decrypt(String encryptedString) {
        String decryptedText=null;
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedText = Base64.decodeBase64(encryptedString);
        byte[] plainText = cipher.doFinal(encryptedText);
        decryptedText= new String(plainText);
        return decryptedText;
    }

    public static void main(String args [])
    {
        DbPasswordHasher td= new DbPasswordHasher();

        String target="target";
        String encrypted=td.encrypt(target);
        String decrypted=td.decrypt(encrypted);

        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);

    }


}