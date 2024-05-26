package org.example.classes;

import java.security.PublicKey;

public class Info {

    private PublicKey publicKey;
    private String content;
    private byte[] contentSignature;

    public Info() {
    }

    public Info(PublicKey publicKey, String content, byte[] contentSignature) {
        this.publicKey = publicKey;
        this.content = content;
        this.contentSignature = contentSignature;
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

    public byte[] getContentSignature() {
        return contentSignature;
    }

    public void setContentSignature(byte[] contentSignature) {
        this.contentSignature = contentSignature;
    }
}
