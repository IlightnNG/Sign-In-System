package org.example.classes;

public class DidDocument {
    private String did;
    private String content;

    public DidDocument() {
    }

    public DidDocument(String did, String content) {
        this.did = did;
        this.content = content;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DidDocument{" +
                "did='" + did + '\''+
                ", content='" + content +'\''+
                '}';
    }
}
