package org.cpsc8810;

import org.cpsc8810.Model.Transaction;
import org.cpsc8810.Model.User;
import org.cpsc8810.Service.Transaction.CreateTransactionService;
import org.cpsc8810.Service.crypto.DigitalSignatureService;
import org.cpsc8810.Service.crypto.HashingService;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String prevTxnHash = String.valueOf(Math.random());
    private static CreateTransactionService createTransactionService = new CreateTransactionService();
    private  static HashingService hashingService = new HashingService();

    private  static DigitalSignatureService digitalSignatureService = new DigitalSignatureService();
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Hello world!");
        prevTxnHash = hashingService.hashData(String.valueOf(Math.random()));
        try {
            User user1 = new User(100);
            Thread.sleep(((int)(Math.random() * 5) + 1)*1000);
            User user2 = new User(0);
            Thread.sleep(((int)(Math.random() * 5) + 1)*1000);
            User user3 = new User(0);
            Thread.sleep(((int)(Math.random() * 5) + 1)*1000);
            User user4 = new User(0);
            Thread.sleep(((int)(Math.random() * 5) + 1)*1000);
            User user5 = new User(0);
            Thread.sleep(((int)(Math.random() * 5) + 1)*1000);
            User user6 = new User(0);

            List<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);
            users.add(user3);
            users.add(user4);
            users.add(user5);
            users.add(user6);

            int max = 5, min = 0;
            int range = max-min+1;
            int rand1 = 0;
            int rand2 = 0;
            List<Transaction> transactions = new ArrayList<>();

            //testing
//            System.out.println(user1.toString());
//            byte[] signedDataTest = user1.signData(prevTxnHash);
//            boolean isVerified = digitalSignatureService.verifySignedPrevTxnHash(signedDataTest, user1.getPublicKey(), prevTxnHash);
//            System.out.println("verified "+isVerified);
//            return;

            while(transactions.size()<13) {
                rand1 = (int)(Math.random() * range) + min;
                rand2 = (int)(Math.random() * range) + min;
                if(rand1 == rand2) continue;

                User sender = users.get(rand1);
                User receiver = users.get(rand2);
                byte[] signedData = null;
                try {
                    signedData = sender.signData(prevTxnHash);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                int randomTxnAmount = (int)(Math.random() * 99) + 1;

                String initialState = sender.getId() +" " +
                        sender.getBalance() +" " +
                        randomTxnAmount + " " +
                        receiver.getId() + " " +
                        receiver.getBalance();
                Transaction transaction = createTransactionService.createTransaction(prevTxnHash, sender, signedData,
                        randomTxnAmount, receiver);
                if(transaction != null) {
                    System.out.println("Initial state of the transaction");
                    System.out.println(initialState);
                    System.out.println("Post transaction state transaction");
                    System.out.println(
                            transaction.getSender().getId() +" " +
                            transaction.getSender().getBalance() +" " +
                            transaction.getTxnId() +" " +
                            transaction.getAmount() + " " +
                            transaction.getReceiver().getId() + " " +
                            transaction.getReceiver().getBalance()
                    );
                    System.out.println(" ");
                    transactions.add(transaction);
                    prevTxnHash = transaction.getTxnHash();
                }
            }


        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


    }
}