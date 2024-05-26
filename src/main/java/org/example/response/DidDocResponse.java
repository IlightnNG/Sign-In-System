package org.example.response;

import org.example.classes.DidDocument;
import org.example.util.JsonControl;

public class DidDocResponse {
    private boolean isDidExist;

    private String didDoc;

    public DidDocResponse() {
    }
    public DidDocResponse(boolean isDidExist, DidDocument didDoc) {
        this.isDidExist = isDidExist;
        this.didDoc = didDoc.toString();
    }

    public DidDocResponse(boolean isDidExist, String didDoc) {
        this.isDidExist = isDidExist;
        this.didDoc = didDoc;
    }

    public boolean isDidExist() {
        return isDidExist;
    }

    public void setDidExist(boolean didExist) {
        isDidExist = didExist;
    }


    public String getDidDoc() {
        return didDoc;
    }

    public void setDidDoc(String didDoc) {
        this.didDoc = didDoc;
    }

    @Override
    public String toString() {
        return "DidDocResponse{" +
                "isDidExist='" + isDidExist +
                ", didDoc='" + didDoc + '\'' +
                '}';
    }
}
