package org.example.classes;

import java.security.PublicKey;

public class Info {

    private String did;
    private PublicKey publicKey;
    private String content;
    private byte[] contentSignature;

    public Info() {
    }

    public Info(String did,PublicKey publicKey, String content, byte[] contentSignature) {
        this.did = did;
        this.publicKey = publicKey;
        this.content = content;
        this.contentSignature = contentSignature;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
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
