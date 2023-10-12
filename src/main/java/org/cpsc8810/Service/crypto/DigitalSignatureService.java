package org.cpsc8810.Service.crypto;

import java.security.*;

/*
    utility service  for DSA purposes
 */
public class DigitalSignatureService {

    /*
    * digitally signs data with provided private key
    * */
    public byte[] signData(String hash, PrivateKey privKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withRSA");

        //Initializing the signature
        sign.initSign(privKey);
        byte[] bytes = hash.getBytes();

        //Adding data to the signature
        sign.update(bytes);

        //Calculating the signature
        byte[] signature = sign.sign();

        return signature;
    }

    /**
     * decrypts signed data with provided public key and verify's with third argument
     * @param signedData
     * @param pubKey
     * @param prevTxnData
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean verifySignedPrevTxnHash(byte[] signedData, PublicKey pubKey, String prevTxnData) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(pubKey);
        sign.update(prevTxnData.getBytes());

        //Verifying the signature
        boolean isVerified = sign.verify(signedData);

        return isVerified;

    }
}
