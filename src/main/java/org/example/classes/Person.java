package org.example.classes;

import java.security.KeyPair;

public class Person {
    private String did;
    // private key and public key
    private KeyPair keyPair;

    public Person(String did, KeyPair keyPair) {
        this.did = did;
        this.keyPair = keyPair;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public String toString() {
        return "Person{" +
                "did='" + did + '\'' +
                ", keyPair=" + keyPair +
                '}';
    }
}
