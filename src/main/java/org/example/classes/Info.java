package org.example.classes;

import java.security.PublicKey;

public class Info {

    private PublicKey publicKey;
    private String content;
    private byte[] signature;

    public Info() {
    }

    public Info(PublicKey publicKey, String content, byte[] signature) {
        this.publicKey = publicKey;
        this.content = content;
        this.signature = signature;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
}
