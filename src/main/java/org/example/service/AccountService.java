package org.example.service;

import org.example.QRcode.QRCodeGenerator;
import org.example.QRcode.QRCodeReader;
import org.example.classes.Envelope;
import org.example.classes.Info;
import org.example.classes.InfoString;
import org.example.classes.Person;
import org.example.process.Receiver;
import org.example.process.Sender;
import org.example.request.ChallengeRequest;
import org.example.response.CreateEnvelopeResponse;
import org.example.util.Hash;
import org.example.util.HybridEncrypt;
import org.example.util.JsonControl;
import org.example.util.Keys;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class AccountService {

    // generate the key pair
    Person alice=  new Person("0x1a", Keys.generateKeyPair());

    // content
    String content = "hello world!fdafafdafadfafagdasgdasfewfa";

    public AccountService() throws NoSuchAlgorithmException {
    }

    // creat content


    public CreateEnvelopeResponse creatEnvelope(ChallengeRequest challenge) throws Exception {

        // creat abstract
        String conAbstract = Hash.getSHA256Hash(content);

        // use A's private key to sign the abstract
        byte[] signature = Keys.sign(conAbstract.getBytes(),alice.getKeyPair().getPrivate());

        // creat info and convert to json
        Info info = new Info(alice.getKeyPair().getPublic(),content,signature);
        InfoString infoString = new InfoString(info);
        String infoJson= JsonControl.infoToJson(infoString);
        System.out.println(infoJson);

        // 生成Bob's AES密钥
        byte[] aesKey = HybridEncrypt.generateAESKey();

        // 将接受到的String转成PublicKey
        PublicKey publicKey = Keys.stringToPublickey(challenge.getPublicKeyWeb());

        // 使用RSA加密AES密钥
        byte[] encryptedAesKey = HybridEncrypt.rsaEncrypt(aesKey, publicKey);

        // 使用AES密钥加密 info+iv 得到envelope, 即CreateEnvelopeResponse
        CreateEnvelopeResponse envelopeResponse =  HybridEncrypt.encryptAES(infoJson.getBytes(), aesKey,encryptedAesKey,challenge.getChallengeId().getBytes());

        //System.out.println("Envelope: "+envelopeResponse.toString());

        return envelopeResponse;
    }
}
