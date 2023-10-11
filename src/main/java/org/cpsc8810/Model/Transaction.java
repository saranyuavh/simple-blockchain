package org.cpsc8810.Model;

import java.security.PublicKey;

public class Transaction {

    private Long txnId;
    private String prevTxnHash;
    private User receiver;
    private User sender;
    private String signedPrevTxn;
    private Integer amount;

    public Transaction(String prevTxnHash, User sender, String signedPrevTxn, Integer amount, User receiver) {
        this.txnId = System.currentTimeMillis();
        this.prevTxnHash = prevTxnHash;
        this.sender = sender;
        this.receiver = receiver;
        this.signedPrevTxn = signedPrevTxn;
        this.amount = amount;
    }

    public Long getTxnId() {
        return txnId;
    }

    public void setTxnId(Long txnId) {
        this.txnId = txnId;
    }

    public String getPrevTxnHash() {
        return prevTxnHash;
    }

    public void setPrevTxnHash(String prevTxnHash) {
        this.prevTxnHash = prevTxnHash;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSignedPrevTxn() {
        return signedPrevTxn;
    }

    public void setSignedPrevTxn(String signedPrevTxn) {
        this.signedPrevTxn = signedPrevTxn;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txnId=" + txnId +
                ", prevTxnHash='" + prevTxnHash + '\'' +
                ", receiver=" + receiver +
                ", sender=" + sender +
                ", signedPrevTxn='" + signedPrevTxn + '\'' +
                ", amount=" + amount +
                '}';
    }
}
