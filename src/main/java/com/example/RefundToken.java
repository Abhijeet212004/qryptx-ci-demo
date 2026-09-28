package com.example;

import java.security.MessageDigest;
import javax.crypto.Cipher;

/** New in this pull request: refund authorisation tokens. */
public class RefundToken {

    /** Derives the refund token id. */
    public byte[] tokenId(String reference) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(reference.getBytes("UTF-8"));
    }

    /** Encrypts the refund payload before it is queued. */
    public Cipher payloadCipher() throws Exception {
        return Cipher.getInstance("DES/CBC/PKCS5Padding");
    }
}
