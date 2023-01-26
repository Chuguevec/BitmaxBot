package service.signature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Signature {
    private static final String ALGORITHM = "HmacSHA256";

    public String getSignature(String apiSecret, String verb, String path, long expires, String data) {
        return calculateHMac(apiSecret, verb + path + expires + data);
    }

    private String calculateHMac(String key, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance(ALGORITHM);
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), ALGORITHM);
            sha256_HMAC.init(secret_key);

            return byteArrayToHex(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
