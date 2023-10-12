package org.cpsc8810.Service.Transaction;

import org.cpsc8810.Model.Transaction;
import org.cpsc8810.Model.User;
import org.cpsc8810.Service.crypto.DigitalSignatureService;
import org.cpsc8810.Service.crypto.HashingService;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class CreateTransactionService {

    DigitalSignatureService digitalSignatureService = new DigitalSignatureService();

    HashingService hashingService = new HashingService();
    public Transaction createTransaction(String prevTxnHash, User sender, byte[] signedPrevTxn, Integer amount, User receiver)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if(sender == null || receiver == null) {
            System.out.println("no sender or receiver, please check");
            return null;
        }
        if(sender.getId().equals(receiver.getId())) {
            System.out.println("sender and received can not be same");
            return null;
        }
        if(sender.getBalance()  < amount) {
            System.out.println("sender can not send more amount than he/she already has");
            return null;
        }
        boolean isVerified = digitalSignatureService.verifySignedPrevTxnHash(signedPrevTxn, sender.getPublicKey(), prevTxnHash);
        if(!isVerified) {
            System.out.println("failed to verify prevtxn hash with sender's public key");
            return null;
        }
        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);
        Transaction transaction = new Transaction(sender, signedPrevTxn, amount, receiver);
        String plainTextForMyHash = hashingService.hashData(prevTxnHash + receiver.getId().toString() + transaction.getTxnId());
        transaction.setTxnHash(plainTextForMyHash);

        return transaction;
    }

}
