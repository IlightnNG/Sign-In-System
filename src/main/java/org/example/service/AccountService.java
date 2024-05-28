package org.example.service;

import org.example.classes.DidDocument;
import org.example.classes.Info;
import org.example.classes.InfoString;
import org.example.classes.Person;
import org.example.request.ChallengeRequest;
import org.example.request.GetDidDocRequest;
import org.example.response.CreateEnvelopeResponse;
import org.example.response.DidDocResponse;
import org.example.util.*;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;

@Service
public class AccountService {

    // generate the key pair
    Person alice=  new Person("0x1a", Keys.generateKeyPair());
    Person bob=  new Person("0x1a", Keys.generateKeyPair());



    // content
    //String content = "hello world!fdafafdafadfafagdasgdasfewfa";

    public AccountService() throws NoSuchAlgorithmException {
    }

    public DidDocResponse getDidDoc(GetDidDocRequest getDidDocRequest){

        boolean isDidExist;
        // if did is null, it not exists. Return false
        if(getDidDocRequest.getDid()==null){
            isDidExist = false;
            return new DidDocResponse(isDidExist,"");
        }
        // get did
        String did = getDidDocRequest.getDid();

        // check if did exist
        isDidExist = Did.isDidExist(did);

        // did not exist
        if(!isDidExist){
            return new DidDocResponse(isDidExist,"");
        }

        // if did exists,then get the document.
        DidDocument didDocument = Did.getDidDocument(did);

        DidDocResponse didDocResponse = new DidDocResponse(isDidExist,didDocument);

        return didDocResponse;
    }

    public CreateEnvelopeResponse creatEnvelope(ChallengeRequest challenge) throws Exception {
        System.out.println("Alice public key: "+ Keys.encodeToString(alice.getKeyPair().getPublic().getEncoded()));
        System.out.println("Alice private key: "+ Keys.encodeToString(alice.getKeyPair().getPrivate().getEncoded()));
        System.out.println("Bob public key: "+ Keys.encodeToString(bob.getKeyPair().getPublic().getEncoded()));
        System.out.println("Bob private key: "+ Keys.encodeToString(bob.getKeyPair().getPrivate().getEncoded()));

        // get challengeId
        String challengeId = challenge.getChallengeId();

        // creat abstract
        String idAbstract = Hash.getSHA256Hash(challengeId);

        // use A's private key to sign the abstract
        byte[] signature = Keys.sign(idAbstract.getBytes(),alice.getKeyPair().getPrivate());

        // creat info and convert to json
        Info info = new Info(alice.getDid(),alice.getKeyPair().getPublic(),challengeId,signature);
        InfoString infoString = new InfoString(info);
        String infoJson= JsonControl.infoToJson(infoString);
        System.out.println("infoJson: "+ infoJson);

        // 生成Bob's AES密钥
        byte[] aesKey = HybridEncrypt.generateAESKey();

        // 将接受到的String转成PublicKey
        PublicKey publicKey = Keys.stringToPublickey(challenge.getPublicKeyWeb());

        // 使用RSA加密AES密钥
        byte[] encryptedAesKey = HybridEncrypt.rsaEncrypt(aesKey, publicKey);

        // 使用AES密钥加密 info+iv 得到envelope, 即CreateEnvelopeResponse
        CreateEnvelopeResponse envelopeResponse =  HybridEncrypt.encryptAES(infoJson.getBytes(), aesKey,encryptedAesKey);

        //System.out.println("Envelope: "+envelopeResponse.toString());

        return envelopeResponse;
    }
}
