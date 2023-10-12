package org.cpsc8810.Model;

import org.cpsc8810.Service.crypto.DigitalSignatureService;

import java.security.*;
import java.util.UUID;

public class User {
    private Long id;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private int balance;

    private DigitalSignatureService digitalSignatureService = new DigitalSignatureService();

    public User(int balance) throws NoSuchAlgorithmException {
        this.id = System.currentTimeMillis();
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(1024, random);
        KeyPair keyPair = keyGen.generateKeyPair();
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public byte[] signData(String prevTxnHash) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return digitalSignatureService.signData(prevTxnHash, this.privateKey);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", publicKey=" + publicKey +
                ", privateKey=" + privateKey + "\n" +
                ", balance=" + balance +
                '}';
    }
}
