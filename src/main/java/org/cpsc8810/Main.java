package org.cpsc8810;

import org.cpsc8810.Model.User;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Hello world!");
        try {
            User user1 = new User(100);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}