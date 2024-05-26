package org.example.process;

import org.example.classes.Envelope;
import org.example.classes.Info;
import org.example.classes.InfoString;
import org.example.classes.Person;
import org.example.util.Hash;
import org.example.util.HybridEncrypt;
import org.example.util.JsonControl;
import org.example.util.Keys;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.*;

public class Sender {


    public static Envelope creatEnvelope(Person person, PublicKey publicKeyB, String content,byte[]  challenge) throws Exception {
        // creat abstract
        String conAbstract = Hash.getSHA256Hash(content);

        // use A's private key to sign the abstract
        byte[] signature = Keys.sign(conAbstract.getBytes(),person.getKeyPair().getPrivate());

        // creat info and convert to json
        Info info = new Info(person.getKeyPair().getPublic(),content,signature);
        InfoString infoString = new InfoString(info);
        String infoJson= JsonControl.infoToJson(infoString);
        System.out.println(infoJson);

        // 生成Bob's AES密钥
        byte[] aesKey = HybridEncrypt.generateAESKey();

        // 使用RSA加密AES密钥
        byte[] encryptedAesKey = HybridEncrypt.rsaEncrypt(aesKey, publicKeyB);

        // 使用AES密钥加密 info+iv 得到envelope
        Envelope envelope = HybridEncrypt.encryptAES(infoJson.getBytes(), aesKey,encryptedAesKey);

        System.out.println("Envelope: "+envelope.toString());

        return envelope;
    }
}
