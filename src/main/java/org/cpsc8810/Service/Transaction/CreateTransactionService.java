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

    /**
     * does all the validations and creates a transaction, steps are explained in the function
     * @param prevTxnHash
     * @param sender
     * @param signedPrevTxn
     * @param amount
     * @param receiver
     * @return
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    public Transaction createTransaction(String prevTxnHash, User sender, byte[] signedPrevTxn, Integer amount, User receiver)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        /**
         * sender or receiver cannot be null
         */
        if(sender == null || receiver == null) {
            System.out.println("no sender or receiver, please check");
            return null;
        }

        /**
         * sender and receiver cannot be the same
         */
        if(sender.getId().equals(receiver.getId())) {
            System.out.println("sender and received can not be same");
            return null;
        }

        /**
         * sender can't send more amount than his/her balance
         */
        if(sender.getBalance()  < amount) {
            System.out.println("sender can not send more amount than he/she already has");
            return null;
        }
        /**
         * verifying sender's authenticity by decrypting signeddata with sender's public key and verifying with prevTxnHash
         */
        boolean isVerified = digitalSignatureService.verifySignedPrevTxnHash(signedPrevTxn, sender.getPublicKey(), prevTxnHash);

        //do not create a transaction is verification is failed
        if(!isVerified) {
            System.out.println("failed to verify prevtxn hash with sender's public key");
            return null;
        }

        /**
         *updating balances after successfull verification
         *
         */
        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        //transaction is created, further added to a block
        Transaction transaction = new Transaction(sender, signedPrevTxn, amount, receiver);
        //creating transaction's hash with below formula
        String plainTextForMyHash = hashingService.hashData(prevTxnHash + receiver.getId().toString() + transaction.getTxnId());

        transaction.setTxnHash(plainTextForMyHash);

        return transaction;
    }

}
