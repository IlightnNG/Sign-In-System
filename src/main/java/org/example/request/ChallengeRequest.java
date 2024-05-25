package org.example.request;

import java.security.PublicKey;
import java.util.Arrays;

public class ChallengeRequest {
    private String challengeId;
    private String publicKeyWeb;

    public ChallengeRequest(String challengeId,String publicKeyWeb) {
        this.challengeId = challengeId;
        this.publicKeyWeb = publicKeyWeb;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public String getPublicKeyWeb() {
        return publicKeyWeb;
    }

    public void setPublicKeyWeb(String publicKeyWeb) {
        this.publicKeyWeb = publicKeyWeb;
    }

    @Override
    public String toString() {
        return "ChallengeRequest{" +
                "challengeId=" +challengeId +
                ", publicKeyWeb=" + publicKeyWeb +
                '}';
    }
}
