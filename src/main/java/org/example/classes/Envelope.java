package org.example.classes;

import org.example.util.JsonControl;
import org.example.util.Keys;

import java.util.Arrays;

public class Envelope {
    private byte[] encryptedInfo;

    private byte[] iv;  // random iv to decryptAES, transmit with encrypted info

    private byte[] encryptedAesKey; // AES key encrypted by RAS public key
    
    public Envelope() {
    }
    public Envelope(byte[] encryptedInfo, byte[] iv, byte[] encryptedAesKey) {
        this.encryptedInfo = encryptedInfo;
        this.iv = iv;
        this.encryptedAesKey = encryptedAesKey;
    }


    public byte[] getEncryptedAesKey() {
        return encryptedAesKey;
    }

    public void setEncryptedAesKey(byte[] encryptedAesKey) {
        this.encryptedAesKey = encryptedAesKey;
    }

    public String serialize(){
        // convert envelope to json
        return JsonControl.envelopeToJson(this);
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "encryptedInfo=" + Arrays.toString(encryptedInfo) +
                ", iv=" + Arrays.toString(iv) +
                ", encryptedAesKey=" + Arrays.toString(encryptedAesKey) +
                '}';
    }

    public byte[] getEncryptedInfo() {
        return encryptedInfo;
    }

    public void setEncryptedInfo(byte[] encryptedInfo) {
        this.encryptedInfo = encryptedInfo;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }


}
