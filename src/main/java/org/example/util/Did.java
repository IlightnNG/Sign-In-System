package org.example.util;

import org.example.classes.DidDocument;

public class Did {
    public static boolean isDidExist(String did){
        if(did.length()<32)return false;
        // did system
        return true;
    }
    public static DidDocument getDidDocument(String did){
        // Did system


        return new DidDocument(did,"did document");
    }
}
