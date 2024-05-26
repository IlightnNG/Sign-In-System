package org.example.classes;

import org.example.util.Keys;

public class InfoString {

    private String publicKey;
    private String content;
    private String signature;

    public InfoString() {
    }
    public InfoString(Info info){
        this.publicKey = Keys.encodeToString(info.getPublicKey().getEncoded());
        this.content = info.getContent();
        this.signature = Keys.encodeToString(info.getContentSignature());
    }

    public InfoString(String publicKey, String content, String signature) {
        this.publicKey = publicKey;
        this.content = content;
        this.signature =signature;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Info{" +
                "publicKey=" + publicKey +
                ", content='" + content +
                ", signature=" + signature +
                '}';
    }
}
