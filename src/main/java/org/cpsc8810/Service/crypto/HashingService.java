package org.cpsc8810.Service.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingService {

    /*
    * hashes provided data using SHA3 algorithm in 256 bits
    * */
    public String hashData(String data) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                data.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);
        return sha3Hex;
    }

    /**
     * converts a given byte data into string
     * @param hash
     * @return
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
