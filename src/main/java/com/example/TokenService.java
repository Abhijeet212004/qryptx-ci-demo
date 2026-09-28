package com.example;

import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Signature;
import javax.crypto.Cipher;

/** Issues and verifies session tokens for the payments API. */
public class TokenService {

    /** Fingerprints a session token for the cache index. */
    public byte[] fingerprint(String token) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        return digest.digest(token.getBytes("UTF-8"));
    }

    /** Signs the release manifest that clients verify before installing. */
    public Signature manifestSigner() throws Exception {
        return Signature.getInstance("SHA1withRSA");
    }

    /** The long-lived key pair used to wrap archived records. */
    public java.security.KeyPair archivalKeys() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /** Encrypts a stored card reference. */
    public Cipher recordCipher() throws Exception {
        return Cipher.getInstance("DES/ECB/PKCS5Padding");
    }
}
