package org.cpsc8810.Model;

public class Transaction {

    private Long txnId;
    private User receiver;
    private User sender;
    private byte[] signedPrevTxn;
    private Integer amount;
    private String txnHash;
    public Transaction( User sender, byte[] signedPrevTxn, Integer amount, User receiver) {
        this.txnId = System.currentTimeMillis();
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

    public byte[] getSignedPrevTxn() {
        return signedPrevTxn;
    }

    public void setSignedPrevTxn(byte[] signedPrevTxn) {
        this.signedPrevTxn = signedPrevTxn;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTxnHash() {
        return txnHash;
    }

    public void setTxnHash(String txnHash) {
        this.txnHash = txnHash;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txnId=" + txnId +
                ", receiver=" + receiver +
                ", sender=" + sender +
                ", signedPrevTxn='" + signedPrevTxn + '\'' +
                ", amount=" + amount +
                '}';
    }
}
